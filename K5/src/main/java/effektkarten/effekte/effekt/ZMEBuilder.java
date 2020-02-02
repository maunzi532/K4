package effektkarten.effekte.effekt;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public class ZMEBuilder
{
	private final EffektZielKartentyp auf;
	private String text;
	private int num = 0;
	private int magieKosten;
	private List<Bedingung> bedingungen = List.of();
	private EndTrigger endTrigger = EndTrigger.ZUG_ENDE;
	private int dauer = 1;
	private boolean betrifftGegner = false;
	private EffektZielKartentyp an;
	private MitWaffe zielWaffe = MitWaffe.VERWENDET;
	private Wirkung wirkung;

	public ZMEBuilder(EffektZielKartentyp auf)
	{
		this.auf = auf;
		an = auf;
	}

	public ZMEBuilder setText(String text)
	{
		this.text = text;
		return this;
	}

	public ZMEBuilder setNum(int num)
	{
		this.num = num;
		return this;
	}

	public ZMEBuilder setMagieKosten(int magieKosten)
	{
		this.magieKosten = magieKosten;
		return this;
	}

	public ZMEBuilder setBedingungen(Bedingung... bedingungen)
	{
		this.bedingungen = List.of(bedingungen);
		return this;
	}

	public ZMEBuilder setEndTrigger(EndTrigger endTrigger)
	{
		this.endTrigger = endTrigger;
		return this;
	}

	public ZMEBuilder setDauer(int dauer)
	{
		this.dauer = dauer;
		return this;
	}

	public ZMEBuilder setBetrifftGegner(boolean betrifftGegner)
	{
		this.betrifftGegner = betrifftGegner;
		return this;
	}

	public ZMEBuilder setAn(EffektZielKartentyp an)
	{
		this.an = an;
		return this;
	}

	public ZMEBuilder setZielWaffe(MitWaffe zielWaffe)
	{
		this.zielWaffe = zielWaffe;
		return this;
	}

	public ZMEBuilder setWirkung(Wirkung wirkung)
	{
		this.wirkung = wirkung;
		return this;
	}

	public ZeitMagieEffekt createZME()
	{
		if(text == null)
		{
			generiereText();
		}
		return new ZeitMagieEffekt(text, num, magieKosten, bedingungen, endTrigger, dauer,
				betrifftGegner, an, zielWaffe, wirkung);
	}

	private void generiereText()
	{
		StringBuilder sb = new StringBuilder();
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
		sb.append("Zahle_").append(magieKosten).append("_Magie_für: ");
		if(betrifftGegner)
		{
			if(an == EffektZielKartentyp.WAFFE)
			{
				sb.append(switch(zielWaffe)
				{
					case HW -> "Ziel_HW: ";
					case NW -> "Ziel_NW: ";
					case VERWENDET -> "Verwendete Waffe (Ziel): ";
				});
			}
			else
			{
				sb.append("Ziel: ");
			}
		}
		else
		{
			if(an == EffektZielKartentyp.WAFFE)
			{
				sb.append(switch(zielWaffe)
				{
					case HW -> "HW: ";
					case NW -> "NW: ";
					case VERWENDET -> "";
				});
			}
			if(an == EffektZielKartentyp.CHARAKTER && auf == EffektZielKartentyp.WAFFE)
			{
				sb.append("Anwender: ");
			}
		}
		sb.append(wirkung.text());
		if(dauer != 1)
		{
			sb.append(" (").append(dauer).append("_Züge)");
		}
		text = sb.toString();
	}
}