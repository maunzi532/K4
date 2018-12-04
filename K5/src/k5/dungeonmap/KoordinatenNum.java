package k5.dungeonmap;

import java.util.*;

public class KoordinatenNum
{
	public final int y, x, s;

	public KoordinatenNum(int y, int x, int s)
	{
		this.y = y;
		this.x = x;
		this.s = s;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof KoordinatenNum)) return false;
		KoordinatenNum that = (KoordinatenNum) o;
		return y == that.y &&
				x == that.x;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(y, x);
	}
}