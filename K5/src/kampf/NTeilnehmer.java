package kampf;

import basiskarte.*;
import effekt.*;
import effekt.wirkung.*;
import java.util.*;
import karten.*;

public class NTeilnehmer
{
	//private static final int[] multiAngriffLimits = new int[]{5, 15, 30};
	private static final int gesVorteilBonusLimit = 5;

	public final int spielerNummer;
	//Charakter
	private NCharakter nCharakter;

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
	private W mit;
	private NTeilnehmer ziel;

	//Werte für diesen Zug
	private int geladeneMagie;
	private boolean gibtMagieAus;
	private int gesAktion;
	private int gesBonusAngriff;
	private int anzahlAngriffe;
	private List<NTeilnehmer> angegriffenVon;

	public NTeilnehmer(int spielerNummer, Charakterkarte charakterkarte, Waffenkarte hauptwafffe, Waffenkarte nebenwaffe)
	{
		this(spielerNummer, charakterkarte, hauptwafffe, nebenwaffe, charakterkarte.getLeben() * 3);
	}

	public NTeilnehmer(int spielerNummer, Charakterkarte charakterkarte, Waffenkarte hauptwafffe, Waffenkarte nebenwaffe, int leben)
	{
		this.spielerNummer = spielerNummer;
		nCharakter = new NCharakter(charakterkarte);
		nHauptwaffe = NWaffe.von(hauptwafffe);
		nNebenwaffe = NWaffe.von(nebenwaffe);
		magie = 0;
		this.leben = leben;
		angegriffenVon = new ArrayList<>();
	}

	public void beginneZug()
	{
		nAktion = null;
		mit = null;
		ziel = null;
		geladeneMagie = 0;
		gibtMagieAus = false;
		gesAktion = 0;
		gesBonusAngriff = 0;
		anzahlAngriffe = 0;
		angegriffenVon.clear();
	}

	public void triggereEffekte(StartTrigger startTrigger, boolean aktionAktiv)
	{
		triggereEffekte1(getCharakterKarte().effekte(), startTrigger);
		if(aktionAktiv)
		{
			triggereEffekte1(getWaffeKarte(mit).effekte(), startTrigger);
			triggereEffekte1(getAktionKarte().effekte(), startTrigger);
			if(ziel != null)
			{
				ziel.triggereEffekte2(getCharakterKarte().effekte(), startTrigger, this, null);
				ziel.triggereEffekte2(getWaffeKarte(mit).effekte(), startTrigger, this, null);
				ziel.triggereEffekte2(getAktionKarte().effekte(), startTrigger, this, null);
			}
		}
		else
		{
			if(nHauptwaffe != null)
				triggereEffekte1va(getWaffeKarte(W.HW).effekte(), startTrigger, W.HW);
			if(nNebenwaffe != null)
				triggereEffekte1va(getWaffeKarte(W.NW).effekte(), startTrigger, W.NW);
		}
	}

	public void triggereEffekte1(List<KartenEffekt> effekte, StartTrigger startTrigger)
	{
		triggereEffekte2(effekte, startTrigger, null, null);
	}

	public void triggereEffekte1va(List<KartenEffekt> effekte, StartTrigger startTrigger, W mit1)
	{
		triggereEffekte2(effekte, startTrigger, null, mit1);
	}

