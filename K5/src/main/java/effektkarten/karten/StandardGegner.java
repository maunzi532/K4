package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public record StandardGegner(Charakterkarte charakterkarte) implements Gegner
{
	public StandardGegner(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, int exp, KartenEffekt... effekte)
	{
		this(new Charakterkarte(name, angriff, waffenwert, geschwindigkeit, verteidigung, leben, exp, effekte));
	}

	@Override
	public String name()
	{
		return charakterkarte.name();
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return charakterkarte.effekte();
	}

	@Override
	public int minExp()
	{
		return charakterkarte.exp();
	}

	@Override
	public int maxExp()
	{
		return charakterkarte.exp();
	}

	@Override
	public List<String> werteLO()
	{
		return charakterkarte.werteLO();
	}

	@Override
	public List<String> werteLU()
	{
		return charakterkarte.werteLU();
	}

	@Override
	public List<String> werteR()
	{
		return charakterkarte.werteR();
	}
}