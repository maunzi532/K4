package sets;

import karten.*;
import kartenset.*;

public class SetV2Klassen extends Kartenset<Charakterkarte>
{
	public SetV2Klassen()
	{
		super();
		neueKarte(new Charakterkarte("Krieger", 10, 7, 7, 9, 6));
		neueKarte(new Charakterkarte("Bogenschütze", 8, 10, 8, 6, 7));
		neueKarte(new Charakterkarte("Magier", 7, 8, 8, 6, 10));
		neueKarte(new Charakterkarte("Vogelmensch", 6, 7, 10, 6, 8));
		neueKarte(new Charakterkarte("Roboter", 8, 7, 8, 10, 5));
		neueKarte(new Charakterkarte("Geist", 5, 8, 9, 7, 8));
		neueKarte(new Charakterkarte("Wasserwesen", 8, 5, 9, 7, 8));
		neueKarte(new Charakterkarte("Pflanzenbeschw." /*"Pflanzenbeschwörer"*/, 8, 8, 5, 9, 7));
		//alleKarten().forEach(e -> System.out.println(e.getName() + " " + (e.getAngriff() + e.getWaffenwert() + e.getGeschwindigkeit() + e.getVerteidigung() + e.getLeben())));
	}
}