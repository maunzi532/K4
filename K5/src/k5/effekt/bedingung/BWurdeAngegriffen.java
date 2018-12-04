package k5.effekt.bedingung;

import k5.kampf.*;
import k5.karten.*;

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