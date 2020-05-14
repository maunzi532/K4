package dungeonmap.karte;

import stapelkarten.*;

public final class MapKarte implements StapelKarte
{
	private static final int ym2 = FeldKoordinaten.ym * 2;
	private static final int xm2 = FeldKoordinaten.xm * 2;
	private static final int xw = FeldKoordinaten.xm * 2 + 1;
	private static final int yw = FeldKoordinaten.ym * 2 + 1;

	private final MapTeil[] inhalt;
	private final int[] modifier;
	private final int modLimit;
	private final boolean umdrehbar;

	public MapKarte(String input)
	{
		String[] split = input.split("\n");
		umdrehbar = split[0].isEmpty();
		inhalt = new MapTeil[yw * xw];
		modifier = new int[inhalt.length];
		for(int iy = 0; iy < yw; iy++)
		{
			for(int ix = 0; ix < xw; ix++)
			{
				inhalt[iy * xw + ix] = MapTeil.charZuMapTeil.get(split[iy + 1].charAt(ix));
			}
		}
		int nMod = 0;
		if(split.length > yw + 1)
		{
			for(int iy = 0; iy < yw; iy++)
			{
				for(int ix = 0; ix < xw; ix++)
				{
					int mod = split[iy + 1 + yw].charAt(ix) - '0' - 1;
					nMod = Math.max(nMod, mod + 1);
					modifier[iy * xw + ix] = mod;
				}
			}
		}
		else
		{
			for(int i = 0; i < inhalt.length; i++)
			{
				if(inhalt[i].hatModifier)
				{
					modifier[i] = nMod;
					nMod++;
				}
				else
					modifier[i] = -1;
			}
		}
		modLimit = nMod;
	}

	public int modLimit()
	{
		return modLimit;
	}

	public boolean umdrehbar()
	{
		return umdrehbar;
	}

	public MapTeil ort(FeldKoordinaten f, boolean verkehrt)
	{
		if(verkehrt)
		{
			return inhalt[(ym2 - f.yfInnen()) * xw + (xm2 - f.xfInnen())];
		}
		else
		{
			return inhalt[f.yfInnen() * xw + f.xfInnen()];
		}
	}

	public int ortM(FeldKoordinaten f, boolean verkehrt)
	{
		if(verkehrt)
		{
			return modifier[(ym2 - f.yfInnen()) * xw + (xm2 - f.xfInnen())];
		}
		else
		{
			return modifier[f.yfInnen() * xw + f.xfInnen()];
		}
	}

	public int anschluss(MapRichtung seite, boolean verkehrt)
	{
		return ort(FeldKoordinaten.f(FeldKoordinaten.ym + seite.y * FeldKoordinaten.ym,
				FeldKoordinaten.xm + seite.x * FeldKoordinaten.xm), verkehrt) == MapTeil.NICHTS ? -1 : 1;
	}
}