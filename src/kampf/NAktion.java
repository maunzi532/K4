package kampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.karten.*;

public class NAktion extends NKarte
{
	public Aktionskarte karte;

	public NAktion(Aktionskarte karte)
	{
		this.karte = karte;
	}

	@Override
	public int basisMagieAenderung()
	{
		return karte.magieMod();
	}

	@Override
	public int basisWert(Basiswert wert)
	{
		return switch(wert)
		{
			case ANGRIFF -> karte.angriffMod();
			case GESCHWINDIGKEIT -> karte.geschwindigkeitMod();
			case WAFFENWERT, VERTEIDIGUNG, LEBEN -> 0;
		};
	}

	public boolean ladeMitMagie()
	{
		return karte.ladeMitMagie();
	}
}