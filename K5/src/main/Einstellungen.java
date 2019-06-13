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
	public List<Integer> gegnerZeilenExpBonus;
	public int gegnerExpStart;
	public int gegnerExpWeiter;

	public Einstellungen()
	{
		anzahlSpieler = 2;
		laengeHauptWeg = 5;
		maxSeitwaerts = 3;
		sicherSeitwaertsMin = 1;
		mittelBossOrte = List.of(2);
		bossgegnerKartennamen = List.of("Mittelgegner", "Fortschreitender Gegner");
		traenkeProSpieler = 2;
		trankExp = List.of(10, 40);
		waffenkistenWerte = List.of(6, 7, 8, 9, 10);
		gegnerZeilenExpBonus = List.of(0, 10, 20, 40, 70, 100);
		gegnerExpStart = 10;
		gegnerExpWeiter = 1;
	}
}