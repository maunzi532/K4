package effektkarten.effekte.bedingung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public class BLebenVergleich implements Bedingung
{
	private final Vergleich vergleich;

	public BLebenVergleich(Vergleich vergleich)
	{
		this.vergleich = vergleich;
	}

	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return vergleich.evaluiere(n.getLeben(), ziel.getLeben());
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Leben ").append(vergleich.text).append(" Leben_(Ziel)");
		return sb.toString();
	}
}