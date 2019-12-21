package effektkarten.sets;

import effektkarten.ansichtkarte.*;
import effektkarten.karten.*;
import java.util.*;
import java.util.stream.*;
import effektkarten.kartebild.*;
import org.junit.*;

public class SetTest
{
	private Kartenset<Aktionskarte> aktionen;
	private Kartenset<Waffenkarte> waffen;
	private Kartenset<Gegner> gegner;
	private Kartenset<Charakterkarte> klassen;
	private KarteBild3 karteBild3;

	@Before
	public void init()
	{
		aktionen = new SetV2Aktionen().fertig();
		waffen = new SetV2Waffen().fertig();
		gegner = new SetV2Gegner().fertig();
		klassen = new SetV2Klassen().fertig();
		karteBild3 = new KarteBild3();
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
			ergebnisA.stream().map(EffektKarte::name).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		System.out.println("Waffen:");
		var ergebnisW = setTest.waffen.findeKarten(input);
		if(ergebnisW.size() <= 5)
			ergebnisW.stream().map(EffektKarte::name).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		System.out.println("Gegner:");
		var ergebnisG = setTest.gegner.findeKarten(input);
		if(ergebnisG.size() <= 5)
			ergebnisG.stream().map(EffektKarte::name).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		System.out.println("Klassen:");
		var ergebnisK = setTest.klassen.findeKarten(input);
		if(ergebnisK.size() <= 5)
			ergebnisK.stream().map(EffektKarte::name).forEach(x -> System.out.println("\t" + x));
		else
			System.out.println("\tZu viele");
		List<EffektKarte> alle = new ArrayList<>();
		alle.addAll(ergebnisA);
		alle.addAll(ergebnisW);
		alle.addAll(ergebnisG);
		alle.addAll(ergebnisK);
		Collection<List<Integer>> a = IntStream
				.range(0, alle.size()).boxed().collect(Collectors.groupingBy(e -> e / 6)).values();
		System.out.println(a.stream().map(e -> e.stream().map(k -> setTest.karteBild3.karteBild(alle.get(k))).collect(Collectors.toList()))
				.map(setTest.karteBild3::zeile).map(e -> Arrays.stream(e).map(String::new).reduce((s, s2) -> s + '\n' + s2).orElseThrow())
				.reduce((s, s2) -> s + "\n\n" + s2).orElse(""));
	}
}