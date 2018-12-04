package k5.dungeonmap;

import java.util.*;

public class MapBild
{
	private static final int yc = 3;
	private static final int xc = 5;

	private DungeonMap map;

	public MapBild(DungeonMap map)
	{
		this.map = map;
	}

	public char[][][][] erstelleBild(List<Spielfigur> spielfiguren, int ak)
	{
		int ys = DungeonMap.ywF * map.getYhMap();
		int xs = DungeonMap.xwF * map.getXwMap();
		char[][][][] arr = new char[ys][xs][yc][xc];
		for(var sf : spielfiguren)
		{
			arr[sf.getY()][sf.getX()][0][0] = 1;
		}
		arr[spielfiguren.get(ak).getY()][spielfiguren.get(ak).getX()][0][0] = 2;
		for(int iy = 0; iy < ys; iy++)
		{
			for(int ix = 0; ix < xs; ix++)
			{
				if(map.feld(iy, ix))
				{
					MapTeil teil = map.ort(iy, ix);
					boolean verwendet = map.verwendet(iy, ix);
					int sfh = arr[iy][ix][0][0];
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							if(sfh > 0)
							{
								arr[iy][ix][ky][kx] = (char)(sfh + '0');
							}
							else
							{
								arr[iy][ix][ky][kx] = verwendet ? teil.zeichen1 : teil.zeichen0;
							}
						}
					}
				}
				else
				{
					for(int ky = 0; ky < yc; ky++)
					{
						for(int kx = 0; kx < xc; kx++)
						{
							arr[iy][ix][ky][kx] = '▉';
						}
					}
				}
			}
		}
		return arr;
	}

	public String erstelleTextBild(List<Spielfigur> spielfiguren, int ak)
	{
		char[][][][] arr = erstelleBild(spielfiguren, ak);
		int ys = DungeonMap.ywF * map.getYhMap();
		int xs = DungeonMap.xwF * map.getXwMap();
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

	public String[] erstelleTextBild1(List<Spielfigur> spielfiguren, int ak)
	{
		char[][][][] arr = erstelleBild(spielfiguren, ak);
		int ys = DungeonMap.ywF * map.getYhMap();
		int xs = DungeonMap.xwF * map.getXwMap();
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
}