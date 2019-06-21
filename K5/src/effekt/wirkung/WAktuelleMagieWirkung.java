package effekt.wirkung;

import effekt.*;

public class WAktuelleMagieWirkung extends DatenWirkung
{
	public WAktuelleMagieWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		super(wirktAuf, multiplikator, divisor, max);
	}

	@Override
	public int triggere(NTI n, NTI ziel, W mit)
	{
		return n.getMagie();
	}

	@Override
	public String nameDaten()
	{
		return "Magie";
	}
}