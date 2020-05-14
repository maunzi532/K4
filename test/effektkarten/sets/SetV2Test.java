package effektkarten.sets;

import effektkarten.ansichtkarte.EffektKarte;
import effektkarten.ansichtkarte.KartenEffekt;
import effektkarten.textbild.KarteBild3;
import effektkarten.karten.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class SetV2Test
{
	private Kartenset<Aktionskarte> aktionen;
	private Kartenset<Waffenkarte> waffen;
	private Kartenset<Gegner> gegner;
	private Kartenset<Charakterkarte> klassen;
	private List<EffektKarte> alleKarten;
	private KarteBild3 karteBild3;

	@BeforeEach
	public void init()
	{
		aktionen = new SetV2Aktionen().fertig();
		waffen = new SetV2Waffen().fertig();
		gegner = new SetV2Gegner().fertig();
		klassen = new SetV2Klassen().fertig();
		alleKarten = new ArrayList<>();
		alleKarten.addAll(aktionen.alleKarten());
		alleKarten.addAll(waffen.alleKarten());
		alleKarten.addAll(gegner.alleKarten());
		alleKarten.addAll(klassen.alleKarten());
		karteBild3 = new KarteBild3();
	}

	/*@Test
	@Ignore*/
	public void alle()
	{
		System.out.println(karteBild3.inZeilen(alleKarten, 7));
	}

	/*@Test
	@Ignore*/
	public void aktionen()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(aktionen.alleKarten()), 7));
	}

	/*@Test
	@Ignore*/
	public void waffen()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(waffen.alleKarten()), 7));
	}

	/*@Test
	@Ignore*/
	public void klassen()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(klassen.alleKarten()), 7));
	}

	/*@Test
	@Ignore*/
	public void gegner()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(gegner.alleKarten()), 7));
	}

	@Test
	public void karteBild3Test()
	{
		System.out.println(Arrays.stream(karteBild3.karteBild("Wugu",
				null, List.of(), List.of(), List.of(), null))
				.map(String::new).reduce((e1, e2) -> e1 + '\n' + e2).orElseThrow());
		System.out.println(Arrays.stream(karteBild3.karteBild("Fortgeschrittener Eisstab", 0b11010100,
				List.of("A", "10", "B", "2"), List.of("C", "-4", "D", "4"), List.of(), null))
				.map(String::new).reduce((e1, e2) -> e1 + '\n' + e2).orElseThrow());
		System.out.println(Arrays.stream(karteBild3.karteBild("Fortgeschrittener Magischer Stab", 0b11111111,
				List.of("A", "10", "Blablah", "2"), List.of("C", "-4", "D", "12868884"), List.of("12000"),
				List.of(new TestEffekt())))
				.map(String::new).reduce((e1, e2) -> e1 + '\n' + e2).orElseThrow());
		System.out.println(Arrays.stream(karteBild3.karteBild(gegner.gibKarte("Koba")))
				.map(String::new).reduce((e1, e2) -> e1 + '\n' + e2).orElseThrow());
	}

	private static final class TestEffekt extends KartenEffekt
	{
		private TestEffekt()
		{
			super("# ", "Wugu Wuguwugu A_+_1 V_-_1", 0);
		}
	}
}