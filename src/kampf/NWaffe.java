package kampf;

import karten.*;

public class NWaffe extends NKarte
{
	public Waffenkarte karte;

	public NWaffe(Waffenkarte karte)
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
}