package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record BMagieAusgeben() implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return n.isGibtMagieAus();
	}

	@Override
	public String text()
	{
		return "Magie durch Aktion ausgegeben wird";
	}
}