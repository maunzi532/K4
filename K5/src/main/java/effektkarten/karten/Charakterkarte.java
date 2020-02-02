package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public record Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben,
		int exp, List<KartenEffekt> effekte) implements EffektKarte
{
	public Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, int exp, KartenEffekt... effekte)
	{
		this(name, angriff, waffenwert, geschwindigkeit, verteidigung, leben, exp, Arrays.asList(effekte));
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}

	@Override
	public List<String> werteLO()
	{
		return List.of("A", String.valueOf(angriff),
				"W", String.valueOf(waffenwert),
				"G", String.valueOf(geschwindigkeit),
				"V", String.valueOf(verteidigung),
				"L", String.valueOf(leben));
	}

	@Override
	public List<String> werteLU()
	{
		return null;
	}

	@Override
	public List<String> werteR()
	{
		if(exp > 0)
			return List.of(String.valueOf(exp));
		else
			return null;
	}
}