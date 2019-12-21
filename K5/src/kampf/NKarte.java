package kampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public abstract class NKarte implements EffektZielKarte
{
	private List<AktiverEffekt> aktiveEffekte;

	public NKarte()
	{
		aktiveEffekte = new ArrayList<>();
	}

	public abstract int magieAenderung();

	public int wert(Wirkungswert wert)
	{

		int basiswert = switch(wert)
				{
					case ANGRIFF -> basisWert(Basiswert.ANGRIFF);
					case GESCHWINDIGKEIT -> basisWert(Basiswert.GESCHWINDIGKEIT);
					case VERTEIDIGUNG -> basisWert(Basiswert.VERTEIDIGUNG);
					case MINDESTSCHADEN, MINDESTSCHUTZ, EXTRAANGRIFFE, MAGIE -> 0;
				};
		return basiswert + aktiveEffekte.stream().mapToInt(ae -> ae.wert(wert)).sum();
	}

	public int angriff()
	{
		return wert(Wirkungswert.ANGRIFF);
	}

	public int geschwindigkeit()
	{
		return wert(Wirkungswert.GESCHWINDIGKEIT);
	}

	public int verteidigung()
	{
		return wert(Wirkungswert.VERTEIDIGUNG);
	}

	public int mindestschaden()
	{
		return wert(Wirkungswert.MINDESTSCHADEN);
	}

	public int mindestschutz()
	{
		return wert(Wirkungswert.MINDESTSCHUTZ);
	}

	public int extraangriffe()
	{
		return wert(Wirkungswert.EXTRAANGRIFFE);
	}

	public int setzeangriffe()
	{
		return aktiveEffekte.stream().mapToInt(AktiverEffekt::setzeangriffe).max().orElse(-1);
	}

	public int setzeangriffe(int vorher)
	{
		int se = setzeangriffe();
		return se < 0 ? vorher : se;
	}

	@Override
	public void neuerEffekt(AnEffekt anEffekt, EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		aktiveEffekte.add(new AktiverEffekt(anEffekt, n, ziel, mit));
	}

	public void beendeEffekte(EndTrigger trigger)
	{
		for(int i = 0; i < aktiveEffekte.size();)
		{
			Optional<AktiverEffekt> aktualisiert = aktiveEffekte.get(i).tick(trigger);
			if(aktualisiert.isPresent())
			{
				aktiveEffekte.set(i, aktualisiert.get());
				i++;
			}
			else
			{
				aktiveEffekte.remove(i);
			}
		}
	}

	public List<AktiverEffekt> aktiveEffekte()
	{
		return aktiveEffekte;
	}
}