package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BLebenVergleich(Vergleich vergleich) implements Bedingung
{
	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return vergleich.evaluiere(sender.getLeben(), ziel.getLeben());
	}

	@Override
	public String text()
	{
		return "Leben " + vergleich.text + " Leben_(Ziel)";
	}
}