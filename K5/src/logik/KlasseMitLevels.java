package logik;

import karten.*;
import kartenset.*;

public class KlasseMitLevels extends UpgradeCharakter
{
	private Klasse klasse;

	public KlasseMitLevels(Klasse klasse, Kartenset<Charakterkarte> klassenkarten)
	{
		this.klasse = klasse;
		charakter = klassenkarten.gibKarte(klasse.klassenName());
		werteUpgrades = new int[5];
	}

	public Klasse getKlasse()
	{
		return klasse;
	}
}