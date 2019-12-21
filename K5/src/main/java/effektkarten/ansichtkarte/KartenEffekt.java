package effektkarten.ansichtkarte;

public abstract class KartenEffekt implements Kartentext
{
	private final String typ;
	private final String text;
	private final int num;

	public KartenEffekt(String typ, String text, int num)
	{
		this.typ = typ;
		this.text = text;
		this.num = num;
	}

	@Override
	public String getTyp()
	{
		return typ;
	}

	@Override
	public String getText()
	{
		return text;
	}

	public int getNum()
	{
		return num;
	}
}