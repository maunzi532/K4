package effektkarten.effekte.effekt;

import effektkarten.effekte.bedingung.Bedingung;
import effektkarten.effekte.wirkung.Wirkung;
import effektkarten.effekte.ziel.*;
import java.util.*;

public final class ZeitTriggerEffekt extends TriggerEffekt
{
	private final EndTrigger endTrigger;
	private final int dauer;
	private final ZeitEffektBetrifft betrifft;
	private final EffektZielKartentyp an;
	private final MitWaffe zielWaffe;
	private final Wirkung wirkung;

	public ZeitTriggerEffekt(String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite, List<Bedingung> bedingungen,
			EndTrigger endTrigger, int dauer, ZeitEffektBetrifft betrifft, EffektZielKartentyp an,
			MitWaffe zielWaffe, Wirkung wirkung)
	{
		super(startTrigger.symbol, text, num, startTrigger, startSeite, bedingungen);
		this.endTrigger = endTrigger;
		this.dauer = dauer;
		this.betrifft = betrifft;
		this.an = an;
		this.zielWaffe = zielWaffe;
		this.wirkung = wirkung;
	}

	@Override
	public void triggere(EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		EffektZielCharakter betrifftCharakter = switch(betrifft)
				{
					case SENDER -> sender;
					case ZIEL -> ziel;
				};
		if(zielWaffe == MitWaffe.VERWENDET)
		{
			betrifftCharakter.effektZielKarte(an).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), sender, ziel);
		}
		else
		{
			betrifftCharakter.effektZielKarte(an, zielWaffe).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), sender, ziel);
		}
	}
}