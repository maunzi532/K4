package effektkarten.effekte.wirkung;

public record WNichtVerwendbar() implements Wirkung
{
	@Override
	public String text()
	{
		return "Kann nicht verwendet werden";
	}
}