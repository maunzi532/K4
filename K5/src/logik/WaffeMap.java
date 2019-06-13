package logik;

import karten.*;

public class WaffeMap
{
	private static final int ALLE_KLASSEN = 0b11111111;

	public final Waffenkarte karte;
	public int benutzungen;

	public WaffeMap(Waffenkarte karte, boolean initial)
	{
		this.karte = karte;
		if(initial)
			benutzungen = 4;
		else if(karte.klassencode() == ALLE_KLASSEN)
			benutzungen = 3;
		else
			benutzungen = 2;
	}
}