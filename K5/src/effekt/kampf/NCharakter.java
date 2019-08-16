package effekt.kampf;

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
			case WAFFENWERT -> karte.getWaffenwert();
			case GESCHWINDIGKEIT -> karte.getGeschwindigkeit();
			case VERTEIDIGUNG -> karte.getVerteidigung();
			case LEBEN -> karte.getLeben();
		};
	}
}