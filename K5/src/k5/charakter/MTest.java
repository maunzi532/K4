package k5.charakter;

import k5.dungeonmap.*;
import k5.sets.*;

public class MTest
{
	public static void main(String[] args)
	{
		Hauptklasse hauptklasse = new Hauptklasse(new Einstellungen(), new SetV2MittelMapKarten(), new SetV2MapKarten(),
				new SetV2Klassen(), new SetV2Gegner(), new SetV2Waffen(), new SetV2Aktionen());
		//m.klassenAuswahl();
		hauptklasse.klassenAuswahl("K", "B");
		MapBild mapBild = new MapBild(hauptklasse.dungeonMap);
		System.out.println(mapBild.erstelleTextBild(hauptklasse.figuren, 0));
		hauptklasse.zielAngeben(mapBild);
	}
}