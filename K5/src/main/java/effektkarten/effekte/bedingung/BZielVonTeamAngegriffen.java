package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BZielVonTeamAngegriffen implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return ziel.getAngegriffenVon().stream().anyMatch(e -> e != n);
	}

	@Override
	public String text()
	{
		return "Ziel in diesem Zug von Charakter außer Anwender angegriffen wurde";
	}
}