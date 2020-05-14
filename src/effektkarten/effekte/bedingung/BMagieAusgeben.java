package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BMagieAusgeben() implements Bedingung
{
	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return sender.isGibtMagieAus();
	}

	@Override
	public String text()
	{
		return "Magie durch Aktion ausgegeben wird";
	}
}