package karten;

import effekt.*;
import java.util.*;
import kartenset.*;

public class Charakterkarte implements Karte
{
	private final String name;
	private final int angriff;
	private final int waffenwert;
	private final int geschwindigkeit;
	private final int verteidigung;
	private final int leben;
	private final int exp;
	private final List<KartenEffekt> effekte;

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
	public String getName()
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

	@Override
	public List<String> werte()
	{
		return Arrays.asList("A", String.valueOf(angriff),
				"W", String.valueOf(waffenwert),
				"G", String.valueOf(geschwindigkeit),
				"V", String.valueOf(verteidigung),
				"L", String.valueOf(leben));
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}
}