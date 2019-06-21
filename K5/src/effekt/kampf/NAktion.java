package effekt.kampf;

import effekt.*;
import karten.*;

public class NAktion extends NKarte
{
	public Aktionskarte karte;

	public NAktion(Aktionskarte karte)
	{
		this.karte = karte;
	}

	@Override
	public int magieAenderung()
	{
		return karte.getMagieMod();
	}

	@Override
	public int basisWert(Basiswert wert)
	{
		return switch(wert)
		{
			case ANGRIFF -> karte.getAngriffMod();
			case GESCHWINDIGKEIT -> karte.getGeschwindigkeitMod();
			case WAFFENWERT, VERTEIDIGUNG, LEBEN -> 0;
		};
	}

	public boolean ladeMitMagie()
	{
		return karte.isLadeMitMagie();
	}
}