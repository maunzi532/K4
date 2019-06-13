package sets;

import karten.*;

public enum Klassen implements Klasse
{
	K("Krieger"),
	B("Bogenschütze"),
	M("Magier"),
	V("Vogelmensch"),
	R("Roboter"),
	G("Geist"),
	W("Wasserwesen"),
	P("Pflanzenbeschwörer");

	Klassen(String name)
	{
		this.name = name;
	}

	public String name;

	@Override
	public String klassenName()
	{
		return name;
	}
}