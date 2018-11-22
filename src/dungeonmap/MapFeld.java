package dungeonmap;

public class MapFeld
{
	private MapKarte karte;
	private boolean verkehrt;
	private boolean[] verwendet;

	public MapFeld(MapKarte karte, boolean verkehrt)
	{
		this.karte = karte;
		this.verkehrt = verkehrt;
		verwendet = new boolean[4];
	}

	public MapTeil ort(int y, int x)
	{
		return karte.ort(y, x, verkehrt);
	}

	public boolean verwendet(int y, int x)
	{
		return verwendet[karte.ortM(y, x, verkehrt)];
	}

	public boolean begehbar(int y, int x)
	{
		int b = ort(y, x).begehbar;
		return switch(b)
		{
			case 0 -> false;
			case 1 -> true;
			case 2 -> verwendet(y, x);
			default -> false;
		};
	}

	public boolean weitergehen(int y, int x)
	{
		return ort(y, x).begehbar == 1;
	}
}