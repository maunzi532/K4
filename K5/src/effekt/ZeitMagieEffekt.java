package effekt;

import effekt.bedingung.*;
import effekt.wirkung.*;
import java.util.*;
import kampf.*;
import karten.*;

public class ZeitMagieEffekt extends MagieEffekt
{
	private final EndTrigger endTrigger;
	private final int dauer;
	private final boolean betrifftGegner;
	private final NKartentyp an;
	private final W zielWaffe;
	private final Wirkung wirkung;

	public ZeitMagieEffekt(String text, int num, int magieKosten, List<Bedingung> bedingungen,
			EndTrigger endTrigger, int dauer, boolean betrifftGegner, NKartentyp an,
			W zielWaffe, Wirkung wirkung)
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
	public void aktiviere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		super.aktiviere(n, ziel, mit);
		NTeilnehmer betrifft = betrifftGegner ? ziel : n;
		W zielWaffe1 = zielWaffe == W.OK ? mit : zielWaffe;
		switch(an)
		{
			case CHARAKTER -> betrifft.nCharakter().neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
			case WAFFE -> betrifft.nWaffe(zielWaffe1).neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
			case AKTION -> betrifft.nAktion().neuerEffekt(new AnEffekt(wirkung, endTrigger, dauer), n, ziel, mit);
		}
	}
}