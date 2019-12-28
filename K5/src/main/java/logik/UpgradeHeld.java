package logik;

import effektkarten.karten.*;

public class UpgradeHeld
{
	private Charakterkarte charakter;
	private int[] werteUpgrades;

	public UpgradeHeld(Charakterkarte charakter, int[] werteUpgrades)
	{
		this.charakter = charakter;
		this.werteUpgrades = werteUpgrades;
	}

	public Charakterkarte charakterkarte()
	{
		return new Charakterkarte(charakter.name(), charakter.getAngriff() + werteUpgrades[0],
				charakter.getWaffenwert() + werteUpgrades[1],
				charakter.getGeschwindigkeit() + werteUpgrades[2],
				charakter.getVerteidigung() + werteUpgrades[3],
				charakter.getLeben() + werteUpgrades[4], charakter.effekte());
	}
}