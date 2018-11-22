package dungeonmap;

public class DungeonMap
{
	public static final int ywF = MapKarte.ym * 2 + 1;
	public static final int xwF = MapKarte.xm * 2 + 1;

	private int yhMap;
	private int xmMap;
	private int xwMap;
	private MapFeld[][] felder;

	public DungeonMap(int yhMap, int xmMap)
	{
		this.yhMap = yhMap;
		this.xmMap = xmMap;
		xwMap = xmMap * 2 + 1;
		felder = new MapFeld[yhMap][xwMap];
	}

	public void setFeld(int yk, int xk, MapFeld feld)
	{
		felder[yk][xk] = feld;
	}

	public boolean inMap(int y, int x)
	{
		return y >= 0 && x >= 0 && y < yhMap * ywF && x < xwMap * xwF;
	}

	public boolean feld(int y, int x)
	{
		return felder[y / ywF][x / xwF] != null;
	}

	public MapTeil ort(int y, int x)
	{
		return felder[y / ywF][x / xwF].ort(y % ywF, x % xwF);
	}

	public boolean verwendet(int y, int x)
	{
		return felder[y / ywF][x / xwF].verwendet(y % ywF, x % xwF);
	}

	public boolean begehbar(int y, int x)
	{
		return inMap(y, x) && feld(y, x) && felder[y / ywF][x / xwF].begehbar(y % ywF, x % xwF);
	}

	public boolean weitergehen(int y, int x)
	{
		return felder[y / ywF][x / xwF].weitergehen(y % ywF, x % xwF);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int iy0 = 0; iy0 < yhMap; iy0++)
		{
			for(int iy1 = 0; iy1 < ywF; iy1++)
			{
				for(int ix0 = 0; ix0 < xwMap; ix0++)
				{
					for(int ix1 = 0; ix1 < xwF; ix1++)
					{
						int y = iy0 * ywF + iy1;
						int x = ix0 * xwF + ix1;
						if(feld(y, x))
						{
							sb.append(verwendet(y, x) ? ort(y, x).zeichen1 : ort(y, x).zeichen0);
						}
						else
						{
							sb.append(' ');
						}
					}
				}
				sb.append('\n');
			}
		}
		return sb.toString();
	}
}