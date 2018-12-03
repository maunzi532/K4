package charakter;

import sets.*;

public class MTest
{
	public static void main(String[] args)
	{
		M m = new M(new Einstellungen(), new SetV2MittelMapKarten(), new SetV2MapKarten(),
				new SetV2Klassen(), new SetV2Gegner(), new SetV2Waffen(), new SetV2Aktionen());
		m.klassenAuswahl();
		System.out.println(m.dungeonMap.toString());
	}
}