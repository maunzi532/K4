package dungeonmap.map;

import dungeonmap.karte.*;
import java.util.*;
import java.util.function.*;

public final class Spielfigur
{
	private static final int[] richtungenY = {-1, 0, 1, 0};
	private static final int[] richtungenX = {0, 1, 0, -1};

	private final KartenMap map;
	private FeldPosition fa;
	private FeldPosition lf;
	private boolean inBewegung;
	private FeldPosition ziel;
	private List<KoordinatenNum> bewegungsgraph;
	private final Deque<KoordinatenNum> pfad = new ArrayDeque<>();

	public Spielfigur(KartenMap map, FeldPosition fp)
	{
		this.map = map;
		fa = fp;
		lf = fp;
		erstelleBewegungsgraph();
	}

	public boolean blockiert()
	{
		return !lf.equals(fa);
	}

	private record KoordinatenNum(FeldPosition fp, int s)
	{
		@Override
		public boolean equals(Object obj)
		{
			if(this == obj) return true;
			if(!(obj instanceof KoordinatenNum obj1)) return false;
			return Objects.equals(fp, obj1.fp);
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(fp);
		}
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
		bewegungsgraph.add(new KoordinatenNum(lf, i));
		for(; i < bewegungsgraph.size(); i++)
		{
			KoordinatenNum ak = bewegungsgraph.get(i);
			if(map.begehbar(ak.fp()) == Begehbar.GEHT)
			{
				for(int r = 0; r < 4; r++)
				{
					FeldPosition f1 = FeldPosition1.addieren(ak.fp(), richtungenY[r], richtungenX[r]);
					if(map.begehbar(f1) != Begehbar.NEIN)
					{
						KoordinatenNum neu = new KoordinatenNum(f1, ak.s() + 1);
						int vorIndex = bewegungsgraph.indexOf(neu);
						if(vorIndex < 0)
						{
							bewegungsgraph.add(neu);
						}
						else if(neu.s() < bewegungsgraph.get(vorIndex).s())
						{
							bewegungsgraph.remove(vorIndex);
							bewegungsgraph.add(neu);
						}
					}
				}
			}
		}
	}

	public boolean geheZu(FeldPosition fp, Supplier<Boolean> lrRNG)
	{
		int zielIndex = bewegungsgraph.indexOf(new KoordinatenNum(fp, 0));
		if(zielIndex < 0)
			return false;
		KoordinatenNum ak = bewegungsgraph.get(zielIndex);
		pfad.clear();
		while(ak.s() > 0)
		{
			pfad.addLast(ak);
			KoordinatenNum ak0 = nk(ak, FeldPosition1.addieren(ak.fp(), -1, 0));
			if(ak0 != null)
				ak = ak0;
			else
			{
				KoordinatenNum ak1 = nk(ak, FeldPosition1.addieren(ak.fp(), 1, 0));
				if(ak1 != null)
					ak = ak1;
				else
				{
					int r = lrRNG.get() ? 1 : -1;
					KoordinatenNum ak2 = nk(ak, FeldPosition1.addieren(ak.fp(), 0, r));
					if(ak2 != null)
						ak = ak2;
					else
					{
						KoordinatenNum ak3 = nk(ak, FeldPosition1.addieren(ak.fp(), 0, -r));
						if(ak3 != null)
							ak = ak3;
						else
							throw new RuntimeException("Fehler in Code");
					}
				}
			}
		}
		ziel = fp;
		inBewegung = true;
		return true;
	}

	private KoordinatenNum nk(KoordinatenNum vor, FeldPosition fp)
	{
		int zielIndex = bewegungsgraph.indexOf(new KoordinatenNum(fp, 0));
		if(zielIndex < 0)
			return null;
		KoordinatenNum ak = bewegungsgraph.get(zielIndex);
		if(ak.s() < vor.s())
			return ak;
		return null;
	}

	public void bewege()
	{
		if(pfad.isEmpty())
			return;
		KoordinatenNum ak = pfad.removeLast();
		lf = fa;
		fa = ak.fp();
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

	public boolean inBewegung()
	{
		return inBewegung;
	}

	public Optional<FeldPosition> ziel()
	{
		return inBewegung ? Optional.of(ziel) : Optional.empty();
	}

	public FeldPosition getFA()
	{
		return fa;
	}

	public Optional<MapKartenPosition> kannForschen()
	{
		if(blockiert())
			return Optional.empty();
		for(int i = 0; i < 4; i++)
		{
			MapKartenPosition mkp = FeldPosition1.addieren(fa, richtungenY[i], richtungenX[i]);
			if(map.isInMap(mkp) && !map.existiertKarte(mkp))
			{
				return Optional.of(mkp);
			}
		}
		return Optional.empty();
	}
}