package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BHauptwaffe implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return mit == MitWaffe.HW;
	}

	@Override
	public String text()
	{
		return "HW";
	}
}