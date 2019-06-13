package effekt.bedingung;

import effekt.*;
import kampf.*;

public class BMagieAusgeben implements Bedingung
{
	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return n.isGibtMagieAus();
	}

	@Override
	public String text()
	{
		return "Magie durch Aktion ausgegeben wird";
	}
}