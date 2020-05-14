package effektkarten.effekte.wirkung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record WWaffeAngriff(int multiplikator) implements Wirkung
{
	@Override
	public int triggere(EffektZielCharakter n, EffektZielCharakter ziel)
	{
		return n.effektZielKarte(EffektZielKartentyp.WAFFE).basisWert(Basiswert.ANGRIFF);
	}

	@Override
	public int wert(Wirkungswert wert, int daten)
	{
		if(wert == Wirkungswert.ANGRIFF)
			return daten * multiplikator;
		else
			return 0;
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder("Angriff ist um A_(Waffe) ");
		if(multiplikator > 0)
			sb.append("erhöht");
		if(multiplikator < 0)
			sb.append("verringert");
		return sb.toString();
	}
}