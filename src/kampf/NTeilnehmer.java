package kampf;

import effektkarten.ansichtkarte.*;
import effektkarten.effekte.effekt.*;
import effektkarten.effekte.wirkung.*;
import effektkarten.effekte.ziel.*;
import java.util.*;
import java.util.stream.*;
import effektkarten.karten.*;
import main.*;

public final class NTeilnehmer implements EffektZielCharakter
{
	private final Einstellungen e;
	//Charakter
	private final NCharakter nCharakter;

	//Waffen
	public NWaffe nHauptwaffe;
	public NWaffe nNebenwaffe;

	//Reihenfolge
	public int gesVorteil0;
	public int index;

	//Permanente Werte
	private int magie;

	private int leben;

	//Aktion
	private NAktion nAktion;
	private MitWaffe mit;
	private NTeilnehmer ziel;

	//Werte für diesen Zug
	private int geladeneMagie;
	private boolean gibtMagieAus;
	public List<MagieEffektOption> magieEffektOptionen;
	private int gesAktion;
	private int gesBonusAngriff;
	private int gesBonusMindestschaden;
	private int anzahlAngriffe;
	private final List<NTeilnehmer> angegriffenVon;

	public NTeilnehmer(Einstellungen e, Charakterkarte charakterkarte, Waffenkarte hauptwafffe, Waffenkarte nebenwaffe)
	{
		this(e, charakterkarte, hauptwafffe, nebenwaffe, charakterkarte.leben() * e.lebenMultiplikator);
	}

	public NTeilnehmer(Einstellungen e, Charakterkarte charakterkarte, Waffenkarte hauptwafffe, Waffenkarte nebenwaffe, int leben)
	{
		this.e = e;
		nCharakter = new NCharakter(charakterkarte);
		nHauptwaffe = NWaffe.von(hauptwafffe);
		nNebenwaffe = NWaffe.von(nebenwaffe);
		magie = 0;
		this.leben = leben;
		magieEffektOptionen = new ArrayList<>();
		angegriffenVon = new ArrayList<>();
	}

	public void beginneZug()
	{
		nAktion = null;
		mit = null;
		ziel = null;
		geladeneMagie = 0;
		gibtMagieAus = false;
		magieEffektOptionen.clear();
		gesAktion = 0;
		gesBonusAngriff = 0;
		gesBonusMindestschaden = 1;
		anzahlAngriffe = 1;
		angegriffenVon.clear();
	}

	public void triggereEffekteVorAktion(StartTrigger startTrigger)
	{
		triggereEffekte(getCharakterKarte().effekte(), startTrigger, true, null, null);
		if(nHauptwaffe != null)
			triggereEffekte(nHauptwaffe.karte.effekte(), startTrigger, true, null, MitWaffe.HW);
		if(nNebenwaffe != null)
			triggereEffekte(nNebenwaffe.karte.effekte(), startTrigger, true, null, MitWaffe.NW);
	}

	public void triggereEffekteMitAktion(StartTrigger startTrigger)
	{
		triggereEffekte(getCharakterKarte().effekte(), startTrigger, true, ziel, null);
		triggereEffekte(getWaffeKarte(mit).effekte(), startTrigger, true, ziel, null);
		triggereEffekte(getAktionKarte().effekte(), startTrigger, true, ziel, null);
		ziel.triggereEffekte(getCharakterKarte().effekte(), startTrigger, false, this, null);
		ziel.triggereEffekte(getWaffeKarte(mit).effekte(), startTrigger, false, this, null);
		ziel.triggereEffekte(getAktionKarte().effekte(), startTrigger, false, this, null);
	}

	private void triggereEffekte(List<? extends KartenEffekt> effekte, StartTrigger startTrigger, boolean eigene, NTeilnehmer andererChar, MitWaffe nichtMit)
	{
		for(KartenEffekt effekt : effekte)
		{
			if(effekt instanceof TriggerEffekt te)
			{
				if(te.startTrigger == startTrigger && eigene ? te.startSeite.eigeneOK : te.startSeite.gegnerOK)
				{
					if(te.bedingungen.stream().allMatch(bedingung -> bedingung.erfuellt(this, andererChar, nichtMit)))
					{
						te.triggere(this, andererChar);
					}
				}
			}
		}
	}

