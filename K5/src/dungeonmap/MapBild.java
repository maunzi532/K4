package dungeonmap;

public class MapBild
{
	public static final int yc = 3;
	public static final int xc = 5;

	private DungeonMap map;

	public MapBild(DungeonMap map)
	{
		this.map = map;
	}

	public char[][][][] erstelleBild()
	{
		int ys = DungeonMap.ywF * map.getYhMap();
		int xs = DungeonMap.xwF * map.getXwMap();
		char[][][][] arr = new char[ys][xs][yc][xc];
		for(int iy = 0; iy < ys; iy++)
		{
			for(int ix = 0; ix < xs; ix++)
			{
				if(map.feld(iy, ix))
				{
					MapTeil teil = map.ort(iy, ix);
					boolean verwendet = map.verwendet(iy, ix);
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

	public String[] erstelleTextBild1()
	{
		char[][][][] arr = erstelleBild();
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