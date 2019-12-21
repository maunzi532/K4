package effektkarten.effekte.bedingung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public class BLebenIst implements Bedingung
{
	private final Vergleich vergleich;
	private final int zu;

	public BLebenIst(Vergleich vergleich, int zu)
	{
		this.vergleich = vergleich;
		this.zu = zu;
	}

	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return vergleich.evaluiere(n.getLeben(), zu);
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Leben ").append(vergleich.text).append(" ").append(zu);
		return sb.toString();
	}
}