package k5.effekt;

public enum Basiswert
{
	ANGRIFF("A"),
	WAFFENWERT("W"),
	GESCHWINDIGKEIT("G"),
	VERTEIDIGUNG("V"),
	LEBEN("L");

	Basiswert(String kurz)
	{
		this.kurz = kurz;
	}

	public String kurz;
}