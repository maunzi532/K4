package effekt.wirkung;

import effekt.*;

public class WWaffeAngriff implements Wirkung
{
	private final int multiplikator;

	public WWaffeAngriff(int multiplikator)
	{
		this.multiplikator = multiplikator;
	}

	@Override
	public int triggere(NTI n, NTI ziel, W mit)
	{
		return n.nWaffe(mit).basisWert(Basiswert.ANGRIFF);
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