package gui;

import dungeonmap.karte.*;
import dungeonmap.map.MittelMapKartenset;
import dungeonmap.mapsets.SetV2MapKarten;
import dungeonmap.mapsets.SetV2MittelMapKarten;
import effektkarten.textbild.KarteBild3;
import effektkarten.karten.*;
import effektkarten.sets.*;
import gui.text.MapBild;
import gui.text.MapBild2Daten;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import main.Einstellungen;
import map2.*;
import stapelkarten.Kartenstapel;
import stapelkarten.MischKartenstapel;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class TextGUI
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
	private MapBild2Daten mapBild2Daten;
	private Random rng;
	private int aktuellerSpielerNum;

	public void start()
	{
		sca = new Scanner(System.in, StandardCharsets.UTF_8);
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
		e = Einstellungen.lies("Einstellungen", sca.nextLine() + "_Spieler");
		vorrat = new Vorrat(e);
		vorrat.map.erstelleMittelWeg(new MittelMapKartenset(new SetV2MittelMapKarten().fertig()), Collections::shuffle);
		mapBild = new MapBild();
		mapBild2Daten = new MapBild2Daten(2, 5);
		rng = new Random();
		vorrat.erstelleSpielfiguren();
		System.out.println(mapBild.erstelleKleinBild(mapBild.felder(vorrat.map, List.of())));
		List<Klasse> klassenOptionen = new ArrayList<>(Arrays.asList(Klassen.values()));
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			System.out.println("Spieler " + (i + 1));
			System.out.println("Klassen: " + klassenOptionen.stream()
					.map(klasse -> klasse.klassenName() + " (" + klasse + ')').collect(Collectors.joining(", ")));
			System.out.print("Auswahl: ");
			Klasse klasse = Klassen.valueOf(sca.nextLine().toUpperCase(Locale.GERMAN));
			klassenOptionen.remove(klasse);
			vorrat.erstelleHeld(Held2.initial(klasse, klassen, waffenKartenstapel, e), i);
		}
	}

	public boolean loop()
	{
		Spieler spieler = vorrat.spielerListe.get(aktuellerSpielerNum);
		switch(spieler.status)
		{
			case MAP -> mapText(spieler);
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
			switch(spieler.status)
			{
				case MAP -> mapAktion(input, spieler);
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

	private void mapText(Spieler spieler)
	{
		System.out.println(mapBild.erstelleTextBild(mapBild2Daten, mapBild.felder(vorrat.map, 6, 6, spieler.spielfigur().getFA(), vorrat.spielerListe)));
	}

	private void mapAktion(String input, Spieler spieler)
	{
		String[] inputA = input.split(" ");
		int cursor = 0;
		FeldPosition zf = spieler.spielfigur().getFA();
		while(cursor < inputA.length)
		{
			String c1 = inputA[cursor].toLowerCase(Locale.GERMAN);
			int n;
			switch(c1)
			{
				case "v", "z", "l", "r" ->
						{
							n = inputA.length > cursor + 1 ? Integer.parseInt(inputA[cursor + 1]) : 1;
							cursor += 2;
						}
				default ->
						{
							n = 1;
							cursor += 1;
						}
			}
			switch(c1)
			{
				case "v" -> zf = FeldPosition1.addieren(zf, -n, 0);
				case "z" -> zf = FeldPosition1.addieren(zf, n, 0);
				case "l" -> zf = FeldPosition1.addieren(zf, 0, -n);
				case "r" -> zf = FeldPosition1.addieren(zf, 0, n);
			}
		}
		if(!zf.equals(spieler.spielfigur().getFA()))
		{
			spieler.spielfigur().geheZu(zf, rng::nextBoolean);
			while(spieler.spielfigur().inBewegung())
			{
				mapText(spieler);
				try
				{
					Thread.sleep(100);
				}catch(InterruptedException ex)
				{
					throw new RuntimeException(ex);
				}
				spieler.spielfigur().bewege();
			}
			spieler.spielfigur().kannForschen().ifPresent(mkp -> vorrat.forsche(mkp, mapKartenstapel));
			mapText(spieler);
		}
	}
}