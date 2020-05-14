package dungeonmap.karte;

public final class FeldPosition1 implements FeldPosition
{
	private static final int ywF = ym * 2 + 1;
	private static final int xwF = xm * 2 + 1;

	private final int yf, xf;

	private FeldPosition1(int yf, int xf)
	{
		this.yf = yf;
		this.xf = xf;
	}

	private FeldPosition1(int yk, int xk, int yf, int xf)
	{
		this(yk * ywF + yf, xk * xwF + xf);
	}

	private FeldPosition1(FeldPosition fp, int yf, int xf)
	{
		this(fp.yf() + yf, fp.xf() + xf);
	}

	@Override
	public int yk()
	{
		return yf / ywF;
	}

	@Override
	public int xk()
	{
		return xf / xwF;
	}

	@Override
	public int yf()
	{
		return yf;
	}

	@Override
	public int xf()
	{
		return xf;
	}

	@Override
	public int yfInnen()
	{
		return yf % ywF;
	}

	@Override
	public int xfInnen()
	{
		return xf % xwF;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof FeldPosition1 obj1))
			return false;
		return yf == obj1.yf && xf == obj1.xf;
	}

	@Override
	public int hashCode()
	{
		return yf * 2361 + xf;
	}

	public static FeldPosition fp(int yf, int xf)
	{
		return new FeldPosition1(yf, xf);
	}

	public static FeldPosition mkfp(int yk, int xk, int yf, int xf)
	{
		return new FeldPosition1(yk, xk, yf, xf);
	}

	public static FeldPosition addieren(FeldPosition fp, int yf, int xf)
	{
		return new FeldPosition1(fp, yf, xf);
	}
}