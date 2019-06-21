package effekt.bedingung;

import effekt.*;

public class BWurdeAngegriffen implements Bedingung
{
	@Override
	public boolean ok(NTI n, NTI ziel, W mit)
	{
		return !n.getAngegriffenVon().isEmpty();
	}

	@Override
	public String text()
	{
		return "Anwender in diesem Zug angegriffen wurde";
	}
}