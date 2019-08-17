package logik;

import java.util.*;

public class ExpTrank
{
	public final int exp;

	public ExpTrank(int exp)
	{
		this.exp = exp;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof ExpTrank)) return false;
		ExpTrank expTrank = (ExpTrank) o;
		return exp == expTrank.exp;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(exp);
	}
}