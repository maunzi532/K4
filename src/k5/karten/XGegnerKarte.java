package k5.karten;

import k5.effekt.*;

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
	public int getExp(int x)
	{
		return xExp[x];
	}
}