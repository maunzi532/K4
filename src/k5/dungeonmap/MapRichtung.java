package k5.dungeonmap;

public enum MapRichtung
{
	VORNE(0, 0, 1),
	RECHTS(1, 1, 2),
	HINTEN(2, 2, 1),
	LINKS(3, 1, 0);

	MapRichtung(int r, int y, int x)
	{
		this.r = r;
		this.y = y;
		this.x = x;
	}

	public int r;
	public int y, x;
}