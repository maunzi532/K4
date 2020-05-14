package dungeonmap.karte;

public enum MapRichtung
{
	VORNE(0, -1, 0),
	RECHTS(1, 0, 1),
	HINTEN(2, 1, 0),
	LINKS(3, 0, -1);

	public final int r;
	public final int y, x;

	MapRichtung(int r, int y, int x)
	{
		this.r = r;
		this.y = y;
		this.x = x;
	}
}