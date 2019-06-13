package effekt.wirkung;

import effekt.*;
import kampf.*;

public class WGesVorteilWirkung extends DatenWirkung
{
	public WGesVorteilWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		super(wirktAuf, multiplikator, divisor, max);
	}

	@Override
	public int triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		int gesVorteil = n.getGesAktion() - ziel.getGesAktion();
		return Math.max(gesVorteil, 0);
	}

	@Override
	public String nameDaten()
	{
		return "Ges._Vorteil";
	}
}