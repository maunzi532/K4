package effektkarten.sets;

import effektkarten.ansichtkarte.*;
import java.util.*;
import effektkarten.kartebild.*;
import org.junit.*;

public class SetV2Test
{
	private SetV2Aktionen setV2Aktionen;
	private SetV2Waffen setV2Waffen;
	private SetV2Klassen setV2Klassen;
	private SetV2Gegner setV2Gegner;
	private List<EffektKarte> alleKarten;
	private KarteBild3 karteBild3;

	@Before
	public void init()
	{
		setV2Aktionen = new SetV2Aktionen();
		setV2Waffen = new SetV2Waffen();
		setV2Klassen = new SetV2Klassen();
		setV2Gegner = new SetV2Gegner();
		alleKarten = new ArrayList<>();
		alleKarten.addAll(setV2Aktionen.alleKarten());
		alleKarten.addAll(setV2Waffen.alleKarten());
		alleKarten.addAll(setV2Klassen.alleKarten());
		alleKarten.addAll(setV2Gegner.alleKarten());
		karteBild3 = new KarteBild3();
	}

	@Test
	public void alle()
	{
		System.out.println(karteBild3.inZeilen(alleKarten, 7));
	}

	@Test
	public void aktionen()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(setV2Aktionen.alleKarten()), 7));
	}

	@Test
	public void waffen()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(setV2Waffen.alleKarten()), 7));
	}

	@Test
	public void klassen()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(setV2Klassen.alleKarten()), 7));
	}

	@Test
	public void gegner()
	{
		System.out.println(karteBild3.inZeilen(new ArrayList<>(setV2Gegner.alleKarten()), 7));
	}

	@Test
	public void karteBild3Test()
	{
		System.out.println(Arrays.stream(karteBild3.karteBild("Wugu",
				null, null, null, null, null))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
		System.out.println(Arrays.stream(karteBild3.karteBild("Fortgeschrittener Eisstab", 0b11010100,
				List.of("A", "10", "B", "2"), List.of("C", "-4", "D", "4"), null, null))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
		System.out.println(Arrays.stream(karteBild3.karteBild("Fortgeschrittener Magischer Stab", 0b11111111,
				List.of("A", "10", "Blablah", "2"), List.of("C", "-4", "D", "12868884"), List.of("12000"),
				List.of(new KartenEffekt("# ", "Wugu Wuguwugu A_+_1 V_-_1", 0){})))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
		System.out.println(Arrays.stream(karteBild3.karteBild(new SetV2Gegner().gibKarte("Koba")))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
	}
}