package effekt.wirkung;

import kampf.*;
import karten.*;

public class WGesVorteilWirkung implements Wirkung
{
	private final Wirkungswert wirktAuf;
	private final int multiplikator;
	private final int divisor;
	private final int max;

	public WGesVorteilWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		this.wirktAuf = wirktAuf;
		this.multiplikator = multiplikator;
		this.divisor = divisor;
		this.max = max;
	}

	@Override
	public int triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		int gesVorteil = n.getGesAngriff() - ziel.getGesAngriff();
		return Math.max(gesVorteil, 0);
	}

	@Override
	public int getWert(Wirkungswert wert, int daten)
	{
		if(wert == wirktAuf)
		{
			int vorMax = daten * multiplikator / divisor;
			if(vorMax > 0 && max > 0)
				return Math.min(vorMax, max);
			if(vorMax < 0 && max < 0)
				return Math.max(vorMax, max);
			return vorMax;
		}
		return 0;
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(wirktAuf.text).append(multiplikator >= 0 ? " + " : " - ");
		if(Math.abs(multiplikator) > 1 || divisor > 1)
			sb.append("(");
		sb.append("Ges. Vorteil");
		if(Math.abs(multiplikator) > 1)
			sb.append(" * ").append(Math.abs(multiplikator));
		if(divisor > 1)
			sb.append(" / ").append(divisor);
		if(Math.abs(multiplikator) > 1 || divisor > 1)
			sb.append(")");
		if(max / multiplikator > 0)
			sb.append(" (max. ").append(max).append(")");
		return sb.toString();
	}
}