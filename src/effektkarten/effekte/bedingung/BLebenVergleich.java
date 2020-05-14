package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record BLebenVergleich(Vergleich vergleich) implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return vergleich.evaluiere(n.getLeben(), ziel.getLeben());
	}

	@Override
	public String text()
	{
		return "Leben " + vergleich.text + " Leben_(Ziel)";
	}
}