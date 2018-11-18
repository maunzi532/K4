package kampf;

import effekt.wirkung.*;
import java.util.*;

public abstract class NKarte
{
	private List<ZeitWirkung> aktiveEffekte;

	public NKarte()
	{
		aktiveEffekte = new ArrayList<>();
	}

	public abstract int basisAngriff();

	public abstract int basisGes();

	public abstract int basisVert();

	public abstract int magieAenderung();

	public int angriff()
	{
		return basisAngriff() + aktiveEffekte().stream().mapToInt(ZeitWirkung::angriff).sum();
	}

	public int geschwindigkeit()
	{
		return basisGes() + aktiveEffekte().stream().mapToInt(ZeitWirkung::geschwindigkeit).sum();
	}

	public int verteidigung()
	{
		return basisVert() + aktiveEffekte().stream().mapToInt(ZeitWirkung::verteidigung).sum();
	}

	public int mindestschaden()
	{
		return aktiveEffekte().stream().mapToInt(ZeitWirkung::mindestschaden).sum();
	}

	public int mindestschutz()
	{
		return aktiveEffekte().stream().mapToInt(ZeitWirkung::mindestschutz).sum();
	}

	public int extraangriffe()
	{
		return aktiveEffekte().stream().mapToInt(ZeitWirkung::extraangriffe).sum();
	}

	public int setzeangriffe()
	{
		return aktiveEffekte().stream().mapToInt(ZeitWirkung::setzeangriffe).max().orElse(-1);
	}

	public int setzeangriffe(int vorher)
	{
		int se = setzeangriffe();
		return se < 0 ? vorher : se;
	}

	public void neuerEffekt(ZeitWirkung wirkung)
	{
		aktiveEffekte.add(wirkung);
	}

	public List<ZeitWirkung> aktiveEffekte()
	{
		return aktiveEffekte;
	}
}