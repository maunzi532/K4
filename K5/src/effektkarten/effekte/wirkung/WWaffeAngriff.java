package effektkarten.effekte.wirkung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public class WWaffeAngriff implements Wirkung
{
	private final int multiplikator;

	public WWaffeAngriff(int multiplikator)
	{
		this.multiplikator = multiplikator;
	}

	@Override
	public int triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return n.effektZielKarte(EffektZielKartentyp.WAFFE, mit).basisWert(Basiswert.ANGRIFF);
	}

	@Override
	public int getAngriff(int daten)
	{
		return daten * multiplikator;
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder("Angriff ist um A_(Waffe) ");
		if(multiplikator > 0)
			sb.append("erhöht");
		if(multiplikator < 0)
			sb.append("verringert");
		return sb.toString();
	}
}