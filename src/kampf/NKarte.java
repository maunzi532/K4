package kampf;

import effektkarten.effekte.wirkung.Wirkungswert;
import effektkarten.effekte.ziel.*;
import java.util.*;

public abstract class NKarte implements EffektZielKarte
{
	private final List<AktiverEffekt> aktiveEffekte;

	protected NKarte()
	{
		aktiveEffekte = new ArrayList<>();
	}

	public int basisMagieAenderung()
	{
		return 0;
	}

	public final int wert(Wirkungswert wert)
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

	public final int setzeangriffe()
	{
		return aktiveEffekte.stream().mapToInt(AktiverEffekt::setzeangriffe).max().orElse(-1);
	}

	public final int setzeangriffe(int vorher)
	{
		int se = setzeangriffe();
		return se < 0 ? vorher : se;
	}

	@Override
	public final void neuerEffekt(AnEffekt anEffekt, EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		aktiveEffekte.add(new AktiverEffekt(anEffekt, sender, ziel));
	}

	public final void beendeEffekte(EndTrigger trigger)
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

	public final List<AktiverEffekt> aktiveEffekte()
	{
		return aktiveEffekte;
	}
}