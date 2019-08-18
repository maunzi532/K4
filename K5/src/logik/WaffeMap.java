package logik;

import karten.*;

public class WaffeMap
{
	private static final int ALLE_KLASSEN = 0b11111111;

	public final Waffenkarte karte;
	public final int maxBenutzungen;
	public int benutzungen;

	public WaffeMap(Waffenkarte karte, boolean initial)
	{
		this.karte = karte;
		if(initial)
			maxBenutzungen = 4;
		else if(karte.klassencode() == ALLE_KLASSEN)
			maxBenutzungen = 3;
		else
			maxBenutzungen = 2;
		benutzungen = maxBenutzungen;
	}
}