package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public record Waffenkarte(String name, int kosten, int angriff, int geschwindigkeit, int klassencode,
		boolean gegnerOK, List<KartenEffekt> effekte) implements EffektKarte
{
	public Waffenkarte(String name, int kosten, int angriff, int geschwindigkeit, int klassencode, boolean gegnerOK, KartenEffekt... effekte)
	{
		this(name, kosten, angriff, geschwindigkeit, klassencode, gegnerOK, Arrays.asList(effekte));
	}

	@Override
	public String name()
	{
		return name;
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
		return List.of();
	}

	@Override
	public List<String> werteR()
	{
		return List.of();
	}
}