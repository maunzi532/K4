package effekt.bedingung;

import kampf.*;
import karten.*;

public class BWenigerBasisAngriff implements Bedingung
{
	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return n.nCharakter().basisAngriff() < ziel.nCharakter().basisAngriff();
	}

	@Override
	public String text()
	{
		return "A (Basis) < A (Ziel)";
	}
}