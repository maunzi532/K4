package kampf;

import effektkarten.effekte.effekt.StartTrigger;
import effektkarten.effekte.ziel.EndTrigger;
import effektkarten.effekte.ziel.MitWaffe;
import java.util.*;
import effektkarten.karten.*;
import java.util.function.*;
import main.*;
import stapelkarten.*;

public final class NKampf
{
	private final Einstellungen e;
	private final List<NTeilnehmer> alleSpieler;
	private final List<NTeilnehmer> alleGegner;
	private List<NTeilnehmer> spieler;
	private List<NTeilnehmer> gegner;
	private List<NTeilnehmer> alle;
	private List<NTeilnehmer> sortiert;
	private final Kartenstapel<Aktionskarte> aktionsKartenstapel;
	private final List<Aktionskarte> aktionenOptionen;

	public NKampf(Einstellungen e, List<NTeilnehmer> alleSpieler, List<NTeilnehmer> alleGegner,
			Kartenstapel<Aktionskarte> aktionsKartenstapel)
	{
		this.e = e;
		this.alleSpieler = alleSpieler;
		this.alleGegner = alleGegner;
		this.aktionsKartenstapel = aktionsKartenstapel;
		aktionenOptionen = new ArrayList<>();
	}

	public List<NTeilnehmer> getAlleSpieler()
	{
		return alleSpieler;
	}

	public List<NTeilnehmer> getAlleGegner()
	{
		return alleGegner;
	}

	public void start()
	{
		spieler = new ArrayList<>(alleSpieler);
		gegner = new ArrayList<>(alleGegner);
		for(int i = 0; i < spieler.size(); i++)
		{
			spieler.get(i).index = i;
		}
		for(int i = 0; i < gegner.size(); i++)
		{
			gegner.get(i).index = i;
		}
		alle = new ArrayList<>();
		alle.addAll(spieler);
		alle.addAll(gegner);
		for(NTeilnehmer n : spieler)
			n.gesVorteil0 = 1;
		//Waffen tauschen möglich
	}

	public void anfangstrigger()
	{
		for(NTeilnehmer n : alle)
		{
			n.triggereEffekteVorAktion(StartTrigger.VERWENDET);
			n.triggereEffekteVorAktion(StartTrigger.ANFANG);
		}
	}

	public void beginneZug()
	{
		for(NTeilnehmer n : alle)
		{
			n.beginneZug();
		}
		if(aktionsKartenstapel != null)
		{
			int anzahlVersuche = aktionsKartenstapel.effektiveDeckKartenAnzahl();
			for(int i = 0; i < anzahlVersuche; i++)
			{
				while(aktionenOptionen.size() < e.basisAktionenOptionen + e.extraAktionenOptionenProSpieler * alleSpieler
						.size())
				{
					aktionenOptionen.add(aktionsKartenstapel.erhalteKarte().orElseThrow());
				}
				if(aktionskartenOK())
					break;
				else
					aktionskartenZurueck();
			}
		}
	}

	public void aktionskartenZurueck()
	{
		if(aktionsKartenstapel != null)
			aktionenOptionen.forEach(aktionsKartenstapel::ablage);
		aktionenOptionen.clear();
	}

	public boolean aktionskartenOK()
	{
		return aktionskartenOKR(new ArrayList<>(), 0);
	}

	private boolean aktionskartenOKR(List<? super Integer> verwendetNum, int spielerNum)
	{
		if(spielerNum >= spieler.size())
			return true;
		boolean ok = false;
		NTeilnehmer n = spieler.get(spielerNum);
		for(int i = 0; i < aktionenOptionen.size(); i++)
		{
			if(!verwendetNum.contains(i) && n.aktionGehtIrgendwie(aktionenOptionen.get(i), gegner))
			{
				verwendetNum.add(i);
				boolean re = aktionskartenOKR(verwendetNum, spielerNum + 1);
				verwendetNum.remove(verwendetNum.size() - 1);
				if(re)
					ok = true;
			}
		}
		return ok;
	}

	public List<Aktionskarte> aktionenOptionen()
	{
		return aktionenOptionen;
	}

	public boolean aktionskarte(NTeilnehmer n, Aktionskarte aktionskarte, MitWaffe mit, NTeilnehmer ziel)
	{
		if(n.aktionGeht(aktionskarte, mit, ziel))
		{
			n.setzeAktion(aktionskarte, mit, ziel);
			aktionenOptionen.remove(aktionskarte);
			return true;
		}
		return false;
	}

	public void gegnerAktionskarten(IntBinaryOperator zielSpielerAuswahl)
	{
		for(int i = 0; i < gegner.size(); i++)
		{
			NTeilnehmer n = gegner.get(i);
			boolean ausgeben = n.getMagie() >= 5;
			NTeilnehmer ziel = spieler.get(zielSpielerAuswahl.applyAsInt(i, spieler.size()));
			n.setzeAktion(aktionsKartenstapel.durchsucheAlle(aktionskarte -> n.aktionGeht(aktionskarte, MitWaffe.HW, ziel) &&
							(!ausgeben || aktionskarte.magieMod() < 0 || aktionskarte.ladeMitMagie()))
							.orElseThrow(), MitWaffe.HW, ziel);
		}
	}

