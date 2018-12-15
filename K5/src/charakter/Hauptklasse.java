package charakter;

import dungeonmap.*;
import java.util.*;
import karten.*;
import kartenset.*;

public class Hauptklasse
{
	public Einstellungen e;
	public Kartenstapel<MapKarte> mapStapel;
	public Kartenset<Charakterkarte> klassenSet;
	public Kartenstapel<Charakterkarte> gegnerStapel;
	public Kartenstapel<Waffenkarte> waffenStapel;
	public Kartenstapel<Aktionskarte> aktionenStapel;
	public DungeonMap dungeonMap;
	public List<HeldSpieler> spieler;
	public int spielerAktuell;

	public Hauptklasse(Einstellungen e, MittelMapKartenset mittelMapSet, AKartenset<MapKarte> mapSet,
			Kartenset<Charakterkarte> klassenSet, Kartenset<Charakterkarte> gegnerSet,
			Kartenset<Waffenkarte> waffenSet, Kartenset<Aktionskarte> aktionenSet)
	{
		this.e = e;
		this.klassenSet = klassenSet;
		mapStapel = new Kartenstapel<>(mapSet.karten);
		gegnerStapel = new Kartenstapel<>(gegnerSet);
		waffenStapel = new Kartenstapel<>(waffenSet);
		aktionenStapel = new Kartenstapel<>(gegnerSet);
		spieler = new ArrayList<>();
		dungeonMap = new DungeonMap(e.yhMap, e.xmMap, e.immerWegW, e.mittelBossOrte);
		dungeonMap.erstelleMittelWeg(mittelMapSet);
	}

	public HeldSpieler heldSpielerAktuell()
	{
		return spieler.get(spielerAktuell);
	}

	public void klassenAuswahl()
	{
		Scanner sca = new Scanner(System.in);
		List<Klasse> klassen = new ArrayList<>(Arrays.asList(Klasse.values()));
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			Optional<Klasse> kl = Optional.empty();
			while(kl.isEmpty())
			{
				System.out.println(klassen);
				System.out.println("Wähle Klasse durch ersten Buchstaben");
				String input = sca.nextLine();
				kl = klassen.stream().filter(e -> e.name().equalsIgnoreCase(input)).findFirst();
			}
			klassen.remove(kl.get());
			spieler.add(new HeldSpieler(new HeldMap(i, kl.get(), klassenSet, waffenStapel), dungeonMap));
		}
	}

	public void klassenAuswahl(String... kl0)
	{
		List<Klasse> klassen = new ArrayList<>(Arrays.asList(Klasse.values()));
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			int i1 = i;
			spieler.add(new HeldSpieler(new HeldMap(i, klassen.stream()
					.filter(e1 -> e1.name().equalsIgnoreCase(kl0[i1])).findFirst().orElseThrow(),
					klassenSet, waffenStapel), dungeonMap));
		}
	}

	public void gehe()
	{
		for(HeldSpieler spieler : spieler)
		{
			spieler.spielfigur.bewege();
		}
	}
}