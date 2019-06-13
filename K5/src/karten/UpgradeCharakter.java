package karten;

public abstract class UpgradeCharakter
{
	protected Charakterkarte charakter;
	protected int[] werteUpgrades;

	public Charakterkarte charakterkarte()
	{
		return new Charakterkarte(charakter.name(), charakter.getAngriff() + werteUpgrades[0],
				charakter.getWaffenwert() + werteUpgrades[1],
				charakter.getGeschwindigkeit() + werteUpgrades[2],
				charakter.getVerteidigung() + werteUpgrades[3],
				charakter.getLeben() + werteUpgrades[4], charakter.getExp(x()), charakter.effekte());
	}

	protected int x()
	{
		return -1;
	}
}