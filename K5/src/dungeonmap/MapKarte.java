package dungeonmap;

import basiskarte.*;
import java.util.*;

public class MapKarte implements AKarte
{
	public static final int ym = 2;
	public static final int xm = 2;
	private static final int ym2 = ym * 2;
	private static final int xm2 = xm * 2;
	public static final int xw = xm * 2 + 1;
	public static final int yw = ym * 2 + 1;
	private static Map<Character, MapTeil> charZuMapTeil = Map.ofEntries(Map.entry('X', MapTeil.NICHTS),
			Map.entry(' ', MapTeil.WEG), Map.entry('G', MapTeil.GEGNER), Map.entry('T', MapTeil.TRANK),
			Map.entry('H', MapTeil.HAENDLER), Map.entry('W', MapTeil.WAFFENKISTE), Map.entry('|', MapTeil.WAND),
			Map.entry('S', MapTeil.START), Map.entry('Z', MapTeil.ZIEL),
			Map.entry('M', MapTeil.MITTELBOSSGEGNER), Map.entry('B', MapTeil.BOSSGEGNER));

	private MapTeil[] inhalt;
	private int[] modifier;
	private int modLimit;
	private boolean umdrehbar;

	public MapKarte(String input)
	{
		String[] split = input.split("\n");
		if(split[0].isEmpty())
			umdrehbar = true;
		inhalt = new MapTeil[yw * xw];
		modifier = new int[inhalt.length];
		for(int iy = 0; iy < yw; iy++)
		{
			for(int ix = 0; ix < xw; ix++)
			{
				inhalt[iy * xw + ix] = charZuMapTeil.get(split[iy + 1].charAt(ix));
			}
		}
		if(split.length > yw + 1)
		{
			for(int iy = 0; iy < yw; iy++)
			{
				for(int ix = 0; ix < xw; ix++)
				{
					int mod = split[iy + 1 + yw].charAt(ix) - '0' - 1;
					modLimit = Math.max(modLimit, mod + 1);
					modifier[iy * xw + ix] = mod;
				}
			}
		}
		else
		{
			int nmod = 0;
			for(int i = 0; i < inhalt.length; i++)
			{
				if(inhalt[i].hatModifier)
				{
					modifier[i] = nmod;
					nmod++;
				}
				else
					modifier[i] = -1;
			}
			modLimit = nmod;
		}
	}

	public int getModLimit()
	{
		return modLimit;
	}

	public boolean isUmdrehbar()
	{
		return umdrehbar;
	}

	public MapTeil ort(int y, int x, boolean verkehrt)
	{
		if(verkehrt)
		{
			return inhalt[(ym2 - y) * xw + (xm2 - x)];
		}
		else
		{
			return inhalt[y * xw + x];
		}
	}

	public int ortM(int y, int x, boolean verkehrt)
	{
		if(verkehrt)
		{
			return modifier[(ym2 - y) * xw + (xm2 - x)];
		}
		else
		{
			return modifier[y * xw + x];
		}
	}

	public int anschluss(MapRichtung seite, boolean verkehrt)
	{
		return ort(ym + seite.y * ym, xm + seite.x * xm, verkehrt) == MapTeil.NICHTS ? -1 : 1;
	}
}