package effekt.bedingung;

import effekt.*;

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
	public boolean ok(NTI n, NTI ziel, W mit)
	{
		int ist = n.getLeben();
		return switch(vergleich)
		{
			case K -> ist < zu;
			case KG -> ist <= zu;
			case G -> ist > zu;
			case GG -> ist >= zu;
		};
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Leben ").append(vergleich.text).append(" ").append(zu);
		return sb.toString();
	}
}