package effekt;

import effekt.bedingung.*;
import effekt.wirkung.*;
import java.util.*;
import kampf.*;
import karten.*;

public class ZeitTriggerEffekt extends TriggerEffekt
{
	private final EndTrigger2 endTrigger;
	private final int dauer;
	private final boolean betrifftGegner;
	private final EffektAn an;
	private final W zielWaffe;
	private final ZeitWirkung wirkung;

	public ZeitTriggerEffekt(String text, int num, StartTrigger2 startTrigger,
			TriggerSeite startSeite, List<Bedingung> bedingungen,
			EndTrigger2 endTrigger, int dauer, boolean betrifftGegner, EffektAn an,
			W zielWaffe, ZeitWirkung wirkung)
	{
		super(startTrigger.symbol, text, num, startTrigger, startSeite, bedingungen);
		this.endTrigger = endTrigger;
		this.dauer = dauer;
		this.betrifftGegner = betrifftGegner;
		this.an = an;
		this.zielWaffe = zielWaffe;
		this.wirkung = wirkung;
	}

	public EndTrigger2 getEndTrigger()
	{
		return endTrigger;
	}

	public int getDauer()
	{
		return dauer;
	}

	public ZeitWirkung getWirkung()
	{
		return wirkung;
	}

	public boolean isBetrifftGegner()
	{
		return betrifftGegner;
	}

	public EffektAn getAn()
	{
		return an;
	}

	public W getZielWaffe()
	{
		return zielWaffe;
	}

	@Override
	public void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		NTeilnehmer betrifft = betrifftGegner ? ziel : n;
		W zielWaffe1 = zielWaffe == W.OK ? mit : zielWaffe;
		switch(an)
		{
			case CHARAKTER -> betrifft.nCharakter().neuerEffekt(wirkung);
			case WAFFE -> betrifft.nWaffe(zielWaffe1).neuerEffekt(wirkung);
			case AKTION -> betrifft.nAktion().neuerEffekt(wirkung);
		}
	}
}