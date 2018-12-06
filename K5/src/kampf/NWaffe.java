package kampf;

import effekt.*;
import karten.*;

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
	public int basisAngriff()
	{
		return karte.getAngriff();
	}

	@Override
	public int basisGes()
	{
		return karte.getGeschwindigkeit();
	}

	@Override
	public int basisVert()
	{
		return 0;
	}

	@Override
	public int magieAenderung()
	{
		return 0;
	}

	@Override
	public int basisWert(Basiswert wert)
	{
		return switch(wert)
		{
			case ANGRIFF -> karte.getAngriff();
			case WAFFENWERT -> 0;
			case GESCHWINDIGKEIT -> karte.getGeschwindigkeit();
			case VERTEIDIGUNG -> 0;
			case LEBEN -> 0;
		};
	}
}