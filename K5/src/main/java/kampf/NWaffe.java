package kampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.karten.*;

public class NWaffe extends NKarte
{
	public Waffenkarte karte;

	public static NWaffe von(Waffenkarte karte)
	{
		if(karte == null)
			return null;
		return new NWaffe(karte);
	}

	private NWaffe(Waffenkarte karte)
	{
		this.karte = karte;
	}

	@Override
	public int basisWert(Basiswert wert)
	{
		return switch(wert)
		{
			case ANGRIFF -> karte.angriff();
			case GESCHWINDIGKEIT -> karte.geschwindigkeit();
			case WAFFENWERT, VERTEIDIGUNG, LEBEN -> 0;
		};
	}
}