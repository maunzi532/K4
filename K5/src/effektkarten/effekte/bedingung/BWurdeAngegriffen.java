package effektkarten.effekte.bedingung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public class BWurdeAngegriffen implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return !n.getAngegriffenVon().isEmpty();
	}

	@Override
	public String text()
	{
		return "Anwender in diesem Zug angegriffen wurde";
	}
}