package effektkarten.effekte.wirkung;

import effektkarten.effekte.ziel.*;

public class WNichtVerwendbar implements Wirkung
{
	@Override
	public String text()
	{
		return "Kann nicht verwendet werden";
	}
}