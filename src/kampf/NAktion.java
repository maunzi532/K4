package kampf;

import karten.*;

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

	public boolean ladeMitMagie()
	{
		return false;
	}
}