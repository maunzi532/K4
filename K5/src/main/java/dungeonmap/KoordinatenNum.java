package dungeonmap;

import java.util.*;

public class KoordinatenNum
{
	public final FeldKoordinaten f;
	public final int s;

	public KoordinatenNum(FeldKoordinaten f, int s)
	{
		this.f = f;
		this.s = s;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof KoordinatenNum)) return false;
		KoordinatenNum that = (KoordinatenNum) o;
		return Objects.equals(f, that.f);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(f);
	}
}