package effektkarten.effekte.wirkung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class StatWirkung implements Wirkung
{
	private final String text;
	private final int angriff;
	private final int geschwindigkeit;
	private final int verteidigung;
	private final int mindestschaden;
	private final int mindestschutz;
	private final int magie;
	private final int extraangriffe;
	private final int setzeangriffe;

	public StatWirkung(int angriff, int geschwindigkeit, int verteidigung)
	{
		this(angriff, geschwindigkeit, verteidigung, 0, 0, 0, 0, -1);
	}

	public StatWirkung(int angriff, int geschwindigkeit, int verteidigung, int mindestschaden, int mindestschutz)
	{
		this(angriff, geschwindigkeit, verteidigung, mindestschaden, mindestschutz, 0, 0, -1);
	}

	public StatWirkung(int angriff, int geschwindigkeit, int verteidigung, int mindestschaden, int mindestschutz,
			int magie, int extraangriffe, int setzeangriffe)
	{
		this.angriff = angriff;
		this.geschwindigkeit = geschwindigkeit;
		this.verteidigung = verteidigung;
		this.mindestschaden = mindestschaden;
		this.mindestschutz = mindestschutz;
		this.magie = magie;
		this.extraangriffe = extraangriffe;
		this.setzeangriffe = setzeangriffe;
		text = generateText();
	}

	@Override
	public int wert(Wirkungswert wert, int daten)
	{
		return switch(wert)
				{
					case ANGRIFF -> angriff;
					case GESCHWINDIGKEIT -> geschwindigkeit;
					case VERTEIDIGUNG -> verteidigung;
					case MINDESTSCHADEN -> mindestschaden;
					case MINDESTSCHUTZ -> mindestschutz;
					case EXTRAANGRIFFE -> extraangriffe;
					case MAGIE -> magie;
				};
	}

	private int[] werte()
	{
		return new int[]{angriff, geschwindigkeit, verteidigung, mindestschaden, mindestschutz};
	}

	private String generateText()
	{
		if(setzeangriffe >= 0)
		{
			return "Anwender greift " + setzeangriffe + "-mal an";
		}
		if(extraangriffe != 0)
		{
			return extraangriffe + "_Extraangriff" + (extraangriffe == 1 ? "" : "e");
		}
		if(magie != 0)
		{
			return (magie > 0 ? "+" : "") + magie + " Magie für eigene Aktionen";
		}
		StringBuilder sb = new StringBuilder();
		boolean k = false;
		int[] werte = werte();
		Wirkungswert[] wt = Wirkungswert.values();
		for(int i = 0; i < werte.length; i++)
		{
			if(werte[i] != 0)
			{
				if(k)
					sb.append(", ");

				if(werte[i] > 0)
				{
					sb.append(wt[i].text).append("+").append(werte[i]);
				}
				else
				{
					sb.append(wt[i].text).append(werte[i]);
				}
				k = true;
			}
		}
		return sb.toString();
	}

	@Override
	public int setzeangriffeWert(int daten)
	{
		return setzeangriffe;
	}

	public String text()
	{
		return text;
	}

	public static StatWirkung angriff(int angriff)
	{
		return new StatWirkung(angriff, 0, 0, 0, 0, 0, 0, -1);
	}

	public static StatWirkung geschwindigkeit(int geschwindigkeit)
	{
		return new StatWirkung(0, geschwindigkeit, 0, 0, 0, 0, 0, -1);
	}

	public static StatWirkung verteidigung(int verteidigung)
	{
		return new StatWirkung(0, 0, verteidigung, 0, 0, 0, 0, -1);
	}

	public static StatWirkung mindestschaden(int mindestschaden)
	{
		return new StatWirkung(0, 0, 0, mindestschaden, 0, 0, 0, -1);
	}

	public static StatWirkung mindestschutz(int mindestschutz)
	{
		return new StatWirkung(0, 0, 0, 0, mindestschutz, 0, 0, -1);
	}

	public static StatWirkung magie(int magie)
	{
		return new StatWirkung(0, 0, 0, 0, 0, magie, 0, -1);
	}

	public static StatWirkung extraangriffe(int extraangriffe)
	{
		return new StatWirkung(0, 0, 0, 0, 0, 0, extraangriffe, -1);
	}

	public static StatWirkung setzeangriffe(int setzeangriffe)
	{
		return new StatWirkung(0, 0, 0, 0, 0, 0, 0, setzeangriffe);
	}
}