package effektkarten.effekte.wirkung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public abstract class DatenWirkung implements Wirkung
{
	public final Wirkungswert wirktAuf;
	public final int multiplikator;
	public final int divisor;
	public final int max;

	public DatenWirkung(Wirkungswert wirktAuf, int multiplikator, int divisor, int max)
	{
		this.wirktAuf = wirktAuf;
		this.multiplikator = multiplikator;
		this.divisor = divisor;
		this.max = max;
	}

	@Override
	public abstract int triggere(EffektZielCharakter n, EffektZielCharakter ziel);

	@Override
	public int wert(Wirkungswert wert, int daten)
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

	public abstract String nameDaten();

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(wirktAuf.text).append(multiplikator >= 0 ? "+_" : "-_");
		if(Math.abs(multiplikator) > 1 || divisor > 1)
			sb.append("(");
		sb.append(nameDaten());
		if(Math.abs(multiplikator) > 1)
			sb.append(" *_").append(Math.abs(multiplikator));
		if(divisor > 1)
			sb.append(" /_").append(divisor);
		if(Math.abs(multiplikator) > 1 || divisor > 1)
			sb.append(")");
		if(max / multiplikator > 0)
			sb.append(" (max._").append(max).append(")");
		return sb.toString();
	}
}