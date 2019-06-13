package effekt.wirkung;

import effekt.*;
import kampf.*;

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