package effektkarten.effekte.effekt;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public class ZeitMagieEffekt extends MagieEffekt
{
	private final EndTrigger endTrigger;
	private final int dauer;
	private final boolean betrifftGegner;
	private final EffektZielKartentyp an;
	private final MitWaffe zielWaffe;
	private final Wirkung wirkung;

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
	public void aktiviere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		super.aktiviere(n, ziel, mit);
		EffektZielCharakter betrifft = betrifftGegner ? ziel : n;
		MitWaffe zielWaffe1 = zielWaffe == MitWaffe.VERWENDET ? mit : zielWaffe;
		betrifft.effektZielKarte(an, zielWaffe1).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
	}
}