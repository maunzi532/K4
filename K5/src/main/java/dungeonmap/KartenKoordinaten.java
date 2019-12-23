package dungeonmap;

import java.util.*;

public interface KartenKoordinaten
{
	int yk();

	int xk();

	static KartenKoordinaten k(int yk, int xk)
	{
		return new K(yk, xk);
	}

	class K implements KartenKoordinaten
	{
		private final int yk, xk;

		private K(int yk, int xk)
		{
			this.yk = yk;
			this.xk = xk;
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
		public boolean equals(Object o)
		{
			if(this == o) return true;
			if(!(o instanceof K)) return false;
			K k = (K) o;
			return yk == k.yk &&
					xk == k.xk;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(yk, xk);
		}
	}
}