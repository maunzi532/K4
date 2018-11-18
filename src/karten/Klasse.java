package karten;

public enum Klasse
{
	K("Krieger"),
	B("Bogenschütze"),
	M("Magier"),
	V("Vogelmensch"),
	R("Roboter"),
	G("Geist"),
	W("Wasserwesen"),
	P("Pflanzenbeschwörer");

	Klasse(String name)
	{
		this.name = name;
	}

	public String name;
}