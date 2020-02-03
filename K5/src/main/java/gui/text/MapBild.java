package gui.text;

import dungeonmap.karte.*;
import dungeonmap.map.*;
import java.util.*;
import java.util.stream.*;
import map2.*;

public class MapBild
{
	public FeldKoordinaten fokus(KartenMap map, int ys, int xs, FeldKoordinaten ort)
	{
		FeldKoordinaten max = FeldKoordinaten.k(map.yhMap, map.xwMap, 0, 0);
		FeldKoordinaten lv = FeldKoordinaten.add(ort, -ys, -xs);
		FeldKoordinaten rh = FeldKoordinaten.add(ort, ys + 1, xs + 1);
		FeldKoordinaten lv2 = FeldKoordinaten.f(-lv.yf(), -lv.xf());
		FeldKoordinaten rh2 = FeldKoordinaten.add(rh, -max.yf(), -max.xf());
		/*int vs = lv.yf() < 0 ? -lv.yf() : 0;
		int hs = rh.yf() < 0 ? -rh.yf() : 0;
		int ls = lv.xf() < 0 ? -lv.xf() : 0;
		int rs = rh2.xf() < 0 ? -rh2.xf() : 0;*/
		return ort;
	}

	public FeldBild[][] felder(KartenMap map, int ys, int xs, FeldKoordinaten fokus, List<Spieler> spieler)
	{
		int ys2 = ys * 2 + 1;
		int xs2 = xs * 2 + 1;
		FeldBild[][] felder = new FeldBild[ys2][xs2];
		for(int iy = 0; iy < ys2; iy++)
		{
			for(int ix = 0; ix < xs2; ix++)
			{
				FeldKoordinaten fa = FeldKoordinaten.add(fokus, iy - ys, ix - xs);
				if(map.existiertKarte(fa))
				{
					felder[iy][ix] = new FeldBild(map.ort(fa), map.isVerwendet(fa),
							spieler.stream().filter(s -> s.spielfigur().getFA().equals(fa)).collect(Collectors.toList()),
							spieler.stream().filter(s -> fa.equals(s.spielfigur().ziel().orElse(null))).collect(Collectors.toList()));
				}
				else
				{
					felder[iy][ix] = new FeldBild(map.isInMap(fa));
				}
			}
		}
		return felder;
	}

	public FeldBild[][] felder(KartenMap map, List<Spieler> spieler)
	{
		FeldKoordinaten max = FeldKoordinaten.k(map.yhMap, map.xwMap, 0, 0);
		int ysF = max.yf();
		int xsF = max.xf();
		FeldBild[][] felder = new FeldBild[ysF][xsF];
		for(int iy = 0; iy < ysF; iy++)
		{
			for(int ix = 0; ix < xsF; ix++)
			{
				FeldKoordinaten fa = FeldKoordinaten.f(iy, ix);
				if(map.existiertKarte(fa))
				{
					felder[iy][ix] = new FeldBild(map.ort(fa), map.isVerwendet(fa),
							spieler.stream().filter(s -> s.spielfigur().getFA().equals(fa)).collect(Collectors.toList()),
							spieler.stream().filter(s -> fa.equals(s.spielfigur().ziel().orElse(null))).collect(Collectors.toList()));
				}
				else
				{
					felder[iy][ix] = new FeldBild(map.isInMap(fa));
				}
			}
		}
		return felder;
	}

	public String erstelleTextBild(MapBild2Daten daten, FeldBild[][] feldBild)
	{
		StringBuilder sb = new StringBuilder();
		for(int iy = 0; iy < feldBild.length; iy++)
		{
			for(int ky = 0; ky < daten.yb(); ky++)
			{
				for(int ix = 0; ix < feldBild[iy].length; ix++)
				{
					sb.append(feldBild[iy][ix].bild(daten)[ky]);
				}
				sb.append('\n');
			}
		}
		return sb.toString();
	}

	public String erstelleKleinBild(FeldBild[][] feldBild)
	{
		StringBuilder sb = new StringBuilder();
		for(int iy = 0; iy < feldBild.length; iy++)
		{
			for(int ix = 0; ix < feldBild[iy].length; ix++)
			{
				sb.append(feldBild[iy][ix].klein());
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}