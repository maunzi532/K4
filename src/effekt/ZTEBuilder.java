package effekt;

import effekt.bedingung.*;
import effekt.wirkung.*;
import java.util.*;
import karten.*;

public class ZTEBuilder
{
	private EffektAn auf;
	private String text;
	private int num = 0;
	private StartTrigger2 startTrigger;
	private TriggerSeite startSeite = TriggerSeite.EIGENE;
	private List<Bedingung> bedingungen = List.of();
	private EndTrigger2 endTrigger = EndTrigger2.ZUG_ENDE;
	private int dauer = 1;
	private boolean betrifftGegner = false;
	private EffektAn an;
	private W zielWaffe = W.OK;
	private ZeitWirkung wirkung;

	public ZTEBuilder(EffektAn auf)
	{
		this.auf = auf;
		an = auf;
	}

	public ZTEBuilder setText(String text)
	{
		this.text = text;
		return this;
	}

	public ZTEBuilder setNum(int num)
	{
		this.num = num;
		return this;
	}

	public ZTEBuilder setStartTrigger(StartTrigger2 startTrigger)
	{
		this.startTrigger = startTrigger;
		return this;
	}

	public ZTEBuilder setStartSeite(TriggerSeite startSeite)
	{
		this.startSeite = startSeite;
		return this;
	}

	public ZTEBuilder setBedingungen(List<Bedingung> bedingungen)
	{
		this.bedingungen = bedingungen;
		return this;
	}

	public ZTEBuilder setBedingungen(Bedingung... bedingungen)
	{
		this.bedingungen = List.of(bedingungen);
		return this;
	}

	public ZTEBuilder setEndTrigger(EndTrigger2 endTrigger)
	{
		this.endTrigger = endTrigger;
		return this;
	}

	public ZTEBuilder setDauer(int dauer)
	{
		this.dauer = dauer;
		return this;
	}

	public ZTEBuilder setBetrifftGegner(boolean betrifftGegner)
	{
		this.betrifftGegner = betrifftGegner;
		return this;
	}

	public ZTEBuilder setAn(EffektAn an)
	{
		this.an = an;
		return this;
	}

	public ZTEBuilder setZielWaffe(W zielWaffe)
	{
		this.zielWaffe = zielWaffe;
		return this;
	}

	public ZTEBuilder setWirkung(ZeitWirkung wirkung)
	{
		this.wirkung = wirkung;
		return this;
	}

	public ZeitTriggerEffekt createZTE()
	{
		return new ZeitTriggerEffekt(text, num, startTrigger, startSeite, bedingungen, endTrigger, dauer,
				betrifftGegner, an, zielWaffe, wirkung);
	}

	public ZTEBuilder generateText()
	{
		StringBuilder sb = new StringBuilder();
		if(startSeite == TriggerSeite.GEGNER)
		{
			sb.append("Wenn angegriffen: ");
		}
		if(!bedingungen.isEmpty())
		{
			sb.append("Wenn ");
			for(int i = 0; i < bedingungen.size(); i++)
			{
				if(i == bedingungen.size() - 1)
					sb.append(bedingungen.get(i).text()).append(": ");
				else
					sb.append(bedingungen.get(i).text()).append(" und ");
			}
		}
		if(betrifftGegner)
		{
			if(an == EffektAn.WAFFE)
			{
				sb.append(switch(zielWaffe)
				{
					case HW -> "Ziel HW: ";
					case NW -> "Ziel NW: ";
					case OK -> "Verwendete Waffe (Ziel): ";
				});
			}
			else
			{
				sb.append("Ziel: ");
			}
		}
		else
		{
			if(an == EffektAn.WAFFE)
			{
				sb.append(switch(zielWaffe)
				{
					case HW -> "HW: ";
					case NW -> "NW: ";
					case OK -> "";
				});
			}
			if(an == EffektAn.CHARAKTER && auf == EffektAn.WAFFE)
			{
				sb.append("Anwender: ");
			}
		}
		sb.append(wirkung.text());
		if(dauer != 1)
		{
			sb.append(" (").append(dauer).append(" Züge)");
		}
		text = sb.toString();
		return this;
	}
}