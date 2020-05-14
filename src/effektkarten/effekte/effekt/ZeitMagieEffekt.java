package effektkarten.effekte.effekt;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public final class ZeitMagieEffekt extends MagieEffekt
{
	public final EndTrigger endTrigger;
	public final int dauer;
	public final boolean betrifftGegner;
	public final EffektZielKartentyp an;
	public final MitWaffe zielWaffe;
	public final Wirkung wirkung;

	public ZeitMagieEffekt(String text, int num, int magieKosten, List<Bedingung> bedingungen,
			EndTrigger endTrigger, int dauer, boolean betrifftGegner, EffektZielKartentyp an,
			MitWaffe zielWaffe, Wirkung wirkung)
	{
		super("", text, num, magieKosten, bedingungen);
		this.endTrigger = endTrigger;
		this.dauer = dauer;
		this.betrifftGegner = betrifftGegner;
		this.an = an;
		this.zielWaffe = zielWaffe;
		this.wirkung = wirkung;
	}

	@Override
	public void aktiviere(EffektZielCharakter n, EffektZielCharakter ziel)
	{
		super.aktiviere(n, ziel);
		EffektZielCharakter betrifft = betrifftGegner ? ziel : n;
		betrifft.effektZielKarte(an).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel);
	}
}