package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BHauptwaffe() implements Bedingung
{
	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return sender.getMit() == MitWaffe.HW || nichtMit == MitWaffe.HW;
	}

	@Override
	public String text()
	{
		return "HW";
	}
}