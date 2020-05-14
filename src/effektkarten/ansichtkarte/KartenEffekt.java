package effektkarten.ansichtkarte;

public abstract class KartenEffekt implements Kartentext
{
	private final String typ;
	private final String text;
	private final int num;

	protected KartenEffekt(String typ, String text, int num)
	{
		this.typ = typ;
		this.text = text;
		this.num = num;
	}

	@Override
	public final String typ()
	{
		return typ;
	}

	@Override
	public final String text()
	{
		return text;
	}

	public final int num()
	{
		return num;
	}
}