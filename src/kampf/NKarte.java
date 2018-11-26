package kampf;

import effekt.*;
import effekt.wirkung.*;
import java.util.*;

public abstract class NKarte
{
	private List<AnEffekt> aktiveEffekte;

	public NKarte()
	{
		aktiveEffekte = new ArrayList<>();
	}

	public abstract int basisAngriff();

	public abstract int basisGes();

	public abstract int basisVert();

	public abstract int magieAenderung();

	public abstract int basisWert(Basiswert wert);

	public int angriff()
	{
		return basisAngriff() + aktiveEffekte().stream().mapToInt(AnEffekt::angriff).sum();
	}

	public int geschwindigkeit()
	{
		return basisGes() + aktiveEffekte().stream().mapToInt(AnEffekt::geschwindigkeit).sum();
	}

	public int verteidigung()
	{
		return basisVert() + aktiveEffekte().stream().mapToInt(AnEffekt::verteidigung).sum();
	}

	public int mindestschaden()
	{
		return aktiveEffekte().stream().mapToInt(AnEffekt::mindestschaden).sum();
	}

	public int mindestschutz()
	{
		return aktiveEffekte().stream().mapToInt(AnEffekt::mindestschutz).sum();
	}

	public int extraangriffe()
	{
		return aktiveEffekte().stream().mapToInt(AnEffekt::extraangriffe).sum();
	}

	public int setzeangriffe()
	{
		return aktiveEffekte().stream().mapToInt(AnEffekt::setzeangriffe).max().orElse(-1);
	}

	public int setzeangriffe(int vorher)
	{
		int se = setzeangriffe();
		return se < 0 ? vorher : se;
	}

	public void neuerEffekt(AnEffekt anEffekt)
	{
		aktiveEffekte.add(anEffekt);
	}

	public void beendeEffekte(EndTrigger trigger)
	{
		aktiveEffekte.removeIf(e -> e.tick(trigger));
	}

	public List<AnEffekt> aktiveEffekte()
	{
		return aktiveEffekte;
	}
}