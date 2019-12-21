package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public class StandardGegner implements Gegner
{
	public final Charakterkarte charakterkarte;
	public final int exp;

	public StandardGegner(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, int exp, KartenEffekt... effekte)
	{
		charakterkarte = new Charakterkarte(name, angriff, waffenwert, geschwindigkeit, verteidigung, leben, effekte);
		this.exp = exp;
	}

	public StandardGegner(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, int exp, List<KartenEffekt> effekte)
	{
		charakterkarte = new Charakterkarte(name, angriff, waffenwert, geschwindigkeit, verteidigung, leben, effekte);
		this.exp = exp;
	}

	@Override
	public int minExp()
	{
		return exp;
	}

	@Override
	public int maxExp()
	{
		return exp;
	}

	@Override
	public String name()
	{
		return charakterkarte.name();
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
		return List.of(String.valueOf(exp));
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return charakterkarte.effekte();
	}
}