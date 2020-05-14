package dungeonmap.karte;

public final class MapKartenPosition1 implements MapKartenPosition
{
	private final int yk, xk;

	private MapKartenPosition1(int yk, int xk)
	{
		this.yk = yk;
		this.xk = xk;
	}

	private MapKartenPosition1(MapKartenPosition mkp, int yk, int xk)
	{
		this(mkp.yk() + yk, mkp.xk() + xk);
	}

	@Override
	public int yk()
	{
		return yk;
	}

	@Override
	public int xk()
	{
		return xk;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof MapKartenPosition1 obj1))
			return false;
		return yk == obj1.yk && xk == obj1.xk;
	}

	@Override
	public int hashCode()
	{
		return yk * 2361 + xk;
	}

	public static MapKartenPosition mkp(int yk, int xk)
	{
		return new MapKartenPosition1(yk, xk);
	}

	public static MapKartenPosition addieren(MapKartenPosition mkp, int yk, int xk)
	{
		return new MapKartenPosition1(mkp, yk, xk);
	}
}