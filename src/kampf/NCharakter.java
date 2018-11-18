package kampf;

import karten.*;

public class NCharakter extends NKarte
{
	public Charakterkarte karte;

	public NCharakter(Charakterkarte karte)
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
		return karte.getVerteidigung();
	}

	@Override
	public int magieAenderung()
	{
		return 0;
	}

	@Override
	public int mindestschaden()
	{
		return super.mindestschaden() + 1;
	}
}