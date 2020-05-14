package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BLebenIst(Vergleich vergleich, int zu) implements Bedingung
{
	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return vergleich.evaluiere(sender.getLeben(), zu);
	}

	@Override
	public String text()
	{
		return "Leben " + vergleich.text + ' ' + zu;
	}
}