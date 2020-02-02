package dungeonmap.karte;

public interface FeldKoordinaten extends KartenKoordinaten
{
	int ym = 2;
	int xm = 2;

	int yf();

	int xf();

	int yfInnen();

	int xfInnen();

	record F(int yf, int xf) implements FeldKoordinaten
	{
		private static final int ywF = ym * 2 + 1;
		private static final int xwF = xm * 2 + 1;

		private F(int yk, int xk, int yf, int xf)
		{
			this(yk * ywF + yf, xk * xwF + xf);
		}

		private F(FeldKoordinaten f, int yf, int xf)
		{
			this(f.yf() + yf, f.xf() + xf);
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
		public int yk()
		{
			return yf / ywF;
		}

		@Override
		public int xk()
		{
			return xf / xwF;
		}
	}

	static F f(int yf, int xf)
	{
		return new F(yf, xf);
	}

	static F k(int yk, int xk, int yf, int xf)
	{
		return new F(yk, xk, yf, xf);
	}

	static F add(FeldKoordinaten f, int yf, int xf)
	{
		return new F(f, yf, xf);
	}
}