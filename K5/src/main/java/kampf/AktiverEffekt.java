package kampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public class AktiverEffekt
{
	public final Wirkung wirkung;
	public final EndTrigger endTrigger;
	public final int dauer;
	public final int daten;

	public AktiverEffekt(AnEffekt anEffekt, EffektZielCharakter n, EffektZielCharakter ziel)
	{
		this(anEffekt.wirkung, anEffekt.endTrigger, anEffekt.dauer, anEffekt.wirkung.triggere(n, ziel));
	}

	private AktiverEffekt(Wirkung wirkung, EndTrigger endTrigger, int dauer, int daten)
	{
		this.wirkung = wirkung;
		this.endTrigger = endTrigger;
		this.dauer = dauer;
		this.daten = daten;
	}

	public Optional<AktiverEffekt> tick(EndTrigger trigger)
	{
		if(trigger == endTrigger)
		{
			if(dauer > 1)
				return Optional.of(new AktiverEffekt(wirkung, endTrigger, dauer - 1, daten));
			else
				return Optional.empty();
		}
		else
			return Optional.of(this);
	}

	public int wert(Wirkungswert wert)
	{
		return wirkung.wert(wert, daten);
	}

	public int setzeangriffe()
	{
		return wirkung.setzeangriffeWert(daten);
	}

	public String text()
	{
		return wirkung.text();
	}
}