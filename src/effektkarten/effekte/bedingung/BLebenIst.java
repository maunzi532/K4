package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record BLebenIst(Vergleich vergleich, int zu) implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return vergleich.evaluiere(n.getLeben(), zu);
	}

	@Override
	public String text()
	{
		return "Leben " + vergleich.text + " " + zu;
	}
}