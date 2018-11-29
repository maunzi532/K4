package charakter;

import karten.*;
import kartenset.*;

public class HeldMap
{
	public KlasseMitLevels held;
	public Charakterkarte charakter;
	public WaffeMap hauptwaffe;
	public WaffeMap nebenwaffe;
	public int leben;
	public int exp;

	public HeldMap(Klasse k, Kartenset<Charakterkarte> klassenkarten, Kartenstapel<Waffenkarte> waffen)
	{
		held = new KlasseMitLevels(k, klassenkarten);
		charakter = held.charakterkarte();
		hauptwaffe = new WaffeMap(waffen.entnehmeKarte(switch(k)
		{
			case K -> "Abenteuerschwert";
			case B -> "Stärkungsbogen";
			case M -> "Geladener Stab";
			case V -> "Schutzflügel";
			case R -> "Politurfaust";
			case G -> "Spuk";
			case W -> "Wasserkugel";
			case P -> "Beschwörungsstab";
		}), true);
		nebenwaffe = null;
		leben = charakter.getLeben() * 3;
		exp = 0;
	}
}