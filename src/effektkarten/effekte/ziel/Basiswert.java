package effektkarten.effekte.ziel;

public enum Basiswert
{
	ANGRIFF("A"),
	WAFFENWERT("W"),
	GESCHWINDIGKEIT("G"),
	VERTEIDIGUNG("V"),
	LEBEN("L");

	public final String kurz;

	Basiswert(String kurz)
	{
		this.kurz = kurz;
	}
}