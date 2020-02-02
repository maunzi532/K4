package effektkarten.effekte.wirkung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record DatenWirkung(Typ typ, Wirkungswert wirktAuf, int multiplikator, int divisor, int max) implements Wirkung
{
	public enum Typ
	{
		GES_VORTEIL,
		AKTUELLE_MAGIE;
	}

	@Override
	public int triggere(EffektZielCharakter n, EffektZielCharakter ziel)
	{
		return switch(typ)
		{
			case GES_VORTEIL -> Math.max(0, n.getGesAktion() - ziel.getGesAktion());
			case AKTUELLE_MAGIE -> n.getMagie();
		};
	}

	@Override
	public int wert(Wirkungswert wert, int daten)
	{
		if(wert == wirktAuf)
		{
			int vorMax = daten * multiplikator / divisor;
			if(vorMax > 0 && max > 0)
				return Math.min(vorMax, max);
			if(vorMax < 0 && max < 0)
				return Math.max(vorMax, max);
			return vorMax;
		}
		return 0;
	}

	public String nameDaten()
	{
		return switch(typ)
		{
			case GES_VORTEIL -> "Ges._Vorteil";
			case AKTUELLE_MAGIE -> "Magie";
		};
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(wirktAuf.text).append(multiplikator >= 0 ? "+_" : "-_");
		if(Math.abs(multiplikator) > 1 || divisor > 1)
			sb.append("(");
		sb.append(nameDaten());
		if(Math.abs(multiplikator) > 1)
			sb.append(" *_").append(Math.abs(multiplikator));
		if(divisor > 1)
			sb.append(" /_").append(divisor);
		if(Math.abs(multiplikator) > 1 || divisor > 1)
			sb.append(")");
		if(max / multiplikator > 0)
			sb.append(" (max._").append(max).append(")");
		return sb.toString();
	}
}
