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

	public int basisMagieAenderung()
	{
		return 0;
	}

	public int wert(Wirkungswert wert)
	{
		int basiswert = switch(wert)
				{
					case ANGRIFF -> basisWert(Basiswert.ANGRIFF);
					case GESCHWINDIGKEIT -> basisWert(Basiswert.GESCHWINDIGKEIT);
					case VERTEIDIGUNG -> basisWert(Basiswert.VERTEIDIGUNG);
					case MAGIE -> basisMagieAenderung();
					case MINDESTSCHADEN, MINDESTSCHUTZ, EXTRAANGRIFFE -> 0;
				};
		return basiswert + aktiveEffekte.stream().mapToInt(ae -> ae.wert(wert)).sum();
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
	public void neuerEffekt(AnEffekt anEffekt, EffektZielCharakter n, EffektZielCharakter ziel)
	{
		aktiveEffekte.add(new AktiverEffekt(anEffekt, n, ziel));
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