package karten;

import effekt.*;
import java.util.*;
import java.util.stream.*;

public class XGegnerKarte extends Charakterkarte
{
	public boolean[] xWerte;
	public int[] xExp;

	public XGegnerKarte(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben,
			int[] xExp, KartenEffekt... effekte)
	{
		super(name, angriff, waffenwert, geschwindigkeit, verteidigung, leben, effekte);
		xWerte = new boolean[5];
		this.xExp = xExp;
	}

	public XGegnerKarte xAngriff()
	{
		xWerte[0] = true;
		return this;
	}

	public XGegnerKarte xWaffenwert()
	{
		xWerte[1] = true;
		return this;
	}

	public XGegnerKarte xGeschwindigkeit()
	{
		xWerte[2] = true;
		return this;
	}

	public XGegnerKarte xVerteidigung()
	{
		xWerte[3] = true;
		return this;
	}

	public XGegnerKarte xLeben()
	{
		xWerte[4] = true;
		return this;
	}

	@Override
	public List<String> werteLO()
	{
		return Arrays.asList("A", angriff + (xWerte[0] ? "+X" : ""),
				"W", waffenwert + (xWerte[1] ? "+X" : ""),
				"G", geschwindigkeit + (xWerte[2] ? "+X" : ""),
				"V", verteidigung + (xWerte[3] ? "+X" : ""),
				"L", leben + (xWerte[4] ? "+X" : ""));
	}

	@Override
	public List<String> werteR()
	{
		return Arrays.stream(xExp).mapToObj(String::valueOf).collect(Collectors.toList());
	}

	@Override
	public int getExp(int x)
	{
		return xExp[x];
	}
}