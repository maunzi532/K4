package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public class XGegnerBuilder
{
	private final String name;
	private final int angriff;
	private final int waffenwert;
	private final int geschwindigkeit;
	private final int verteidigung;
	private final int leben;
	private final List<KartenEffekt> effekte;
	private final boolean[] xWerte;

	public XGegnerBuilder(String name, int angriff, int waffenwert, int geschwindigkeit, int verteidigung, int leben, KartenEffekt... effekte)
	{
		this.name = name;
		this.angriff = angriff;
		this.waffenwert = waffenwert;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.leben = leben;
		this.effekte = Arrays.asList(effekte);
		xWerte = new boolean[5];
	}

	public XGegnerBuilder xAngriff()
	{
		xWerte[0] = true;
		return this;
	}

	public XGegnerBuilder xWaffenwert()
	{
		xWerte[1] = true;
		return this;
	}

	public XGegnerBuilder xGeschwindigkeit()
	{
		xWerte[2] = true;
		return this;
	}

	public XGegnerBuilder xVerteidigung()
	{
		xWerte[3] = true;
		return this;
	}

	public XGegnerBuilder xLeben()
	{
		xWerte[4] = true;
		return this;
	}

	public XGegner xExp(int... xExp)
	{
		if(xExp.length != XGegner.X_ANZAHL)
			throw new RuntimeException("Anzahl Exp-Werte für X-Gegner \"" + name + "\" muss " + XGegner.X_ANZAHL + " sein");
		return new XGegner(new Charakterkarte(name, angriff, waffenwert, geschwindigkeit, verteidigung, leben, 0, effekte), xWerte, xExp);
	}
}