	public void triggereEffekte2(List<KartenEffekt> effekte, StartTrigger startTrigger, NTeilnehmer ang, W mit1)
	{
		boolean eigene;
		NTeilnehmer ziel1;
		W mit2 = mit1 == null ? mit : mit1;
		if(ang == null)
		{
			eigene = true;
			ziel1 = ziel;
		}
		else
		{
			eigene = false;
			ziel1 = ang;
		}
		for(KartenEffekt e : effekte)
		{
			if(e instanceof TriggerEffekt)
			{
				TriggerEffekt te = (TriggerEffekt) e;
				if(te.getStartTrigger() == startTrigger && eigene ? te.getStartSeite().eigeneOK : te.getStartSeite().gegnerOK)
				{
					if(te.bedingungen.stream().allMatch(n -> n.ok(this, ziel1, mit2)))
					{
						te.triggere(this, ziel1, mit2);
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
			nWaffe(mit).beendeEffekte(trigger);
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

	public void beendeEffekte(W w)
	{
		nWaffe(w).beendeEffekte(EndTrigger.VERWENDET);
	}

	public boolean aktionGeht(Aktionskarte aktionskarte, W mit, NTeilnehmer ziel)
	{
		NAktion nAktion1 = new NAktion(aktionskarte);
		if(nWaffe(mit).aktiveEffekte().stream().anyMatch(e -> e.wirkung instanceof WNichtVerwendbar))
			return false;
		return nAktion1.magieAenderung() + nCharakter.magieAenderung() + nWaffe(mit).magieAenderung() + magie >= 0;
	}

	public boolean gibtMagieAus(NAktion nAktion1, W mit, NTeilnehmer ziel)
	{
		if(nAktion1.magieAenderung() + nCharakter.magieAenderung() + nWaffe(mit).magieAenderung() < 0)
			return true;
		return nAktion1.ladeMitMagie() && magie > 0;
	}

	public void setzeAktion(Aktionskarte aktionskarte, W mit, NTeilnehmer ziel)
	{
		nAktion = new NAktion(aktionskarte);
		this.mit = mit;
		this.ziel = ziel;
		gibtMagieAus = gibtMagieAus(nAktion, mit, ziel);
	}

	public void zahleMagie()
	{
		magie += nCharakter.magieAenderung() + nWaffe(mit).magieAenderung() + nAktion.magieAenderung();
		if(nAktion.ladeMitMagie())
		{
			geladeneMagie = magie;
			magie = 0;
		}
	}

	public void berechneGes()
	{
		gesAktion = Math.max(nCharakter.geschwindigkeit() + nWaffe(mit).geschwindigkeit() + nAktion.geschwindigkeit(), 0);
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
		for(int i = 0; gesVorteil() >= i * 5; i++)
		{
			if(i % 2 == 0)
				gesBonusAngriff++;
			else
				anzahlAngriffe++;
		}
		/*for(int i = 0; i < multiAngriffLimits.length; i++)
		{
			if(gesAktion - ziel.gesAktion < multiAngriffLimits[i])
				break;
			anzahlAngriffe++;
		}*/
		anzahlAngriffe += nCharakter.extraangriffe() + nWaffe(mit).extraangriffe() + nAktion.extraangriffe();
		if(anzahlAngriffe < 1)
			anzahlAngriffe = 1;
		anzahlAngriffe = nCharakter.setzeangriffe(anzahlAngriffe);
		anzahlAngriffe = nWaffe(mit).setzeangriffe(anzahlAngriffe);
		anzahlAngriffe = nAktion.setzeangriffe(anzahlAngriffe);
	}

	public void angriff(int num)
	{
		if(num == 0)
			triggereEffekte(StartTrigger.EINMAL_VOR, true);
		triggereEffekte(StartTrigger.IMMER_VOR, true);
		int normalSchaden = Math.max(nCharakter.angriff() + nWaffe(mit).angriff() + nAktion.angriff() + gesBonusAngriff, 0)
				- Math.max(ziel.nCharakter.verteidigung() + ziel.nWaffe(ziel.mit).verteidigung() + ziel.nAktion.verteidigung(), 0);
		int mindestschaden = nCharakter.mindestschaden() + nWaffe(mit).mindestschaden() + nAktion.mindestschaden()
				- ziel.nCharakter.mindestschutz() - ziel.nWaffe(ziel.mit).mindestschutz() - ziel.nAktion.mindestschutz();
		ziel.leben -= Math.max(Math.max(normalSchaden, mindestschaden), 0);
		if(num == 0)
			triggereEffekte(StartTrigger.EINMAL_NACH, true);
		triggereEffekte(StartTrigger.IMMER_NACH, true);
		beendeEffekte(EndTrigger.NACH_ANGRIFF);
		ziel.beendeEffekte(EndTrigger.NACH_ANGEGRIFFEN);
		ziel.angegriffenVon.add(this);
	}

	public int getGeladeneMagie()
	{
		return geladeneMagie;
	}

	public int getGesAktion()
	{
		return gesAktion;
	}

	public int getAnzahlAngriffe()
	{
		return anzahlAngriffe;
	}

	public List<NTeilnehmer> getAngegriffenVon()
	{
		return angegriffenVon;
	}

	public W getMit()
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

	public int getMagie()
	{
		return magie;
	}

	public void setMagie(int magie)
	{
		this.magie = magie;
	}

	public int getLeben()
	{
		return leben;
	}

	public NCharakter nCharakter()
	{
		return nCharakter;
	}

	public NWaffe nWaffe(W w)
	{
		return switch(w)
		{
			case HW -> nHauptwaffe;
			case NW -> nNebenwaffe;
			default -> throw new RuntimeException();
		};
	}

	public NAktion nAktion()
	{
		return nAktion;
	}

	public Charakterkarte getCharakterKarte()
	{
		return nCharakter.karte;
	}

	public Waffenkarte getWaffeKarte(W w)
	{
		switch(w)
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
			default -> throw new RuntimeException();
		}
	}

	public Aktionskarte getAktionKarte()
	{
		return nAktion != null ? nAktion.karte : null;
	}

	public boolean isGibtMagieAus()
	{
		return gibtMagieAus;
	}
}