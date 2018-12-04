package k5.kampf;

import k5.effekt.*;
import k5.karten.*;

public class NAktion extends NKarte
{
	public Aktionskarte karte;

	public NAktion(Aktionskarte karte)
	{
		this.karte = karte;
	}

	@Override
	public int basisAngriff()
	{
		return karte.getAngriffMod();
	}

	@Override
	public int basisGes()
	{
		return karte.getGeschwindigkeitMod();
	}

	@Override
	public int basisVert()
	{
		return 0;
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
			case WAFFENWERT -> 0;
			case GESCHWINDIGKEIT -> karte.getGeschwindigkeitMod();
			case VERTEIDIGUNG -> 0;
			case LEBEN -> 0;
		};
	}

	public boolean ladeMitMagie()
	{
		return karte.isLadeMitMagie();
	}
}