package effekt.wirkung;

public enum Wirkungswert
{
	ANGRIFF("A"),
	GESCHWINDIGKEIT("G"),
	VERTEIDIGUNG("V"),
	MINDESTSCHADEN("Mindestschaden"),
	MINDESTSCHUTZ("Mindestschutz"),
	EXTRAANGRIFFE("Extraangriff"),
	MAGIE("Magie für Angriffe");

	Wirkungswert(String text)
	{
		this.text = text;
	}

	public String text;
}