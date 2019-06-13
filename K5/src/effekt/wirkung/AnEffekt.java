package effekt.wirkung;

import effekt.*;
import kampf.*;

public class AnEffekt
{
	public final Wirkung wirkung;
	public final EndTrigger endTrigger;
	public int dauer;
	public int daten;

	public AnEffekt(Wirkung wirkung, EndTrigger endTrigger, int dauer)
	{
		this.wirkung = wirkung;
		this.endTrigger = endTrigger;
		this.dauer = dauer;
	}

	public void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		daten = wirkung.triggere(n, ziel, mit);
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
		return wirkung.getWert(Wirkungswert.ANGRIFF, daten);
	}

	public int geschwindigkeit()
	{
		return wirkung.getWert(Wirkungswert.GESCHWINDIGKEIT, daten);
	}

	public int verteidigung()
	{
		return wirkung.getWert(Wirkungswert.VERTEIDIGUNG, daten);
	}

	public int mindestschaden()
	{
		return wirkung.getWert(Wirkungswert.MINDESTSCHADEN, daten);
	}

	public int mindestschutz()
	{
		return wirkung.getWert(Wirkungswert.MINDESTSCHUTZ, daten);
	}

	public int extraangriffe()
	{
		return wirkung.getWert(Wirkungswert.EXTRAANGRIFFE, daten);
	}

	public int magie()
	{
		return wirkung.getWert(Wirkungswert.MAGIE, daten);
	}

	public int setzeangriffe()
	{
		return wirkung.getSetzeangriffe(daten);
	}

	public String text()
	{
		return wirkung.text();
	}
}