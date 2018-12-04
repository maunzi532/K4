package k5.effekt;

import k5.effekt.bedingung.*;
import k5.effekt.direktwirkung.*;
import java.util.*;

public class DTEBuilder
{
	private String text;
	private int num = 0;
	private StartTrigger startTrigger;
	private StartTriggerSeite startTriggerSeite = StartTriggerSeite.EIGENE;
	private List<Bedingung> bedingungen = List.of();
	private DirektWirkung direktWirkung;

	public DTEBuilder setText(String text)
	{
		this.text = text;
		return this;
	}

	public DTEBuilder setNum(int num)
	{
		this.num = num;
		return this;
	}

	public DTEBuilder setStartTrigger(StartTrigger startTrigger)
	{
		this.startTrigger = startTrigger;
		return this;
	}

	public DTEBuilder setStartTriggerSeite(StartTriggerSeite startTriggerSeite)
	{
		this.startTriggerSeite = startTriggerSeite;
		return this;
	}

	public DTEBuilder setBedingungen(Bedingung... bedingungen)
	{
		this.bedingungen = List.of(bedingungen);
		return this;
	}

	public DTEBuilder setDirektWirkung(DirektWirkung direktWirkung)
	{
		this.direktWirkung = direktWirkung;
		return this;
	}

	public DirektTriggerEffekt createDTE()
	{
		if(text == null)
			generateText();
		return new DirektTriggerEffekt(text, num, startTrigger, startTriggerSeite, bedingungen, direktWirkung);
	}

	public DTEBuilder generateText()
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
		sb.append(direktWirkung.text());
		text = sb.toString();
		return this;
	}
}