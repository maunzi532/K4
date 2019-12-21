package map2;

import effektkarten.karten.*;

public class AktiverGegner
{
	public final Gegner gegner;
	public final Charakterkarte charakterkarte;

	public AktiverGegner(Gegner gegner, Charakterkarte charakterkarte)
	{
		this.gegner = gegner;
		this.charakterkarte = charakterkarte;
	}
}