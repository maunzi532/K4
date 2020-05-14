package effektkarten.effekte.wirkung;

import effektkarten.effekte.ziel.*;

public record WNichtVerwendbar() implements Wirkung
{
	@Override
	public String text()
	{
		return "Kann nicht verwendet werden";
	}
}