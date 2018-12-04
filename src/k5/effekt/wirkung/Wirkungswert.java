package k5.effekt.wirkung;

public enum Wirkungswert
{
	ANGRIFF("A_"),
	GESCHWINDIGKEIT("G_"),
	VERTEIDIGUNG("V_"),
	MINDESTSCHADEN("Mindestschaden "),
	MINDESTSCHUTZ("Mindestschutz "),
	EXTRAANGRIFFE("Extraangriff"),
	MAGIE("Magie für Angriffe ");

	Wirkungswert(String text)
	{
		this.text = text;
	}

	public String text;
}