	public void beendeEffekte(EndTrigger trigger)
	{
		nCharakter.beendeEffekte(trigger);
		if(trigger == EndTrigger.NACH_ANGRIFF)
		{
			nWaffe(mit).beendeEffekte(EndTrigger.NACH_ANGRIFF);
		}
		else
		{
			if(nHauptwaffe != null)
				nHauptwaffe.beendeEffekte(trigger);
			if(nNebenwaffe != null)
				nNebenwaffe.beendeEffekte(trigger);
		}
		nAktion.beendeEffekte(trigger);
	}

	public void beendeEffekte(MitWaffe mitWaffe)
	{
		nWaffe(mitWaffe).beendeEffekte(EndTrigger.VERWENDET);
	}

	public void waffenTauschen()
	{
		NWaffe z = nHauptwaffe;
		nHauptwaffe = nNebenwaffe;
		nNebenwaffe = z;
		gesVorteil0 = -1;
	}

	public int kombinierterWert(Wirkungswert wert, MitWaffe mitWaffe, NAktion mitAktion)
	{
		return nCharakter.wert(wert) + nWaffe(mitWaffe).wert(wert) + mitAktion.wert(wert);
	}

	public boolean aktionGeht(Aktionskarte aktionskarte, MitWaffe mitWaffe, NTeilnehmer ziel1)
	{
		if(!aktiv() || nWaffe(mitWaffe) == null)
			return false;
		NAktion nAktion1 = new NAktion(aktionskarte);
		if(nWaffe(mitWaffe).aktiveEffekte().stream().anyMatch(effekt -> effekt.wirkung instanceof WNichtVerwendbar))
			return false;
		return kombinierterWert(Wirkungswert.MAGIE, mitWaffe, nAktion1) + magie >= 0;
	}

	public void setzeAktion(Aktionskarte aktionskarte, MitWaffe mit, NTeilnehmer ziel)
	{
		nAktion = new NAktion(aktionskarte);
		this.mit = mit;
		this.ziel = ziel;
		gibtMagieAus = gibtMagieAus(nAktion, mit, ziel);
	}

	public boolean aktionGehtIrgendwie(Aktionskarte aktionskarte, List<? extends NTeilnehmer> ziele)
	{
		for(NTeilnehmer ziel1 : ziele)
		{
			if(aktionGeht(aktionskarte, MitWaffe.HW, ziel1) || aktionGeht(aktionskarte, MitWaffe.NW, ziel1))
				return true;
		}
		return false;
	}

	public boolean gibtMagieAus(NAktion nAktion1, MitWaffe mitWaffe, NTeilnehmer ziel1)
	{
		if(kombinierterWert(Wirkungswert.MAGIE, mitWaffe, nAktion1) < 0)
			return true;
		return nAktion1.ladeMitMagie() && magie > 0;
	}

	public int kombinierterWert(Wirkungswert wert)
	{
		return nCharakter.wert(wert) + nWaffe(mit).wert(wert) + nAktion.wert(wert);
	}

	public void zahleMagie()
	{
		magie += kombinierterWert(Wirkungswert.MAGIE);
		if(nAktion.ladeMitMagie())
		{
			geladeneMagie = magie;
			magie = 0;
		}
	}

	public void erstelleMagieEffektOptionen()
	{
		magieEffektOptionen.addAll(nWaffe(mit).karte.effekte().stream().filter(effekt -> effekt instanceof MagieEffekt).map(effekt -> (MagieEffekt) effekt)
				.filter(effekt -> effekt.kannAktivieren(this, ziel)).map(effekt -> new MagieEffektOption(nWaffe(mit), effekt)).collect(Collectors.toList()));
	}

	public boolean magieEffektOptionenOK()
	{
		return magieEffektOptionen.stream().filter(option -> option.benutzen).mapToInt(option -> option.magieEffekt.magieKosten).sum() <= magie;
	}

	public void aktiviereMagieEffekte()
	{
		magieEffektOptionen.stream().filter(option -> option.benutzen).forEach(option -> option.magieEffekt.aktiviere(this, ziel));
	}

	public void berechneGes()
	{
		gesAktion = Math.max(kombinierterWert(Wirkungswert.GESCHWINDIGKEIT), 0);
	}

	public int gesVorteil()
	{
		return gesAktion - ziel.gesAktion;
	}

