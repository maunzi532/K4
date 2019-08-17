package main;

import java.util.*;

public class Einstellungen
{
	public int anzahlSpieler;
	public int laengeHauptWeg;
	public int maxSeitwaerts;
	public int sicherSeitwaertsMin;
	public List<Integer> mittelBossOrte;
	public List<String> bossgegnerKartennamen;
	public int traenkeProSpieler;
	public List<Integer> trankExp;
	public List<Integer> waffenkistenWerte;
	public int wandKosten;
	public int basisHaendlerAuswahl;
	public int extraHaendlerAuswahlProHaendler;
	public int haendlerNiedrigstesAngebot;
	public int haendlerHoechstesAngebot;
	public List<Integer> gegnerZeilenExpBonus;
	public List<Integer> gegnerExpAbfolge;
	public int gegnerExpAbfolgeMultiplikator;
	public int gegnerExpStart;
	public int gegnerExpWeiter;
	public int basisAktionenOptionen;
	public int extraAktionenOptionenProSpieler;
	public int lebenMultiplikator;
	public int gesBonusAbstand;

	public Einstellungen()
	{
		anzahlSpieler = 2;
		laengeHauptWeg = 5; //Start, L, R, Beide, Boss
		maxSeitwaerts = 3;
		sicherSeitwaertsMin = 1; //muss weiterführen
		mittelBossOrte = List.of(2); //Mitte
		bossgegnerKartennamen = List.of("Mittelgegner", "Fortschreitender Gegner");
		traenkeProSpieler = 2; //Am anfang und nach MittelBoss
		trankExp = List.of(10, 40);
		waffenkistenWerte = List.of(6, 7, 8, 9, 10); //1 Waffe pro spieler
		wandKosten = 20;
		basisHaendlerAuswahl = 3;
		extraHaendlerAuswahlProHaendler = 1;
		haendlerNiedrigstesAngebot = -1;
		haendlerHoechstesAngebot = 2;
		gegnerZeilenExpBonus = List.of(0, 10, 20, 40, 70, 100); //0 - 10, 10 - 20, ... , 70 - 100
		gegnerExpAbfolge = List.of(10, 15, 20, 35, 50, 75);
		gegnerExpAbfolgeMultiplikator = 10;
		gegnerExpStart = 0; //10
		gegnerExpWeiter = 1; //10, 15, 20, 35, 50, 75, 100, ...
		basisAktionenOptionen = 3;
		extraAktionenOptionenProSpieler = 1;
		lebenMultiplikator = 3;
		gesBonusAbstand = 5;
	}
}