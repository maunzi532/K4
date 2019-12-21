package main;

import java.util.*;

public class Einstellungen
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
		gegnerAnzahlZiehenVersuche = 3;
		gegnerWaffenwertMin = -1;
		gegnerWaffenwertMax = 0;
		basisAktionenOptionen = 3;
		extraAktionenOptionenProSpieler = 1;
		lebenMultiplikator = 3;
		gesBonusAbstand = 5;
	}
}