package main;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public final class Einstellungen
{
	public final int anzahlSpieler;
	public final int laengeHauptWeg;
	public final int maxSeitwaerts;
	public final int sicherSeitwaertsMin;
	public final List<Integer> mittelBossOrte;
	public final List<String> bossgegnerKartennamen;
	public final int traenkeProSpieler;
	public final List<Integer> trankExp;
	public final List<Integer> waffenkistenWerte;
	public final int wandKosten;
	public final int basisHaendlerAuswahl;
	public final int extraHaendlerAuswahlProHaendler;
	public final int haendlerNiedrigstesAngebot;
	public final int haendlerHoechstesAngebot;
	public final List<Integer> gegnerZeilenExpBonus;
	public final List<Integer> gegnerExpAbfolge;
	public final int gegnerExpAbfolgeMultiplikator;
	public final int gegnerExpStart;
	public final int gegnerExpWeiter;
	public final int gegnerAnzahlZiehenVersuche;
	public final int gegnerWaffenwertMin;
	public final int gegnerWaffenwertMax;
	public final int basisAktionenOptionen;
	public final int extraAktionenOptionenProSpieler;
	public final int lebenMultiplikator;
	public final int gesBonusAbstand;

	public Einstellungen(List<List<String>> input)
	{
		Map<String, String> map = new HashMap<>();
		for(List<String> input1 : input)
		{
			input1.forEach(e -> map.put(e.substring(0, e.indexOf(" = ")), e.substring(e.indexOf(" = ") + 3, e.indexOf(';'))));
		}
		anzahlSpieler = Integer.parseInt(map.get("anzahlSpieler"));
		laengeHauptWeg = Integer.parseInt(map.get("laengeHauptWeg"));
		maxSeitwaerts = Integer.parseInt(map.get("maxSeitwaerts"));
		sicherSeitwaertsMin = Integer.parseInt(map.get("sicherSeitwaertsMin"));
		mittelBossOrte = intList(map.get("mittelBossOrte"));
		bossgegnerKartennamen = stringList(map.get("bossgegnerKartennamen"));
		traenkeProSpieler = Integer.parseInt(map.get("traenkeProSpieler"));
		trankExp = intList(map.get("trankExp"));
		waffenkistenWerte = intList(map.get("waffenkistenWerte"));
		wandKosten = Integer.parseInt(map.get("wandKosten"));
		basisHaendlerAuswahl = Integer.parseInt(map.get("basisHaendlerAuswahl"));
		extraHaendlerAuswahlProHaendler = Integer.parseInt(map.get("extraHaendlerAuswahlProHaendler"));
		haendlerNiedrigstesAngebot = Integer.parseInt(map.get("haendlerNiedrigstesAngebot"));
		haendlerHoechstesAngebot = Integer.parseInt(map.get("haendlerHoechstesAngebot"));
		gegnerZeilenExpBonus = intList(map.get("gegnerZeilenExpBonus"));
		gegnerExpAbfolge = intList(map.get("gegnerExpAbfolge"));
		gegnerExpAbfolgeMultiplikator = Integer.parseInt(map.get("gegnerExpAbfolgeMultiplikator"));
		gegnerExpStart = Integer.parseInt(map.get("gegnerExpStart"));
		gegnerExpWeiter = Integer.parseInt(map.get("gegnerExpWeiter"));
		gegnerAnzahlZiehenVersuche = Integer.parseInt(map.get("gegnerAnzahlZiehenVersuche"));
		gegnerWaffenwertMin = Integer.parseInt(map.get("gegnerWaffenwertMin"));
		gegnerWaffenwertMax = Integer.parseInt(map.get("gegnerWaffenwertMax"));
		basisAktionenOptionen = Integer.parseInt(map.get("basisAktionenOptionen"));
		extraAktionenOptionenProSpieler = Integer.parseInt(map.get("extraAktionenOptionenProSpieler"));
		lebenMultiplikator = Integer.parseInt(map.get("lebenMultiplikator"));
		gesBonusAbstand = Integer.parseInt(map.get("gesBonusAbstand"));
	}

	private List<Integer> intList(String value)
	{
		return Arrays.stream(value.substring(value.indexOf('(') + 1, value.indexOf(')')).split(", "))
				.map(Integer::parseInt).collect(Collectors.toList());
	}

	private List<String> stringList(String value)
	{
		return Arrays.stream(value.substring(value.indexOf('(') + 1, value.indexOf(')')).split(", "))
				.map(v -> v.substring(1, v.length() - 1)).collect(Collectors.toList());
	}

	public static Einstellungen lies(String... dateien)
	{
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		return new Einstellungen(Arrays.stream(dateien).map(f ->
		{
			try
			{
				return Files.readAllLines(Paths.get(Objects.requireNonNull(cl.getResource(f)).toURI()));
			}catch(IOException | URISyntaxException ex)
			{
				throw new RuntimeException(ex);
			}
		}).collect(Collectors.toList()));
	}
}