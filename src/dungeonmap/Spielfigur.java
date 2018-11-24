package dungeonmap;

import java.util.*;

public class Spielfigur
{
	private static final int[] richtungenY = new int[]{-1, 0, 1, 0};
	private static final int[] richtungenX = new int[]{0, 1, 0, -1};

	private DungeonMap map;
	private int y, x;
	private int ly, lx;
	private List<KoordinatenNum> bewegungsgraph;
	private Deque<KoordinatenNum> pfad = new ArrayDeque<>();

	public boolean blockiert()
	{
		return ly != y || lx != x;
	}

	public void erstelleBewegungsgraph()
	{
		bewegungsgraph = new ArrayList<>();
		int i = 0;
		if(blockiert())
		{
			bewegungsgraph.add(new KoordinatenNum(y, x, 0));
			i++;
		}
		bewegungsgraph.add(new KoordinatenNum(ly, lx, i));
		for(; i < bewegungsgraph.size(); i++)
		{
			KoordinatenNum ak = bewegungsgraph.get(i);
			if(map.weitergehen(ak.y, ak.x))
			{
				for(int r = 0; r < 4; r++)
				{
					if(map.begehbar(ak.y + richtungenY[r], ak.x + richtungenX[r]))
					{
						KoordinatenNum neu = new KoordinatenNum(ak.y + richtungenY[r], ak.x + richtungenX[r], ak.s + 1);
						int vorIndex = bewegungsgraph.indexOf(neu);
						if(vorIndex < 0)
						{
							bewegungsgraph.add(neu);
						}
						else if(neu.s < bewegungsgraph.get(vorIndex).s)
						{
							bewegungsgraph.remove(vorIndex);
							bewegungsgraph.add(neu);
						}
					}
				}
			}
		}
	}

	public boolean geheZu(int y, int x)
	{
		int zielIndex = bewegungsgraph.indexOf(new KoordinatenNum(y, x, 0));
		if(zielIndex < 0)
			return false;
		KoordinatenNum ak = bewegungsgraph.get(zielIndex);
		pfad.clear();
		while(ak.s > 0)
		{
			pfad.addLast(ak);
			KoordinatenNum ak0 = nk(ak, ak.y - 1, ak.x);
			if(ak0 != null)
				ak = ak0;
			else
			{
				KoordinatenNum ak1 = nk(ak, ak.y + 1, ak.x);
				if(ak1 != null)
					ak = ak1;
				else
				{
					int r = (int)((System.currentTimeMillis() % 2) * 2 - 1);
					KoordinatenNum ak2 = nk(ak, ak.y, ak.x + r);
					if(ak2 != null)
						ak = ak2;
					else
					{
						KoordinatenNum ak3 = nk(ak, ak.y, ak.x - r);
						if(ak3 != null)
							ak = ak3;
						else
							throw new RuntimeException();
					}
				}
			}
		}
		return true;
	}

	private KoordinatenNum nk(KoordinatenNum vor, int y, int x)
	{
		int zielIndex = bewegungsgraph.indexOf(new KoordinatenNum(y, x, 0));
		if(zielIndex < 0)
			return null;
		KoordinatenNum ak = bewegungsgraph.get(zielIndex);
		if(ak.s < vor.s)
			return ak;
		return null;
	}

	public void bewege()
	{
		if(pfad.isEmpty())
			return;
		KoordinatenNum ak = pfad.removeLast();
		ly = y;
		lx = x;
		y = ak.y;
		x = ak.x;
		if(map.ort(y, x).begehbar != 1)
		{
			ly = y;
			lx = x;
		}
		if(pfad.isEmpty())
			erstelleBewegungsgraph();
	}

	public KoordinatenNum kannForschen()
	{
		if(blockiert())
			return null;
		for(int i = 0; i < 4; i++)
		{
			int sy = y + richtungenY[i];
			int sx = x + richtungenX[i];
			if(map.inMap(sy, sx) && !map.feld(sy, sx))
			{
				return new KoordinatenNum(sy, sx, 0);
			}
		}
		return null;
	}
}