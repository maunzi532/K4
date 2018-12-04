package k5.charakter;

import k5.karten.*;
import k5.kartenset.*;

public class WaffeMap
{
	public final Waffenkarte karte;
	public int benutzungen;

	public WaffeMap(Waffenkarte karte, boolean initial)
	{
		this.karte = karte;
		if(initial)
			benutzungen = 4;
		else if(karte.klassencode() == Karte.alleKlassen)
			benutzungen = 3;
		else
			benutzungen = 2;
	}
}