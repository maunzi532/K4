package kampf;

import effekt.*;
import java.util.*;
import karten.*;
import kartenset.*;
import main.*;

public class NKampf
{
	private Einstellungen e;
	private List<NTeilnehmer> spieler0;
	private List<NTeilnehmer> gegner0;
	private List<NTeilnehmer> spieler;
	private List<NTeilnehmer> gegner;
	private List<NTeilnehmer> alle;
	private List<NTeilnehmer> sortiert;
	private Kartenstapel<Aktionskarte> aktionsKartenstapel;
	private List<Aktionskarte> aktionenOptionen;

	public NKampf(Einstellungen e, List<NTeilnehmer> spieler0, List<NTeilnehmer> gegner0,
			Kartenstapel<Aktionskarte> aktionsKartenstapel)
	{
		this.e = e;
		this.spieler0 = spieler0;
		this.gegner0 = gegner0;
		this.aktionsKartenstapel = aktionsKartenstapel;
		aktionenOptionen = new ArrayList<>();
	}

	public List<NTeilnehmer> getAlleSpieler()
	{
		return spieler0;
	}

	public List<NTeilnehmer> getAlleGegner()
	{
		return gegner0;
	}

	public void start()
	{
		spieler = new ArrayList<>(spieler0);
		gegner = new ArrayList<>(gegner0);
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
			if(n.nHauptwaffe != null)
				n.triggereEffekte1va(n.getWaffeKarte(W.HW).effekte(), StartTrigger.VERWENDET, W.HW);
			if(n.nNebenwaffe != null)
				n.triggereEffekte1va(n.getWaffeKarte(W.NW).effekte(), StartTrigger.VERWENDET, W.NW);
			n.triggereEffekte(StartTrigger.ANFANG, false);
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
				while(aktionenOptionen.size() < e.basisAktionenOptionen + e.extraAktionenOptionenProSpieler * spieler0.size())
				{
					aktionenOptionen.add(aktionsKartenstapel.erhalteKarte().orElseThrow());
				}
				if(aktionskartenOK())
					break;
				else
					kartenZurueck();
			}
		}
	}

	public void kartenZurueck()
	{
		if(aktionsKartenstapel != null)
			aktionenOptionen.forEach(aktionsKartenstapel::ablage);
		aktionenOptionen.clear();
	}

	public boolean aktionskartenOK()
	{
		return aktionskartenOKR(new ArrayList<>(), 0);
	}

	private boolean aktionskartenOKR(List<Integer> verwendetNum, int spielerNum)
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

	public boolean aktionskarte(NTeilnehmer n, Aktionskarte aktionskarte, W mit, NTeilnehmer ziel)
	{
		if(n.aktionGeht(aktionskarte, mit, ziel))
		{
			n.setzeAktion(aktionskarte, mit, ziel);
			aktionenOptionen.remove(aktionskarte);
			return true;
		}
		return false;
	}

	public void gegnerAktionskarten(Random r)
	{
		for(NTeilnehmer n : gegner)
		{
			boolean ausgeben = n.getMagie() >= 5;
			NTeilnehmer ziel = spieler.get(spieler.size() > 1 ? r.nextInt(spieler.size()) : 0);
			n.setzeAktion(aktionsKartenstapel.durchsucheAlle(aktionskarte -> n.aktionGeht(aktionskarte, W.HW, ziel) &&
					(!ausgeben || aktionskarte.getMagieMod() < 0 || aktionskarte.isLadeMitMagie())).orElseThrow(), W.HW, ziel);
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
			n.triggereEffekte(StartTrigger.GES_VOR, true);
		}
		for(NTeilnehmer n : alle)
		{
			n.berechneGes();
		}
		sortiert = new ArrayList<>(alle);
		sortiert.sort(Comparator.comparing(NTeilnehmer::getGesAktion).thenComparing(e -> e.gesVorteil0).thenComparing(e -> e.index).reversed());
		for(NTeilnehmer n : alle)
		{
			n.triggereEffekte(StartTrigger.GES_NACH, true);
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
			n.triggereEffekte(StartTrigger.ZUGENDE, true);
		}
		if(aktionsKartenstapel != null)
		{
			for(NTeilnehmer n : alle)
			{
				if(n.getAktionKarte() != null)
					aktionsKartenstapel.ablage(n.getAktionKarte());
			}
		}
		spieler.removeIf(e -> !e.aktiv());
		gegner.removeIf(e -> !e.aktiv());
		if(spieler.isEmpty())
		{
			kartenZurueck();
			return -1;
		}
		if(gegner.isEmpty())
		{
			kartenZurueck();
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