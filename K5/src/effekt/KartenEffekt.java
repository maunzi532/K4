package effekt;

public class KartenEffekt
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

	public String getTyp()
	{
		return typ;
	}

	public String getText()
	{
		return text;
	}

	public int getNum()
	{
		return num;
	}
}