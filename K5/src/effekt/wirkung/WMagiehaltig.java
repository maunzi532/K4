package effekt.wirkung;

import effekt.*;

public class WMagiehaltig implements Wirkung
{
	private final Wirkungswert wirktAuf;

	public WMagiehaltig(Wirkungswert wirktAuf)
	{
		this.wirktAuf = wirktAuf;
	}

	@Override
	public int triggere(NTI n, NTI ziel, W mit)
	{
		return n.getGeladeneMagie();
	}

	@Override
	public int getWert(Wirkungswert wert, int daten)
	{
		if(wert == wirktAuf)
		{
			return daten;
		}
		return 0;
	}

	@Override
	public String text()
	{
		return wirktAuf.text + "ist um gezahlte Magie erhöht";
	}
}