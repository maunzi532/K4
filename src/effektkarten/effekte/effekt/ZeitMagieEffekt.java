package effektkarten.effekte.effekt;

import effektkarten.effekte.bedingung.Bedingung;
import effektkarten.effekte.wirkung.Wirkung;
import effektkarten.effekte.ziel.*;
import java.util.*;

public final class ZeitMagieEffekt extends MagieEffekt
{
	private final EndTrigger endTrigger;
	private final int dauer;
	private final ZeitEffektBetrifft betrifft;
	private final EffektZielKartentyp an;
	private final MitWaffe zielWaffe;
	private final Wirkung wirkung;

	public ZeitMagieEffekt(String text, int num, int magieKosten, List<Bedingung> bedingungen,
			EndTrigger endTrigger, int dauer, ZeitEffektBetrifft betrifft, EffektZielKartentyp an,
			MitWaffe zielWaffe, Wirkung wirkung)
	{
		super(text, num, magieKosten, bedingungen);
		this.endTrigger = endTrigger;
		this.dauer = dauer;
		this.betrifft = betrifft;
		this.an = an;
		this.zielWaffe = zielWaffe;
		this.wirkung = wirkung;
	}

	@Override
	public void aktiviere(EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		super.aktiviere(sender, ziel);
		EffektZielCharakter betrifftCharakter = switch(betrifft)
				{
					case SENDER -> sender;
					case ZIEL -> ziel;
				};
		betrifftCharakter.effektZielKarte(an).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), sender, ziel);
	}
}