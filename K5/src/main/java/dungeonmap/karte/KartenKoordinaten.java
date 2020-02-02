package dungeonmap.karte;

public interface KartenKoordinaten
{
	int yk();

	int xk();

	record K(int yk, int xk) implements KartenKoordinaten
	{
		private K(KartenKoordinaten k, int yk, int xk)
		{
			this(k.yk() + yk, k.xk() + xk);
		}
	}

	static KartenKoordinaten k(int yk, int xk)
	{
		return new K(yk, xk);
	}

	static KartenKoordinaten add(KartenKoordinaten k, int yk, int xk)
	{
		return new K(k, yk, xk);
	}
}