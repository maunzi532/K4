package kampf;

import effektkarten.effekte.ziel.Basiswert;
import effektkarten.karten.*;

public final class NAktion extends NKarte
{
	public final Aktionskarte karte;

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