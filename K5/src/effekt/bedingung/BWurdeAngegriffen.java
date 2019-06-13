package effekt.bedingung;

import effekt.*;
import kampf.*;

public class BWurdeAngegriffen implements Bedingung
{
	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return !n.getAngegriffenVon().isEmpty();
	}

	@Override
	public String text()
	{
		return "Anwender in diesem Zug angegriffen wurde";
	}
}