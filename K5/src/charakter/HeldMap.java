package charakter;

import kampf.*;
import karten.*;
import kartenset.*;

public class HeldMap
{
	public final int spielerNummer;
	public KlasseMitLevels held;
	public Charakterkarte charakter;
	public WaffeMap hauptwaffe;
	public WaffeMap nebenwaffe;
	public int leben;
	public int exp;

	public HeldMap(int spielerNummer, Klasse k, Kartenset<Charakterkarte> klassenkarten, Kartenstapel<Waffenkarte> waffen)
	{
		this.spielerNummer = spielerNummer;
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

	public NTeilnehmer erstelleNTeilnehmer()
	{
		return new NTeilnehmer(spielerNummer, charakter, hauptwaffe.karte, nebenwaffe.karte, leben);
	}

	public Waffenwechsel erstelleWaffenwechsel(TeamItems teamItems)
	{
		return new Waffenwechsel(spielerNummer, teamItems, hauptwaffe, nebenwaffe);
	}
}