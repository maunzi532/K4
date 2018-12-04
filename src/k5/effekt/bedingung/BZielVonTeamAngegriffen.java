package k5.effekt.bedingung;

import k5.kampf.*;
import k5.karten.*;

public class BZielVonTeamAngegriffen implements Bedingung
{
	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return ziel.getAngegriffenVon().stream().anyMatch(e -> e != n);
	}

	@Override
	public String text()
	{
		return "Ziel in diesem Zug von Charakter außer Anwender angegriffen wurde";
	}
}