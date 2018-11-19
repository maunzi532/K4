package effekt.bedingung;

import kampf.*;
import karten.*;

public class BMehrAG implements Bedingung
{
	private final int min;

	public BMehrAG()
	{
		min = 1;
	}

	public BMehrAG(int min)
	{
		this.min = min;
	}

	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return n.getGesAngriff() - ziel.getGesAngriff() >= min;
	}

	@Override
	public String text()
	{
		if(min == 1)
			return "Ges > Ges (Ziel)";
		else
			return "Ges >= Ges (Ziel) + " + min;
	}
}