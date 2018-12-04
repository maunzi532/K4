package k5.charakter;

import java.util.*;

public class Einstellungen
{
	public int anzahlSpieler;
	public int yhMap;
	public int xmMap;
	public List<Integer> mittelBossOrte;
	public List<String> bossgegnerKarten;
	public int traenkeProSpieler;
	public List<Integer> trankExp;
	public List<Integer> waffenkistenWerte;
	public List<Integer> gegnerZeilenWerte;
	public int gegnerWertStart;
	public int gegnerWertWeiter;

	public Einstellungen()
	{
		anzahlSpieler = 2;
		yhMap = 5;
		xmMap = 3;
		mittelBossOrte = List.of(2);
		bossgegnerKarten = List.of("Mittelgegner", "Fortschreitender Gegner");
		traenkeProSpieler = 2;
		trankExp = List.of(10, 40);
		waffenkistenWerte = List.of(6, 7, 8, 9, 10);
		gegnerZeilenWerte = List.of(5, 10, 20, 40, 70, 100);
		gegnerWertStart = 2;
		gegnerWertWeiter = 2;
	}
}