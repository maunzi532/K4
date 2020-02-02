package map2;

import dungeonmap.map.*;

public class Spieler
{
	public Spielfigur spielfigur;
	public Held2 held;
	public SpielerStatus status;

	public Spieler()
	{
		status = SpielerStatus.MAP;
	}

	public Spielfigur spielfigur()
	{
		return spielfigur;
	}

	public Held2 held()
	{
		return held;
	}

	public SpielerStatus status()
	{
		return status;
	}
}