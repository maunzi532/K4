package karten;

import basiskarte.*;
import java.util.*;

public class Charakterkarte implements Karte
{
	protected final String name;
	protected final int angriff;
	protected final int waffenwert;
	protected final int geschwindigkeit;
	protected final int verteidigung;
	protected final int leben;
	protected final int exp;
	protected final List<KartenEffekt> effekte;

	public Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, KartenEffekt... effekte)
	{
		this.name = name;
		this.angriff = angriff;
		this.waffenwert = waffenwert;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.leben = leben;
		exp = 0;
		this.effekte = Arrays.asList(effekte);
	}

	public Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, int exp, KartenEffekt... effekte)
	{
		this.name = name;
		this.angriff = angriff;
		this.waffenwert = waffenwert;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.leben = leben;
		this.exp = exp;
		this.effekte = Arrays.asList(effekte);
	}

	public Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, List<KartenEffekt> effekte)
	{
		this.name = name;
		this.angriff = angriff;
		this.waffenwert = waffenwert;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.leben = leben;
		exp = 0;
		this.effekte = effekte;
	}

	public Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, int exp, List<KartenEffekt> effekte)
	{
		this.name = name;
		this.angriff = angriff;
		this.waffenwert = waffenwert;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.leben = leben;
		this.exp = exp;
		this.effekte = effekte;
	}

	@Override
	public String name()
	{
		return name;
	}

	public int getAngriff()
	{
		return angriff;
	}

	public int getWaffenwert()
	{
		return waffenwert;
	}

	public int getGeschwindigkeit()
	{
		return geschwindigkeit;
	}

	public int getVerteidigung()
	{
		return verteidigung;
	}

	public int getLeben()
	{
		return leben;
	}

	public int getExp(int x)
	{
		return exp;
	}

	public int inBereich(int min, int max)
	{
		return exp >= min && exp <= max ? 0 : -1;
	}

	@Override
	public List<String> werteLO()
	{
		return Arrays.asList("A", String.valueOf(angriff),
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
		return null;
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}
}