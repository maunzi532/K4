package effektkarten.sets;

import effektkarten.karten.*;

public class SetV2Klassen extends KartensetBuilder<Charakterkarte>
{
	public SetV2Klassen()
	{
		neueKarte(new Charakterkarte("Krieger", 10, 7, 6, 9, 6, 0));
		neueKarte(new Charakterkarte("Bogenschütze", 7, 10, 8, 6, 7, 0));
		neueKarte(new Charakterkarte("Magier", 7, 8, 7, 6, 10, 0));
		neueKarte(new Charakterkarte("Vogelmensch", 8, 6, 10, 7, 7, 0));
		neueKarte(new Charakterkarte("Roboter", 8, 7, 8, 10, 5, 0));
		neueKarte(new Charakterkarte("Geist", 5, 8, 9, 7, 8, 0));
		neueKarte(new Charakterkarte("Wasserwesen", 8, 5, 8, 7, 9, 0));
		neueKarte(new Charakterkarte("Pflanzenbeschwörer", 8, 8, 5, 7, 9, 0));
	}
}