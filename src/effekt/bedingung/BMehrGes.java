package effekt.bedingung;

import kampf.*;
import karten.*;

public class BMehrGes implements Bedingung
{
	final int min;

	public BMehrGes()
	{
		min = 1;
	}

	public BMehrGes(int min)
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