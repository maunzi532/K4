package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BGesVorteil implements Bedingung
{
	public final int min;

	public BGesVorteil()
	{
		min = 1;
	}

	public BGesVorteil(int min)
	{
		this.min = min;
	}

	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return n.getGesAktion() - ziel.getGesAktion() >= min;
	}

	@Override
	public String text()
	{
		if(min == 1)
			return "Ges > Ges_(Ziel)";
		else
			return "Ges ≥ Ges_(Ziel) +_" + min;
	}
}