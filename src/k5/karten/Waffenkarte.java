package k5.karten;

import k5.effekt.*;
import java.util.*;
import k5.kartenset.*;

public class Waffenkarte implements Karte
{
	private final String name;
	private final int kosten;
	private final int angriff;
	private final int geschwindigkeit;
	private final int klassencode;
	private final List<KartenEffekt> effekte;

	public Waffenkarte(String name, int kosten, int angriff, int geschwindigkeit, int klassencode,
			KartenEffekt... effekte)
	{
		this.name = name;
		this.kosten = kosten;
		this.angriff = angriff;
		this.geschwindigkeit = geschwindigkeit;
		this.klassencode = klassencode;
		this.effekte = Arrays.asList(effekte);
	}

	@Override
	public String getName()
	{
		return name;
	}

	public int getKosten()
	{
		return kosten;
	}

	public int getAngriff(int nAngriff)
	{
		return angriff;
	}

	public int getAngriff()
	{
		return angriff;
	}

	public int getGeschwindigkeit(int nAngriff)
	{
		return geschwindigkeit;
	}

	public int getGeschwindigkeit()
	{
		return geschwindigkeit;
	}

	@Override
	public List<String> werte()
	{
		return Arrays.asList("K", String.valueOf(kosten),
				"A", String.valueOf(angriff),
				"G", String.valueOf(geschwindigkeit));
	}

	@Override
	public int klassencode()
	{
		return klassencode;
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}
}