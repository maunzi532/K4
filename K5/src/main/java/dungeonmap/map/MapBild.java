package dungeonmap.map;

import dungeonmap.karte.*;

public class MapBild
{
	public static final int yc = 3;
	public static final int xc = 5;
	public static final int ywF = FeldKoordinaten.ym * 2 + 1;
	public static final int xwF = FeldKoordinaten.xm * 2 + 1;

	private final KartenMap map;

	public MapBild(KartenMap map)
	{
		this.map = map;
	}

	public char[][][][] erstelleBild()
	{
		int ys = ywF * map.yhMap;
		int xs = xwF * map.xwMap;
		char[][][][] arr = new char[ys][xs][yc][xc];
		for(int iy = 0; iy < ys; iy++)
		{
			for(int ix = 0; ix < xs; ix++)
			{
				FeldKoordinaten f = FeldKoordinaten.f(iy, ix);
				if(map.existiertKarte(f))
				{
					MapTeil teil = map.ort(f);
					boolean verwendet = map.isVerwendet(f);
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							arr[iy][ix][ky][kx] = verwendet ? teil.zeichen1 : teil.zeichen0;
						}
					}
				}
				else
				{
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							arr[iy][ix][ky][kx] = '\u2588';
						}
					}
				}
			}
		}
		return arr;
	}

	public String erstelleTextBild()
	{
		char[][][][] arr = erstelleBild();
		int ys = ywF * map.yhMap;
		int xs = xwF * map.xwMap;
		StringBuilder sb = new StringBuilder();
		for(int iy = 0; iy < ys; iy++)
		{
			for(int ky = 0; ky < yc; ky++)
			{
				for(int ix = 0; ix < xs; ix++)
				{
					sb.append(arr[iy][ix][ky]);
				}
				sb.append('\n');
			}
		}
		return sb.toString();
	}

	public String[] erstelleTextBild1()
	{
		char[][][][] arr = erstelleBild();
		int ys = ywF * map.yhMap;
		int xs = xwF * map.xwMap;
		String[] re = new String[ys * yc];
		for(int iy = 0; iy < ys; iy++)
		{
			for(int ky = 0; ky < yc; ky++)
			{
				StringBuilder sb = new StringBuilder();
				for(int ix = 0; ix < xs; ix++)
				{
					sb.append(arr[iy][ix][ky]);
				}
				re[iy * yc + ky] = sb.toString();
			}
		}
		return re;
	}

	public String mapAlsText()
	{
		StringBuilder sb = new StringBuilder();
		for(int iy0 = 0; iy0 < map.yhMap; iy0++)
		{
			for(int iy1 = 0; iy1 < ywF; iy1++)
			{
				for(int ix0 = 0; ix0 < map.xwMap; ix0++)
				{
					for(int ix1 = 0; ix1 < xwF; ix1++)
					{
						FeldKoordinaten f = FeldKoordinaten.k(iy0, ix0, iy1, ix1);
						if(map.existiertKarte(f))
						{
							sb.append(map.isVerwendet(f) ? map.ort(f).zeichen1 : map.ort(f).zeichen0);
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

	public char[][][][] erstelleBild(KartenKoordinaten k1, KartenKoordinaten k2)
	{
		int ysK = k1.yk();
		int xsK = k1.xk();
		int ywK = k2.yk() - k1.yk() + 1;
		int xwK = k2.xk() - k1.xk() + 1;
		int ysF = ywF * ywK;
		int xsF = xwF * xwK;
		char[][][][] arr = new char[ysF][xsF][yc][xc];
		for(int iy = 0; iy < ysF; iy++)
		{
			for(int ix = 0; ix < xsF; ix++)
			{
				FeldKoordinaten f = FeldKoordinaten.k(ysK, xsK, iy, ix);
				if(map.existiertKarte(f))
				{
					MapTeil teil = map.ort(f);
					boolean verwendet = map.isVerwendet(f);
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							arr[iy][ix][ky][kx] = verwendet ? teil.zeichen1 : teil.zeichen0;
						}
					}
				}
				else if(map.isInMap(f))
				{
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							arr[iy][ix][ky][kx] = '\u2588';
						}
					}
				}
				else
				{
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							arr[iy][ix][ky][kx] = ' ';
						}
					}
				}
			}
		}
		return arr;
	}

	public String erstelleTextBild(KartenKoordinaten k1, KartenKoordinaten k2)
	{
		char[][][][] arr = erstelleBild(k1, k2);
		StringBuilder sb = new StringBuilder();
		int ys = arr.length;
		for(int iy = 0; iy < ys; iy++)
		{
			int xs = arr[iy].length;
			for(int ky = 0; ky < yc; ky++)
			{
				for(int ix = 0; ix < xs; ix++)
				{
					sb.append(arr[iy][ix][ky]);
				}
				sb.append('\n');
			}
		}
		return sb.toString();
	}
}