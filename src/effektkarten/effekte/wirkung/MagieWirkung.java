package effektkarten.effekte.wirkung;

import effektkarten.effekte.ziel.*;

public record MagieWirkung(int magie, boolean gegner) implements DirektWirkung
{
	@Override
	public void triggere(EffektZielCharakter n, EffektZielCharakter ziel)
	{
		if(gegner)
		{
			ziel.setMagie(Math.max(ziel.getMagie() + magie, 0));
		}
		else
		{
			n.setMagie(Math.max(n.getMagie() + magie, 0));
		}
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		if(gegner)
		{
			sb.append("Ziel Magie_");
		}
		else
		{
			sb.append("Magie_");
		}
		if(magie > 0)
		{
			sb.append("+").append(magie);
		}
		else
		{
			sb.append(magie).append(" (min._0)");
		}
		return sb.toString();
	}
}