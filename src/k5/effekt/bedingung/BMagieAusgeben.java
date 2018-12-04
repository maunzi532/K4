package k5.effekt.bedingung;

import k5.kampf.*;
import k5.karten.*;

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
		return "Magie ausgegeben wird";
	}
}