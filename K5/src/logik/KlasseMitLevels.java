package logik;

import karten.*;
import kartenset.*;

public class KlasseMitLevels extends UpgradeCharakter
{
	private Klasse klasse;

	public KlasseMitLevels(Klasse klasse, Kartenset<Charakterkarte> klassenkarten)
	{
		this.klasse = klasse;
		charakter = klassenkarten.gibKarte(switch(klasse)
		{
			case K -> "Krieger";
			case B -> "Bogenschütze";
			case M -> "Magier";
			case V -> "Vogelmensch";
			case R -> "Roboter";
			case G -> "Geist";
			case W -> "Wasserwesen";
			case P -> "Pflanzenbeschw.";
		});
		werteUpgrades = new int[5];
	}

	public Klasse getKlasse()
	{
		return klasse;
	}
}