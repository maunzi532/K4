package kampf;

import effekt.*;
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

	@Override
	public int basisWert(Basiswert wert)
	{
		return switch(wert)
		{
			case ANGRIFF -> karte.getAngriff();
			case WAFFENWERT -> karte.getWaffenwert();
			case GESCHWINDIGKEIT -> karte.getGeschwindigkeit();
			case VERTEIDIGUNG -> karte.getVerteidigung();
			case LEBEN -> karte.getLeben();
		};
	}
}