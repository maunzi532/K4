package effektkarten.effekte.wirkung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class WAktuelleMagieWirkung extends DatenWirkung
{
	public WAktuelleMagieWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		super(wirktAuf, multiplikator, divisor, max);
	}

	@Override
	public int triggere(EffektZielCharakter n, EffektZielCharakter ziel)
	{
		return n.getMagie();
	}

	@Override
	public String nameDaten()
	{
		return "Magie";
	}
}