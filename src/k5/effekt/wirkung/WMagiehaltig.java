package k5.effekt.wirkung;

import k5.kampf.*;
import k5.karten.*;

public class WMagiehaltig implements Wirkung
{
	private final Wirkungswert wirktAuf;

	public WMagiehaltig(Wirkungswert wirktAuf)
	{
		this.wirktAuf = wirktAuf;
	}

	@Override
	public int triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
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