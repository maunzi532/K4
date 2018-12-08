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

	public Begehbar begehbar(int y, int x)
	{
		if(verwendet(y, x))
			return ort(y, x).begehbar1;
		else
			return ort(y, x).begehbar0;
	}

	public int anschluss(MapRichtung seite)
	{
		return karte.anschluss(seite, verkehrt);
	}
}