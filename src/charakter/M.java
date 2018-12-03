package charakter;

import dungeonmap.*;
import java.util.*;
import karten.*;
import kartenset.*;

public class M
{
	public Einstellungen e;
	public Kartenstapel<MapKarte> mapStapel;
	public Kartenset<Charakterkarte> klassenSet;
	public Kartenstapel<Charakterkarte> gegnerStapel;
	public Kartenstapel<Waffenkarte> waffenStapel;
	public Kartenstapel<Aktionskarte> aktionenStapel;
	public DungeonMap dungeonMap;
	public List<HeldMap> spieler;
	public List<Spielfigur> figuren;
	public int spielerAktuell;

	public M(Einstellungen e, MittelMapKartenset mittelMapSet, AKartenset<MapKarte> mapSet,
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
		figuren = new ArrayList<>();
		dungeonMap = new DungeonMap(e.yhMap, e.xmMap, e.mittelBossOrte);
		dungeonMap.erstelleMittelWeg(mittelMapSet);
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
			spieler.add(new HeldMap(i, kl.get(), klassenSet, waffenStapel));
			figuren.add(dungeonMap.erstelleSpielfigur(i));
		}
	}

	public void klassenAuswahl(String... kl0)
	{
		List<Klasse> klassen = new ArrayList<>(Arrays.asList(Klasse.values()));
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			int i1 = i;
			spieler.add(new HeldMap(i, klassen.stream().filter(e1 -> e1.name().equalsIgnoreCase(kl0[i1])).findFirst().orElseThrow(), klassenSet, waffenStapel));
			figuren.add(dungeonMap.erstelleSpielfigur(i));
		}
	}

	public void zielAngeben(MapBild mapBild)
	{
		Scanner sca = new Scanner(System.in);
		boolean ok = false;
		while(!ok)
		{
			System.out.println("Zielkoordinaten y, x (Aktuell y=" + figuren.get(spielerAktuell).getY()
					+ " x=" + figuren.get(spielerAktuell).getX() + ")");
			int y = sca.nextInt();
			int x = sca.nextInt();
			ok = figuren.get(spielerAktuell).geheZu(y, x);
		}
		System.out.println(mapBild.erstelleTextBild(figuren, spielerAktuell));
		while(figuren.get(spielerAktuell).nochBewegen())
		{
			try
			{
				Thread.sleep(100);
			}catch(InterruptedException e1)
			{
				throw new RuntimeException(e1);
			}
			gehe();
			System.out.println(mapBild.erstelleTextBild(figuren, spielerAktuell));
		}
	}

	public void gehe()
	{
		for(Spielfigur sf : figuren)
		{
			sf.bewege();
		}
	}
}