package sets;

import karten.*;

public enum Klassen implements Klasse
{
	K("Krieger", "Abenteuerschwert", 0),
	B("Bogenschütze", "Stärkungsbogen", 1),
	M("Magier", "Geladener Stab", 2),
	V("Vogelmensch", "Schutzflügel", 3),
	R("Roboter", "Politurfaust", 4),
	G("Geist", "Spuk", 5),
	W("Wasserwesen", "Wasserkugel", 6),
	P("Pflanzenbeschwörer", "Beschwörungsstab", 7);

	Klassen(String name, String startwaffe, int positionInKlassencode)
	{
		this.name = name;
		this.startwaffe = startwaffe;
		this.positionInKlassencode = positionInKlassencode;
	}

	public String name;
	public String startwaffe;
	public int positionInKlassencode;

	@Override
	public String klassenName()
	{
		return name;
	}

	@Override
	public String startwaffe()
	{
		return startwaffe;
	}

	@Override
	public int positionInKlassencode()
	{
		return positionInKlassencode;
	}
}