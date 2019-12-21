package effektkarten.effekte.effekt;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public class ZeitTriggerEffekt extends TriggerEffekt
{
	public final EndTrigger endTrigger;
	public final int dauer;
	public final boolean betrifftGegner;
	public final EffektZielKartentyp an;
	public final MitWaffe zielWaffe;
	public final Wirkung wirkung;

	public ZeitTriggerEffekt(String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite, List<Bedingung> bedingungen,
			EndTrigger endTrigger, int dauer, boolean betrifftGegner, EffektZielKartentyp an,
			MitWaffe zielWaffe, Wirkung wirkung)
	{
		super(startTrigger.symbol, text, num, startTrigger, startSeite, bedingungen);
		this.endTrigger = endTrigger;
		this.dauer = dauer;
		this.betrifftGegner = betrifftGegner;
		this.an = an;
		this.zielWaffe = zielWaffe;
		this.wirkung = wirkung;
	}

	@Override
	public void triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		EffektZielCharakter betrifft = betrifftGegner ? ziel : n;
		MitWaffe zielWaffe1 = zielWaffe == MitWaffe.VERWENDET ? mit : zielWaffe;
		betrifft.effektZielKarte(an, zielWaffe1).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
	}
}