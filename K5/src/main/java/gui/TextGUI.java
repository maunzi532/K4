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
		TextGUI t = new TextGUI();
		t.start();
		while(true)
		{
			if(t.loop())
				break;
		}
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
	private Random rng;

	private int aktuellerSpielerNum;

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
		rng = new Random();
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
			Klasse k = Klassen.valueOf(sca.nextLine().toUpperCase());
			klassenOptionen.remove(k);
			vorrat.erstelleHeld(Held2.initial(k, klassen, waffenKartenstapel, e), i);
		}
	}

	public boolean loop()
	{
		Spieler s = vorrat.spieler.get(aktuellerSpielerNum);
		switch(s.status)
		{
			case MAP -> mapText(s);
			case HAENDLER_LOBBY -> {}
			case GEGNER_LOBBY -> {}
			case HAENDLER -> {}
			case KAMPF -> {}
		}
		String input = sca.nextLine();
		if("e".equalsIgnoreCase(input))
		{
			return true;
		}
		else if("h".equalsIgnoreCase(input))
		{
			hilfe();
		}
		else if(input.length() == 1 && input.chars().allMatch(c -> c >= '0' && c <= '9'))
		{
			int num = Integer.parseInt(input);
			if(num >= 1 && num <= e.anzahlSpieler)
			{
				aktuellerSpielerNum = num - 1;
				System.out.println("Aktueller Spieler: Spieler " + num);
			}
		}
		else
		{
			switch(s.status)
			{
				case MAP -> mapAktion(input, s);
				case HAENDLER_LOBBY -> {}
				case GEGNER_LOBBY -> {}
				case HAENDLER -> {}
				case KAMPF -> {}
			}
		}
		return false;
	}

	private void hilfe()
	{
		System.out.println("Hilfetext");
	}

	private void mapText(Spieler s)
	{
		//lokale map ansicht
		KartenKoordinaten ka = s.spielfigur().getFA();
		System.out.println(mapBild.erstelleTextBild(KartenKoordinaten.add(ka, -1, -1), KartenKoordinaten.add(ka, 1, 1)));
	}

	private void mapAktion(String input, Spieler s)
	{
		String[] inputA = input.split(" ");
		switch(inputA[0].toLowerCase())
		{
			case "v" ->
					{
						int n = inputA.length > 1 ? Integer.parseInt(inputA[1]) : 1;
						s.spielfigur().geheZu(FeldKoordinaten.add(s.spielfigur().getFA(), -n, 0), rng::nextBoolean);
					}
		}
		while(s.spielfigur().inBewegung())
		{
			s.spielfigur().bewege();
		}
		Optional<FeldKoordinaten> f = s.spielfigur().kannForschen();
		f.ifPresent(f1 -> vorrat.map.forsche(f1, mapKartenstapel));
	}
}