package logik;

import effektkarten.karten.*;

public final class UpgradeHeld
{
	private final Charakterkarte basiskarte;
	private final int[] werteUpgrades;

	public UpgradeHeld(Charakterkarte basiskarte, int[] werteUpgrades)
	{
		this.basiskarte = basiskarte;
		this.werteUpgrades = werteUpgrades;
	}

	public Charakterkarte charakterkarte()
	{
		return new Charakterkarte(basiskarte.name(), basiskarte.angriff() + werteUpgrades[0],
				basiskarte.waffenwert() + werteUpgrades[1],
				basiskarte.geschwindigkeit() + werteUpgrades[2],
				basiskarte.verteidigung() + werteUpgrades[3],
				basiskarte.leben() + werteUpgrades[4], 0, basiskarte.effekte());
	}
}