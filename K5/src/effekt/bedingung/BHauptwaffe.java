package effekt.bedingung;

import effekt.*;
import kampf.*;

public class BHauptwaffe implements Bedingung
{
	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return mit == W.HW;
	}

	@Override
	public String text()
	{
		return "HW";
	}
}