	public void berechneAnzahlAngriffe()
	{
		if(ziel == null)
		{
			anzahlAngriffe = 0;
			return;
		}
		anzahlAngriffe = 1;
		for(int i = 0; gesVorteil() >= i * e.gesBonusAbstand; i++)
		{
			if(i % 2 == 0)
			{
				gesBonusAngriff++;
				gesBonusMindestschaden++;
			}
			else
				anzahlAngriffe++;
		}
		anzahlAngriffe += kombinierterWert(Wirkungswert.EXTRAANGRIFFE);
		if(anzahlAngriffe < 1)
			anzahlAngriffe = 1;
		anzahlAngriffe = nCharakter.setzeangriffe(anzahlAngriffe);
		anzahlAngriffe = nWaffe(mit).setzeangriffe(anzahlAngriffe);
		anzahlAngriffe = nAktion.setzeangriffe(anzahlAngriffe);
	}

	public void angriff(int num)
	{
		if(num == 0)
			triggereEffekteMitAktion(StartTrigger.EINMAL_VOR);
		triggereEffekteMitAktion(StartTrigger.IMMER_VOR);
		int normalSchaden = Math.max(0, kombinierterWert(Wirkungswert.ANGRIFF) + gesBonusAngriff)
				- Math.max(0, ziel.kombinierterWert(Wirkungswert.VERTEIDIGUNG));
		int mindestschaden = kombinierterWert(Wirkungswert.MINDESTSCHADEN) + gesBonusMindestschaden
				- ziel.kombinierterWert(Wirkungswert.MINDESTSCHUTZ);
		ziel.leben -= Math.max(Math.max(normalSchaden, mindestschaden), 0);
		if(num == 0)
			triggereEffekteMitAktion(StartTrigger.EINMAL_NACH);
		triggereEffekteMitAktion(StartTrigger.IMMER_NACH);
		beendeEffekte(EndTrigger.NACH_ANGRIFF);
		ziel.beendeEffekte(EndTrigger.NACH_ANGEGRIFFEN);
		ziel.angegriffenVon.add(this);
	}

	@Override
	public int getGeladeneMagie()
	{
		return geladeneMagie;
	}

	@Override
	public int getGesAktion()
	{
		return gesAktion;
	}

	public int getAnzahlAngriffe()
	{
		return anzahlAngriffe;
	}

	@Override
	public List<NTeilnehmer> getAngegriffenVon()
	{
		return angegriffenVon;
	}

	@Override
	public MitWaffe getMit()
	{
		return mit;
	}

	public NTeilnehmer getZiel()
	{
		return ziel;
	}

	public boolean aktiv()
	{
		return leben > 0;
	}

	@Override
	public int getMagie()
	{
		return magie;
	}

	@Override
	public void setMagie(int magie)
	{
		this.magie = magie;
	}

	@Override
	public int getLeben()
	{
		return leben;
	}

	public NWaffe nWaffe(MitWaffe mitWaffe)
	{
		return switch(mitWaffe)
				{
					case HW -> nHauptwaffe;
					case NW -> nNebenwaffe;
					default -> throw new RuntimeException("Nur HW oder NW erlaubt");
				};
	}

	@Override
	public EffektZielKarte effektZielKarte(EffektZielKartentyp kartentyp)
	{
		return switch(kartentyp)
				{
					case CHARAKTER -> nCharakter;
					case WAFFE -> nWaffe(mit);
					case AKTION -> nAktion;
				};
	}

	@Override
	public EffektZielKarte effektZielKarte(EffektZielKartentyp kartentyp, MitWaffe zielWaffe)
	{
		return switch(kartentyp)
				{
					case CHARAKTER -> nCharakter;
					case WAFFE -> nWaffe(zielWaffe);
					case AKTION -> nAktion;
				};
	}

	public Charakterkarte getCharakterKarte()
	{
		return nCharakter.karte;
	}

	public Waffenkarte getWaffeKarte(MitWaffe mitWaffe)
	{
		switch(mitWaffe)
		{
			case HW ->
			{
				if(nHauptwaffe == null)
					return null;
				return nHauptwaffe.karte;
			}
			case NW ->
			{
				if(nNebenwaffe == null)
					return null;
				return nNebenwaffe.karte;
			}
			default -> throw new RuntimeException("Nur HW oder NW erlaubt");
		}
	}

	public Aktionskarte getAktionKarte()
	{
		return nAktion != null ? nAktion.karte : null;
	}

	@Override
	public boolean isGibtMagieAus()
	{
		return gibtMagieAus;
	}
}