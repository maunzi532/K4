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
	private final List<KartenEffekt> effekte;

	public Charakterkarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, KartenEffekt... effekte)
	{
		this.name = name;
		this.angriff = angriff;
		this.waffenwert = waffenwert;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.leben = leben;
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