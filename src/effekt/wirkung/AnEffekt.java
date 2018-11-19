package effekt.wirkung;

import effekt.*;

public class AnEffekt
{
	public final Wirkung wirkung;
	public final EndTrigger endTrigger;
	public int dauer;

	public AnEffekt(Wirkung wirkung, EndTrigger endTrigger, int dauer)
	{
		this.wirkung = wirkung;
		this.endTrigger = endTrigger;
		this.dauer = dauer;
	}

	public boolean tick(EndTrigger trigger)
	{
		if(trigger == endTrigger)
		{
			dauer--;
			return dauer <= 0;
		}
		return false;
	}

	public int angriff()
	{
		return wirkung.angriff();
	}

	public int geschwindigkeit()
	{
		return wirkung.geschwindigkeit();
	}

	public int verteidigung()
	{
		return wirkung.verteidigung();
	}

	public int mindestschaden()
	{
		return wirkung.mindestschaden();
	}

	public int mindestschutz()
	{
		return wirkung.mindestschutz();
	}

	public int extraangriffe()
	{
		return wirkung.extraangriffe();
	}

	public int setzeangriffe()
	{
		return wirkung.setzeangriffe();
	}

	public String text()
	{
		return wirkung.text();
	}
}