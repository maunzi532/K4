package effektkarten.effekte.bedingung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public class BMagieAusgeben implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return n.isGibtMagieAus();
	}

	@Override
	public String text()
	{
		return "Magie durch Aktion ausgegeben wird";
	}
}