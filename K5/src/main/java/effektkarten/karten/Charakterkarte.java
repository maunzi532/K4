package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public class Charakterkarte implements EffektKarte
{
	public final String name;
	public final int angriff;
	public final int waffenwert;
	public final int geschwindigkeit;
	public final int verteidigung;
	public final int leben;
	public final int exp;
	public final List<KartenEffekt> effekte;

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
		return List.of();
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}
}