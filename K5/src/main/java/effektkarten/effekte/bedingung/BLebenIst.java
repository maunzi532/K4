package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BLebenIst implements Bedingung
{
	public final Vergleich vergleich;
	public final int zu;

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