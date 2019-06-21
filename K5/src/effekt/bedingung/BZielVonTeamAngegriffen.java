package effekt.bedingung;

import effekt.*;

public class BZielVonTeamAngegriffen implements Bedingung
{
	@Override
	public boolean ok(NTI n, NTI ziel, W mit)
	{
		return ziel.getAngegriffenVon().stream().anyMatch(e -> e != n);
	}

	@Override
	public String text()
	{
		return "Ziel in diesem Zug von Charakter außer Anwender angegriffen wurde";
	}
}