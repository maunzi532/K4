package dungeonmap;

import dungeonmap.karte.*;
import java.util.*;
import java.util.function.*;

public class Spielfigur
{
	private static final int[] richtungenY = new int[]{-1, 0, 1, 0};
	private static final int[] richtungenX = new int[]{0, 1, 0, -1};

	private final KartenMap map;
	private FeldKoordinaten fa;
	private FeldKoordinaten lf;
	private boolean inBewegung;
	private List<KoordinatenNum> bewegungsgraph;
	private final Deque<KoordinatenNum> pfad = new ArrayDeque<>();

	public Spielfigur(KartenMap map, FeldKoordinaten f)
	{
		this.map = map;
		fa = f;
		lf = f;
		erstelleBewegungsgraph();
	}

	public boolean blockiert()
	{
		return !lf.equals(fa);
	}

	public void erstelleBewegungsgraph()
	{
		bewegungsgraph = new ArrayList<>();
		int i = 0;
		if(blockiert())
		{
			bewegungsgraph.add(new KoordinatenNum(fa, 0));
			i++;
		}
		bewegungsgraph.add(new KoordinatenNum(fa, i));
		for(; i < bewegungsgraph.size(); i++)
		{
			KoordinatenNum ak = bewegungsgraph.get(i);
			if(map.begehbar(ak.f) == Begehbar.GEHT)
			{
				for(int r = 0; r < 4; r++)
				{
					FeldKoordinaten f1 = FeldKoordinaten.f(ak.f.yf() + richtungenY[r], ak.f.xf() + richtungenX[r]);
					if(map.begehbar(f1) != Begehbar.NEIN)
					{
						KoordinatenNum neu = new KoordinatenNum(f1, ak.s + 1);
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

	public boolean geheZu(FeldKoordinaten f1, Supplier<Boolean> lrRNG)
	{
		int zielIndex = bewegungsgraph.indexOf(new KoordinatenNum(f1, 0));
		if(zielIndex < 0)
			return false;
		KoordinatenNum ak = bewegungsgraph.get(zielIndex);
		pfad.clear();
		while(ak.s > 0)
		{
			pfad.addLast(ak);
			KoordinatenNum ak0 = nk(ak, FeldKoordinaten.f(ak.f.yf() - 1, ak.f.xf()));
			if(ak0 != null)
				ak = ak0;
			else
			{
				KoordinatenNum ak1 = nk(ak, FeldKoordinaten.f(ak.f.yf() + 1, ak.f.xf()));
				if(ak1 != null)
					ak = ak1;
				else
				{
					int r = lrRNG.get() ? 1 : -1;
					KoordinatenNum ak2 = nk(ak, FeldKoordinaten.f(ak.f.yf(), ak.f.xf() + r));
					if(ak2 != null)
						ak = ak2;
					else
					{
						KoordinatenNum ak3 = nk(ak, FeldKoordinaten.f(ak.f.yf(), ak.f.xf() - r));
						if(ak3 != null)
							ak = ak3;
						else
							throw new RuntimeException();
					}
				}
			}
		}
		inBewegung = true;
		return true;
	}

	private KoordinatenNum nk(KoordinatenNum vor, FeldKoordinaten f1)
	{
		int zielIndex = bewegungsgraph.indexOf(new KoordinatenNum(f1, 0));
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
		lf = fa;
		fa = ak.f;
		if(map.begehbar(fa) == Begehbar.GEHT)
		{
			lf = fa;
		}
		if(pfad.isEmpty())
		{
			inBewegung = false;
			erstelleBewegungsgraph();
		}
	}

	public boolean bewegungFertig()
	{
		return !inBewegung;
	}

	public FeldKoordinaten kannForschen()
	{
		if(blockiert())
			return null;
		for(int i = 0; i < 4; i++)
		{
			FeldKoordinaten f1 = FeldKoordinaten.f(fa.yf() + richtungenY[i], fa.xf() + richtungenX[i]);
			if(map.isInMap(f1) && !map.existiertKarte(f1))
			{
				return f1;
			}
		}
		return null;
	}

	public int getYF()
	{
		return fa.yf();
	}

	public int getXF()
	{
		return fa.xf();
	}
}