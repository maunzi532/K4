package effekt.bedingung;

import effekt.*;

public class BHauptwaffe implements Bedingung
{
	@Override
	public boolean ok(NTI n, NTI ziel, W mit)
	{
		return mit == W.HW;
	}

	@Override
	public String text()
	{
		return "HW";
	}
}