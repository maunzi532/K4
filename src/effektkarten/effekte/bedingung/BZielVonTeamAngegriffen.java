package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BZielVonTeamAngegriffen() implements Bedingung
{
	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return ziel.getAngegriffenVon().stream().anyMatch(e -> e != sender);
	}

	@Override
	public String text()
	{
		return "Ziel in diesem Zug von Charakter außer Anwender angegriffen wurde";
	}
}