package effekt;

public class Effekt
{
	private final String typ;
	private final String text;
	private final int num;

	public Effekt(String text, int num)
	{
		typ = null;
		this.text = text;
		this.num = num;
	}

	public Effekt(String typ, String text, int num)
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