package map2;

import effektkarten.karten.*;

public class UpgradeHeld extends UpgradeCharakter
{
	public UpgradeHeld(Charakterkarte charakter, int[] werteUpgrades)
	{
		this.charakter = charakter;
		this.werteUpgrades = werteUpgrades;
	}
}