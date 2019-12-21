package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public class Waffenkarte implements EffektKarte
{
	public final String name;
	public final int kosten;
	public final int angriff;
	public final int geschwindigkeit;
	public final int klassencode;
	public final boolean gegnerOK;
	public final List<KartenEffekt> effekte;

	public Waffenkarte(String name, int kosten, int angriff, int geschwindigkeit, int klassencode,
			boolean gegnerOK, KartenEffekt... effekte)
	{
		this.name = name;
		this.kosten = kosten;
		this.angriff = angriff;
		this.geschwindigkeit = geschwindigkeit;
		this.klassencode = klassencode;
		this.gegnerOK = gegnerOK;
		this.effekte = Arrays.asList(effekte);
	}

	@Override
	public String name()
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

	public boolean isGegnerOK()
	{
		return gegnerOK;
	}

	@Override
	public List<String> werteLO()
	{
		return List.of("K", String.valueOf(kosten),
				"A", String.valueOf(angriff),
				"G", String.valueOf(geschwindigkeit));
	}

	@Override
	public List<String> werteLU()
	{
		return null;
	}

	@Override
	public List<String> werteR()
	{
		return null;
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