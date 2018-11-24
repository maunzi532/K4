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
		verwendet = new boolean[karte.getModLimit()];
	}

	public MapTeil ort(int y, int x)
	{
		return karte.ort(y, x, verkehrt);
	}

	public boolean verwendet(int y, int x)
	{
		int mod = karte.ortM(y, x, verkehrt);
		if(mod >= 0)
			return verwendet[mod];
		else
			return false;
	}

	public void setVerwendet(int y, int x)
	{
		int mod = karte.ortM(y, x, verkehrt);
		if(mod >= 0)
			verwendet[mod] = true;
	}

	public MapFeld setVerwendet(int num)
	{
		verwendet[num] = true;
		return this;
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