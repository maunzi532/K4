package effektkarten.effekte.wirkung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public class WAktuelleMagieWirkung extends DatenWirkung
{
	public WAktuelleMagieWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		super(wirktAuf, multiplikator, divisor, max);
	}

	@Override
	public int triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return n.getMagie();
	}

	@Override
	public String nameDaten()
	{
		return "Magie";
	}
}