	public void magieZahlen()
	{
		for(NTeilnehmer n : alle)
		{
			n.zahleMagie();
		}
		for(NTeilnehmer n : spieler)
		{
			n.erstelleMagieEffektOptionen();
		}
	}

	public boolean magieEffektOptionenOK()
	{
		return alle.stream().allMatch(NTeilnehmer::magieEffektOptionenOK);
	}

	public void zugV()
	{
		for(NTeilnehmer n : alle)
		{
			n.aktiviereMagieEffekte();
		}
		for(NTeilnehmer n : alle)
		{
			n.triggereEffekteMitAktion(StartTrigger.GES_VOR);
		}
		for(NTeilnehmer n : alle)
		{
			n.berechneGes();
		}
		sortiert = new ArrayList<>(alle);
		sortiert.sort(Comparator.comparing(NTeilnehmer::getGesAktion).thenComparing(e1 -> e1.gesVorteil0).thenComparing(e2 -> e2.index).reversed());
		for(NTeilnehmer n : alle)
		{
			n.triggereEffekteMitAktion(StartTrigger.GES_NACH);
		}
		for(NTeilnehmer n : alle)
		{
			n.berechneAnzahlAngriffe();
		}
	}

	public void angriffe()
	{
		int maxAngriffe = sortiert.stream().mapToInt(NTeilnehmer::getAnzahlAngriffe).max().orElse(0);
		for(int a = 0; a < maxAngriffe; a++)
		{
			for(NTeilnehmer n : sortiert)
			{
				if(n.getAnzahlAngriffe() > a && n.aktiv() && n.getZiel().aktiv())
				{
					n.angriff(a);
				}
			}
		}
	}

	public int beendeZug()
	{
		for(NTeilnehmer n : alle)
		{
			n.triggereEffekteMitAktion(StartTrigger.ZUGENDE);
		}
		if(aktionsKartenstapel != null)
		{
			for(NTeilnehmer n : alle)
			{
				if(n.getAktionKarte() != null)
					aktionsKartenstapel.ablage(n.getAktionKarte());
			}
		}
		spieler.removeIf(spieler1 -> !spieler1.aktiv());
		gegner.removeIf(gegner1 -> !gegner1.aktiv());
		if(spieler.isEmpty())
		{
			aktionskartenZurueck();
			return -1;
		}
		if(gegner.isEmpty())
		{
			aktionskartenZurueck();
			return 1;
		}
		alle = new ArrayList<>();
		alle.addAll(spieler);
		alle.addAll(gegner);
		for(NTeilnehmer n : alle)
		{
			n.beendeEffekte(EndTrigger.NACH_ANGEGRIFFEN);
			n.beendeEffekte(EndTrigger.ZUG_ENDE);
		}
		return 0;
	}

	public void aktionskarteAblegen(Aktionskarte aktionskarte)
	{
		if(aktionsKartenstapel != null)
			aktionsKartenstapel.ablage(aktionskarte);
		aktionenOptionen.remove(aktionskarte);
	}

	/*
	Kampf beginnen
	1 Evtl. Waffen wechseln / Reihenfolge anpassen > INPUT
	2 VerwendetW aktualisieren (Wenn Spieler)
	3 Effekte mit Verwendungstrigger / Anfangstrigger

	//Waffen wechseln
	//1 Entferne NWaffe Objekte
	//2 Beende HW Effekte
	//3 Neue NWaffe Objekte hinzu
	//Nach Anfang
	//4 VerwendetW aktualisieren (Wenn Spieler)
	//5 Effekte mit Verwendungstrigger

	Zug beginnen
	1 Aktionskarten ziehen
	2 Nicht verwendbare Aktionskarten austauschen
	3 Aktionskarten auswählen > INPUT
	4 Gegner ziehen Aktionskarten

	Zug durchführen 1
	1 Ändere Magie
	2 Lade Magie (Magiehaltiger Angriff)
	3 Benutze Magieeffekte / Magie zahlen > INPUT

	Zug durchführen 2
	1 Effekte mit Starttrigger vor Ges. Berechnung
	2 Ges. Berechnung
	3 Ermitteln wer wann drankommt
	4 Effekte mit Starttrigger nach Ges. Berechnung
	5 Anzahl Angriffe ermitteln
	6 Extraangriffe dazu (Schnellschlag, Ast, Extraangriff)
	7 Angriffsanzahl setzen (Doppelschlag)

	Angriff durchführen
	1 Vorangriffseffekte durchführen
	2 Schaden ermitteln / Mindestschaden ermitteln / Leben abziehen
	3 Nachangriffseffekte durchführen
	4 Angriffsendtrigger

	Zug beenden
	1 Zugende Effekte durchführen
	2 Effekte ticken / beenden
	3 Aktionskarten ablegen > INPUT
	 */
}