package kampf;

import effektkarten.effekte.ziel.Basiswert;
import effektkarten.karten.*;

public final class NCharakter extends NKarte
{
	public final Charakterkarte karte;

	public NCharakter(Charakterkarte karte)
	{
		this.karte = karte;
	}

	@Override
	public int basisWert(Basiswert wert)
	{
		return switch(wert)
		{
			case ANGRIFF -> karte.angriff();
			case WAFFENWERT -> karte.waffenwert();
			case GESCHWINDIGKEIT -> karte.geschwindigkeit();
			case VERTEIDIGUNG -> karte.verteidigung();
			case LEBEN -> karte.leben();
		};
	}
}