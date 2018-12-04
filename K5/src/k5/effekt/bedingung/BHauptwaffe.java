package k5.effekt.bedingung;

import k5.kampf.*;
import k5.karten.*;

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