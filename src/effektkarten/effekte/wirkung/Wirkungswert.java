package effektkarten.effekte.wirkung;

public enum Wirkungswert
{
	ANGRIFF("A_"),
	GESCHWINDIGKEIT("G_"),
	VERTEIDIGUNG("V_"),
	MINDESTSCHADEN("Mindestschaden "),
	MINDESTSCHUTZ("Mindestschutz "),
	EXTRAANGRIFFE("Extraangriff"),
	MAGIE("Magie für Angriffe ");

	public final String text;

	Wirkungswert(String text)
	{
		this.text = text;
	}
}