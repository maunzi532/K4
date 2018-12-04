package k5.effekt;

import k5.effekt.bedingung.*;
import k5.effekt.wirkung.*;
import java.util.*;
import k5.karten.*;

public class ZTEBuilder
{
	private NKartentyp auf;
	private String text;
	private int num = 0;
	private StartTrigger startTrigger;
	private StartTriggerSeite startTriggerSeite = StartTriggerSeite.EIGENE;
	private List<Bedingung> bedingungen = List.of();
	private EndTrigger endTrigger = EndTrigger.ZUG_ENDE;
	private int dauer = 1;
	private boolean betrifftGegner = false;
	private NKartentyp an;
	private W zielWaffe = W.OK;
	private Wirkung wirkung;

	public ZTEBuilder(NKartentyp auf)
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

	public ZTEBuilder setStartTrigger(StartTrigger startTrigger)
	{
		this.startTrigger = startTrigger;
		return this;
	}

	public ZTEBuilder setStartTriggerSeite(StartTriggerSeite startTriggerSeite)
	{
		this.startTriggerSeite = startTriggerSeite;
		return this;
	}

	public ZTEBuilder setBedingungen(Bedingung... bedingungen)
	{
		this.bedingungen = List.of(bedingungen);
		return this;
	}

	public ZTEBuilder setEndTrigger(EndTrigger endTrigger)
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

	public ZTEBuilder setAn(NKartentyp an)
	{
		this.an = an;
		return this;
	}

	public ZTEBuilder setZielWaffe(W zielWaffe)
	{
		this.zielWaffe = zielWaffe;
		return this;
	}

	public ZTEBuilder setWirkung(Wirkung wirkung)
	{
		this.wirkung = wirkung;
		return this;
	}

	public ZeitTriggerEffekt createZTE()
	{
		if(text == null)
		{
			generateText();
		}
		return new ZeitTriggerEffekt(text, num, startTrigger, startTriggerSeite, bedingungen, endTrigger, dauer,
				betrifftGegner, an, zielWaffe, wirkung);
	}

	public ZTEBuilder generateText()
	{
		StringBuilder sb = new StringBuilder();
		if(startTriggerSeite == StartTriggerSeite.GEGNER)
		{
			sb.append("Wenn_angegriffen: ");
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
		if(startTrigger == StartTrigger.ZUGENDE)
		{
			sb.append("Nächster_Zug: ");
		}
		if(betrifftGegner)
		{
			if(an == NKartentyp.WAFFE)
			{
				sb.append(switch(zielWaffe)
				{
					case HW -> "Ziel_HW: ";
					case NW -> "Ziel_NW: ";
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
			if(an == NKartentyp.WAFFE)
			{
				sb.append(switch(zielWaffe)
				{
					case HW -> "HW: ";
					case NW -> "NW: ";
					case OK -> "";
				});
			}
			if(an == NKartentyp.CHARAKTER && auf == NKartentyp.WAFFE)
			{
				sb.append("Anwender: ");
			}
		}
		sb.append(wirkung.text());
		if(endTrigger == EndTrigger.ZUG_ENDE)
		{
			int dauer1 = dauer;
			if(startTrigger == StartTrigger.ZUGENDE)
			{
				dauer1--;
			}
			if(dauer1 != 1)
			{
				sb.append(" (").append(dauer1).append("_Züge)");
			}
		}
		if(endTrigger == EndTrigger.NACH_ANGEGRIFFEN)
		{
			sb.append(", endet nachdem Anwender angegriffen wurde");
		}
		text = sb.toString();
		return this;
	}
}