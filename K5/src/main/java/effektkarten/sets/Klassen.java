package effektkarten.sets;

import effektkarten.karten.*;

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

	public final String name;
	public final String startwaffe;
	public final int positionInKlassencode;
	
	Klassen(String name, String startwaffe, int positionInKlassencode)
	{
		this.name = name;
		this.startwaffe = startwaffe;
		this.positionInKlassencode = positionInKlassencode;
	}

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