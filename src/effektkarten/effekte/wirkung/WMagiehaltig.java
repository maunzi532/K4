package effektkarten.effekte.wirkung;

import effektkarten.effekte.ziel.*;

public record WMagiehaltig(Wirkungswert wirktAuf, int max) implements Wirkung
{
	@Override
	public int triggere(EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		return sender.getGeladeneMagie();
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
		return wirktAuf.text + "ist um gezahlte Magie erhöht (max. " + max + ')';
	}
}