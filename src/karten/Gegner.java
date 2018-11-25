package karten;

public class Gegner extends UpgradeCharakter
{
	public int x;

	public Gegner(Charakterkarte charakter)
	{
		this.charakter = charakter;
		werteUpgrades = new int[5];
		x = -1;
	}

	public Gegner(XGegnerKarte xKarte, int x)
	{
		charakter = xKarte;
		werteUpgrades = new int[5];
		for(int i = 0; i < 5; i++)
		{
			if(xKarte.xWerte[i])
				werteUpgrades[i] = x;
		}
		this.x = x;
	}

	@Override
	protected int x()
	{
		return x;
	}
}