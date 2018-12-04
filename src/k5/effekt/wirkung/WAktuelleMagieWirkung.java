package k5.effekt.wirkung;

import k5.kampf.*;
import k5.karten.*;

public class WAktuelleMagieWirkung extends DatenWirkung
{
	public WAktuelleMagieWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		super(wirktAuf, multiplikator, divisor, max);
	}

	@Override
	public int triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return n.getMagie();
	}

	@Override
	public String nameDaten()
	{
		return "Magie";
	}
}