package kartenset;

import java.util.*;
import sets.*;

public class SetTest
{
	private SetV2Aktionen aktionen;
	private SetV2Waffen waffen;
	private SetV2Gegner gegner;
	private SetV2Klassen klassen;
	private KarteBild karteBild;

	public void init()
	{
		aktionen = new SetV2Aktionen();
		waffen = new SetV2Waffen();
		gegner = new SetV2Gegner();
		klassen = new SetV2Klassen();
		karteBild = new KarteBild(17, 7, 5, 10);
	}

	public static void main(String[] args)
	{
		SetTest setTest = new SetTest();
		setTest.init();
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		System.out.println("Aktionen:");
		var ergebnisA = setTest.aktionen.findeKarten(input);
		if(ergebnisA.size() <= 5)
			ergebnisA.stream().map(Karte::getName).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		System.out.println("Waffen:");
		var ergebnisW = setTest.waffen.findeKarten(input);
		if(ergebnisW.size() <= 5)
			ergebnisW.stream().map(Karte::getName).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		System.out.println("Gegner:");
		var ergebnisG = setTest.gegner.findeKarten(input);
		if(ergebnisG.size() <= 5)
			ergebnisG.stream().map(Karte::getName).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		System.out.println("Klassen:");
		var ergebnisK = setTest.klassen.findeKarten(input);
		if(ergebnisK.size() <= 5)
			ergebnisK.stream().map(Karte::getName).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		List<Karte> alle = new ArrayList<>();
		alle.addAll(ergebnisA);
		alle.addAll(ergebnisW);
		alle.addAll(ergebnisG);
		alle.addAll(ergebnisK);
		System.out.println(setTest.karteBild.bilderReihe2(alle, 6));
	}
}