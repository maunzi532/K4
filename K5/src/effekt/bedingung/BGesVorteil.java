package effekt.bedingung;

import effekt.*;

public class BGesVorteil implements Bedingung
{
	private final int min;

	public BGesVorteil()
	{
		min = 1;
	}

	public BGesVorteil(int min)
	{
		this.min = min;
	}

	@Override
	public boolean ok(NTI n, NTI ziel, W mit)
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