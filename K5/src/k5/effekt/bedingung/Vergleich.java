package k5.effekt.bedingung;

public enum Vergleich
{
	K("<"),
	KG("≤"),
	G(">"),
	GG("≥");

	Vergleich(String text)
	{
		this.text = text;
	}

	public String text;
}