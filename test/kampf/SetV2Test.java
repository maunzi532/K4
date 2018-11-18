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
	private KarteBild wugu;

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
		wugu = new KarteBild(17, 7, 5, 10);
	}

	@Test
	public void alle()
	{
		System.out.println(wugu.bilderReihe(alleKarten));
	}

	@Test
	public void bilderReihe2()
	{
		System.out.println(wugu.bilderReihe2(alleKarten, 6));
	}
}