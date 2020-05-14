package map2;

import dungeonmap.map.*;

public final class Spieler
{
	public int nummer;
	public Spielfigur spielfigur;
	public Held2 held;
	public SpielerStatus status;

	public Spieler(int nummer)
	{
		this.nummer = nummer;
		status = SpielerStatus.MAP;
	}

	public int nummer()
	{
		return nummer;
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