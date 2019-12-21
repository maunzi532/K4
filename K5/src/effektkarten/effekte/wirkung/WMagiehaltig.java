package effektkarten.effekte.wirkung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class WMagiehaltig implements Wirkung
{
	private final Wirkungswert wirktAuf;
	private final int max;

	public WMagiehaltig(Wirkungswert wirktAuf, int max)
	{
		this.wirktAuf = wirktAuf;
		this.max = max;
	}

	@Override
	public int triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return n.getGeladeneMagie();
	}

	@Override
	public int wert(Wirkungswert wert, int daten)
	{
		if(wert == wirktAuf)
		{
			return Math.min(daten, max);
		}
		return 0;
	}

	@Override
	public String text()
	{
		return wirktAuf.text + "ist um gezahlte Magie erhöht (max. " + max + ")";
	}
}