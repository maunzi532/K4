package effektkarten.kartebild;

public final class Klassen3
{
	public final int anzahlKlassen;
	public final int alleKlassenCode;
	public final String[] klassenNamen;

	public Klassen3()
	{
		anzahlKlassen = 8;
		alleKlassenCode = 0b11111111;
		klassenNamen = new String[]{"Krieger", "Bogenschütze", "Magier", "Vogelmensch",
				"Roboter", "Geist", "Wasserwesen", "Pflanzenbeschwörer"};
	}
}