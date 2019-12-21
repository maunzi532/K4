package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;
import java.util.stream.*;

public class XGegner implements Gegner
{
	public static final int X_ANZAHL = 5;

	public final Charakterkarte basiskarte;
	public final boolean[] xWerte;
	public final int[] xExp;

	public XGegner(Charakterkarte basiskarte, boolean[] xWerte, int[] xExp)
	{
		this.basiskarte = basiskarte;
		this.xWerte = xWerte;
		this.xExp = xExp;
	}

	@Override
	public int minExp()
	{
		return xExp[0];
	}

	@Override
	public int maxExp()
	{
		return xExp[X_ANZAHL - 1];
	}

	public int inBereich(int min, int max)
	{
		for(int i = 0; i < xExp.length; i++)
		{
			if(xExp[i] >= min && xExp[i] <= max)
				return i;
		}
		return -1;
	}

	public Charakterkarte erstelleCharakterkarte(int x)
	{
		return new Charakterkarte(basiskarte.name,
				basiskarte.angriff + (xWerte[0] ? x : 0),
				basiskarte.waffenwert + (xWerte[1] ? x : 0),
				basiskarte.geschwindigkeit + (xWerte[2] ? x : 0),
				basiskarte.verteidigung + (xWerte[3] ? x : 0),
				basiskarte.leben + (xWerte[4] ? x : 0),
				xExp[x],
				basiskarte.effekte);
	}

	@Override
	public String name()
	{
		return basiskarte.name();
	}

	@Override
	public List<String> werteLO()
	{
		return List.of("A", basiskarte.angriff + (xWerte[0] ? "+X" : ""),
			"W", basiskarte.waffenwert + (xWerte[1] ? "+X" : ""),
			"G", basiskarte.geschwindigkeit + (xWerte[2] ? "+X" : ""),
			"V", basiskarte.verteidigung + (xWerte[3] ? "+X" : ""),
			"L", basiskarte.leben + (xWerte[4] ? "+X" : ""));
	}

	@Override
	public List<String> werteLU()
	{
		return basiskarte.werteLU();
	}

	@Override
	public List<String> werteR()
	{
		return Arrays.stream(xExp).mapToObj(String::valueOf).collect(Collectors.toList());
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return basiskarte.effekte();
	}
}