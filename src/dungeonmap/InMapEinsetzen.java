package dungeonmap;

public class InMapEinsetzen
{
	public final DungeonMap map;
	public final MapKarte karte;
	public final int yk, xk;

	public InMapEinsetzen(DungeonMap map, MapKarte karte, int yk, int xk)
	{
		this.map = map;
		this.karte = karte;
		this.yk = yk;
		this.xk = xk;
	}
}