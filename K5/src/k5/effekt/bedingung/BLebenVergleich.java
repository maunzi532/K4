package k5.effekt.bedingung;

import k5.kampf.*;
import k5.karten.*;

public class BLebenVergleich implements Bedingung
{
	private final Vergleich vergleich;

	public BLebenVergleich(Vergleich vergleich)
	{
		this.vergleich = vergleich;
	}

	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		int diff = n.getLeben() - ziel.getLeben();
		return switch(vergleich)
		{
			case K -> diff < 0;
			case KG -> diff <= 0;
			case G -> diff > 0;
			case GG -> diff >= 0;
		};
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Leben ").append(vergleich.text).append(" Leben_(Ziel)");
		return sb.toString();
	}
}