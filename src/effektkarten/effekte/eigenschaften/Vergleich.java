package effektkarten.effekte.eigenschaften;

public enum Vergleich
{
	K("<"),
	KG("≤"),
	G(">"),
	GG("≥");

	public final String text;

	Vergleich(String text)
	{
		this.text = text;
	}

	public boolean evaluiere(int wert, int vergleichswert)
	{
		return switch(this)
				{
					case K -> wert < vergleichswert;
					case KG -> wert <= vergleichswert;
					case G -> wert > vergleichswert;
					case GG -> wert >= vergleichswert;
				};
	}
}