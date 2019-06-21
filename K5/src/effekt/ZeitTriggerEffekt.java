package effekt;

import effekt.bedingung.*;
import effekt.wirkung.*;
import java.util.*;

public class ZeitTriggerEffekt extends TriggerEffekt
{
	private final EndTrigger endTrigger;
	private final int dauer;
	private final boolean betrifftGegner;
	private final Kartentyp an;
	private final W zielWaffe;
	private final Wirkung wirkung;

	public ZeitTriggerEffekt(String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite, List<Bedingung> bedingungen,
			EndTrigger endTrigger, int dauer, boolean betrifftGegner, Kartentyp an,
			W zielWaffe, Wirkung wirkung)
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
	public void triggere(NTI n, NTI ziel, W mit)
	{
		NTI betrifft = betrifftGegner ? ziel : n;
		W zielWaffe1 = zielWaffe == W.OK ? mit : zielWaffe;
		switch(an)
		{
			case CHARAKTER -> betrifft.nCharakter().neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
			case WAFFE -> betrifft.nWaffe(zielWaffe1).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
			case AKTION -> betrifft.nAktion().neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
		}
	}
}