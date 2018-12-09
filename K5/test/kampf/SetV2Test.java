package kampf;

import java.util.*;
import kartenset.*;
import org.junit.*;
import sets.*;

public class SetV2Test
{
	private SetV2Aktionen setV2Aktionen;
	private SetV2Waffen setV2Waffen;
	private SetV2Klassen setV2Klassen;
	private SetV2Gegner setV2Gegner;
	private List<Karte> alleKarten;
	private KarteBild2 wugu2;

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
		wugu2 = new KarteBild2();
	}

	@Test
	public void alle()
	{
		System.out.println(wugu2.bilderReihe(alleKarten));
	}

	@Test
	public void bilderReihe2()
	{
		System.out.println(wugu2.bilderReihe2(alleKarten, 5));
	}

	@Test
	public void aktionen()
	{
		System.out.println(wugu2.bilderReihe2(new ArrayList<>(setV2Aktionen.alleKarten()), 6));
	}

	@Test
	public void waffen()
	{
		System.out.println(wugu2.bilderReihe2(new ArrayList<>(setV2Waffen.alleKarten()), 6));
	}

	@Test
	public void klassen()
	{
		System.out.println(wugu2.bilderReihe2(new ArrayList<>(setV2Klassen.alleKarten()), 6));
	}

	@Test
	public void gegner()
	{
		System.out.println(wugu2.bilderReihe2(new ArrayList<>(setV2Gegner.alleKarten()), 6));
	}

	@Test
	public void alleKB3()
	{
		KarteBild3 kb3 = new KarteBild3();
		alleKarten.forEach(k -> System.out.println(Arrays.stream(kb3.karteBild(k))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow()));
	}
}