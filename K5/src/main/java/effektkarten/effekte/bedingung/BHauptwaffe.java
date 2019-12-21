package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BHauptwaffe implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return n.getMit() == MitWaffe.HW || nichtMit == MitWaffe.HW;
	}

	@Override
	public String text()
	{
		return "HW";
	}
}