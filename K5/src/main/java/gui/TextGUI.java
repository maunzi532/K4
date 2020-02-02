package gui;

import dungeonmap.karte.*;
import dungeonmap.map.*;
import dungeonmap.mapsets.*;
import effektkarten.kartebild.*;
import effektkarten.karten.*;
import effektkarten.sets.*;
import java.util.*;
import java.util.stream.*;
import main.*;
import map2.*;
import stapelkarten.*;

public class TextGUI
{
	public static void main(String[] args)
	{
		new TextGUI().start();
	}

	private Scanner sca;
	private Kartenset<Aktionskarte> aktionen;
	private Kartenset<Waffenkarte> waffen;
	private Kartenset<Gegner> gegner;
	private Kartenset<Charakterkarte> klassen;
	private KarteBild3 karteBild;
	private Kartenstapel<Aktionskarte> aktionenKartenstapel;
	private Kartenstapel<Waffenkarte> waffenKartenstapel;
	private Kartenstapel<Gegner> gegnerKartenstapel;
	private Kartenstapel<MapKarte> mapKartenstapel;
	private Einstellungen e;
	private Vorrat vorrat;
	private MapBild mapBild;

	public void start()
	{
		sca = new Scanner(System.in);
		aktionen = new SetV2Aktionen().fertig();
		waffen = new SetV2Waffen().fertig();
		gegner = new SetV2Gegner().fertig();
		klassen = new SetV2Klassen().fertig();
		karteBild = new KarteBild3();
		aktionenKartenstapel = aktionen.kartenstapel();
		waffenKartenstapel = waffen.kartenstapel();
		gegnerKartenstapel = gegner.kartenstapel();
		mapKartenstapel = new MischKartenstapel<>(new SetV2MapKarten().fertig());
		System.out.print("Anzahl Spieler: ");
		e = Einstellungen.lies("Einstellungen", sca.nextLine() + " Spieler");
		vorrat = new Vorrat(e);
		vorrat.map.erstelleMittelWeg(new MittelMapKartenset(new SetV2MittelMapKarten().fertig()), Collections::shuffle);
		mapBild = new MapBild(vorrat.map);
		vorrat.erstelleSpielfiguren();
		System.out.println(mapBild.mapAlsText());
		List<Klasse> klassenOptionen = new ArrayList<>(Arrays.asList(Klassen.values()));
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			//erstelle Held
			System.out.println("Spieler " + (i + 1));
			System.out.println("Klassen: " + klassenOptionen.stream()
					.map(k -> k.klassenName() + " (" + k.toString() + ")").collect(Collectors.joining(", ")));
			System.out.print("Auswahl: ");
			Klasse k = Klassen.valueOf(sca.nextLine());
			klassenOptionen.remove(k);
			vorrat.erstelleHeld(Held2.initial(k, klassen, waffenKartenstapel, e), i);
		}
	}
}