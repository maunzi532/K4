package dungeonmap;

import dungeonmap.karte.*;
import java.util.*;

public interface FeldKoordinaten extends KartenKoordinaten
{
	int yf();

	int xf();

	int yfInnen();

	int xfInnen();

	static F f(int yf, int xf)
	{
		return new F(yf, xf);
	}

	static F k(int yk, int xk, int yf, int xf)
	{
		return new F(yk, xk, yf, xf);
	}

	class F implements FeldKoordinaten
	{
		private static final int ywF = MapKarte.ym * 2 + 1;
		private static final int xwF = MapKarte.xm * 2 + 1;

		private final int yf, xf;

		private F(int yf, int xf)
		{
			this.yf = yf;
			this.xf = xf;
		}

		private F(int yk, int xk, int yf, int xf)
		{
			this.yf = yk * ywF + yf;
			this.xf = xk * xwF + xf;
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
		public boolean equals(Object o)
		{
			if(this == o) return true;
			if(!(o instanceof F)) return false;
			F f = (F) o;
			return yf == f.yf &&
					xf == f.xf;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(yf, xf);
		}
	}
}