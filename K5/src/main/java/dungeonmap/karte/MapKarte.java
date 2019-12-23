package dungeonmap.karte;

import stapelkarten.*;

public class MapKarte implements StapelKarte
{
	public static final int ym = 2;
	public static final int xm = 2;
	private static final int ym2 = ym * 2;
	private static final int xm2 = xm * 2;
	public static final int xw = xm * 2 + 1;
	public static final int yw = ym * 2 + 1;

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

	public int getModLimit()
	{
		return modLimit;
	}

	public boolean isUmdrehbar()
	{
		return umdrehbar;
	}

	public MapTeil ort(int yf, int xf, boolean verkehrt)
	{
		if(verkehrt)
		{
			return inhalt[(ym2 - yf) * xw + (xm2 - xf)];
		}
		else
		{
			return inhalt[yf * xw + xf];
		}
	}

	public int ortM(int yf, int xf, boolean verkehrt)
	{
		if(verkehrt)
		{
			return modifier[(ym2 - yf) * xw + (xm2 - xf)];
		}
		else
		{
			return modifier[yf * xw + xf];
		}
	}

	public int anschluss(MapRichtung seite, boolean verkehrt)
	{
		return ort(ym + seite.y * ym, xm + seite.x * xm, verkehrt) == MapTeil.NICHTS ? -1 : 1;
	}
}