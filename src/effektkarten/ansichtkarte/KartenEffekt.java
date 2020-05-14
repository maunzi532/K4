package effektkarten.ansichtkarte;

public abstract class KartenEffekt implements Kartentext
{
	public final String typ;
	public final String text;
	public final int num;

	protected KartenEffekt(String typ, String text, int num)
	{
		this.typ = typ;
		this.text = text;
		this.num = num;
	}

	@Override
	public String typ()
	{
		return typ;
	}

	@Override
	public String text()
	{
		return text;
	}
}