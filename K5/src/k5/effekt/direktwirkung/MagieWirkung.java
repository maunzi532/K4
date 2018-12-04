package k5.effekt.direktwirkung;

import k5.kampf.*;
import k5.karten.*;

public class MagieWirkung implements DirektWirkung
{
	private final int magie;
	private final boolean gegner;

	public MagieWirkung(int magie, boolean gegner)
	{
		this.magie = magie;
		this.gegner = gegner;
	}

	@Override
	public void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
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