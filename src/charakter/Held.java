package charakter;

import java.util.*;
import karten.*;
import kartenset.*;

public class Held extends UpgradeCharakter
{
	private Klasse klasse;
	public List<Waffenkarte> waffen;

	public Held(Klasse klasse, Kartenset<Charakterkarte> klassenkarten, Kartenset<Waffenkarte> waffenkarten)
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
		waffen = new ArrayList<>();
		/*waffen.add(waffenkarten.gibKarte(switch(klasse)
		{
			case K -> "Abenteuerschwert";
			case B -> "Bogenschütze";
			case M -> "Geladener Stab";
			case V -> "Schtzflügel";
			case R -> "Politurfaust";
			case G -> "Spuk";
			case W -> "Wasserwesen";
			case P -> "Pflanzenbeschwörer";
		}));*/
	}

	public Klasse getKlasse()
	{
		return klasse;
	}
}