package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BGesVorteil(int min) implements Bedingung
{
	public BGesVorteil()
	{
		this(1);
	}

	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return sender.getGesAktion() - ziel.getGesAktion() >= min;
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