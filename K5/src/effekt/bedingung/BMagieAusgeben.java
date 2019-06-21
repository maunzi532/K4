package effekt.bedingung;

import effekt.*;

public class BMagieAusgeben implements Bedingung
{
	@Override
	public boolean ok(NTI n, NTI ziel, W mit)
	{
		return n.isGibtMagieAus();
	}

	@Override
	public String text()
	{
		return "Magie durch Aktion ausgegeben wird";
	}
}