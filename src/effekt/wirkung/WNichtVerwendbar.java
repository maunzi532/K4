package effekt.wirkung;

public class WNichtVerwendbar implements Wirkung
{
	@Override
	public String text()
	{
		return "Kann nicht verwendet werden";
	}
}