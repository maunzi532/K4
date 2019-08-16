package map2;

import karten.*;
import kartenset.*;
import logik.*;
import main.*;

public class Held2
{
	public Klasse klasse;
	public Charakterkarte charakterkarte;
	public UpgradeHeld upgradeHeld;
	public WaffeMap hauptwaffe;
	public WaffeMap nebenwaffe;
	public int[] upgrades;
	public int exp;
	public int leben;
	public HeldStatus trankStatus;
	public int gegnerExp;

	public Held2(Klasse klasse, WaffeMap hauptwaffe, WaffeMap nebenwaffe, int[] upgrades, int exp,
			Kartenset<Charakterkarte> charakterkarten, Einstellungen einstellungen)
	{
		this.klasse = klasse;
		charakterkarte = charakterkarten.gibKarte(klasse.klassenName());
		upgradeHeld = new UpgradeHeld(charakterkarte, upgrades);
		this.hauptwaffe = hauptwaffe;
		this.nebenwaffe = nebenwaffe;
		this.upgrades = upgrades;
		this.exp = exp;
		leben = (charakterkarte.getLeben() + upgrades[4]) * einstellungen.lebenMultiplikator;
		trankStatus = HeldStatus.MAXIMAL;
		gegnerExp = 0;
	}

	public static Held2 lesen(String input, Kartenset<Charakterkarte> charakterkarten, Einstellungen einstellungen)
	{
		return null;
	}

	public static Held2 initial(Klasse klasse, Kartenstapel<Waffenkarte> waffenkartenstapel,
			Kartenset<Charakterkarte> charakterkarten, Einstellungen einstellungen)
	{
		return new Held2(klasse, new WaffeMap(waffenkartenstapel.entnehmeKarte(klasse.startwaffe()), true), new WaffeMap(),
				new int[5], 0, charakterkarten, einstellungen);
	}
}