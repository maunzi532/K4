package dungeonmap.map;

import dungeonmap.karte.*;
import java.util.*;
import java.util.function.*;
import main.*;
import stapelkarten.*;

public final class KartenMap
{
	public final int yhMap;
	private final int xmMap;
	public final int xwMap;
	private final int immerWegW;
	private final List<Integer> mittelBossOrte;
	private final KarteInMap[][] kartenFelder;

	public KartenMap(Einstellungen e)
	{
		yhMap = e.laengeHauptWeg;
		xmMap = e.maxSeitwaerts;
		xwMap = xmMap * 2 + 1;
		immerWegW = e.sicherSeitwaertsMin;
		mittelBossOrte = e.mittelBossOrte;
		kartenFelder = new KarteInMap[yhMap][xwMap];
	}

	public void setzeKarte(MapKartenPosition mkp, KarteInMap feld)
	{
		kartenFelder[mkp.yk()][mkp.xk()] = feld;
	}

	public KarteInMap karte(MapKartenPosition mkp)
	{
		return kartenFelder[mkp.yk()][mkp.xk()];
	}

	public boolean isInMap(MapKartenPosition mkp)
	{
		return mkp.yk() >= 0 && mkp.xk() >= 0 && mkp.yk() < yhMap && mkp.xk() < xwMap;
	}

	public boolean existiertKarte(MapKartenPosition mkp)
	{
		return isInMap(mkp) && karte(mkp) != null;
	}

	public MapTeil ort(FeldPosition fp)
	{
		return karte(fp).ort(fp);
	}

	public boolean isVerwendet(FeldPosition fp)
	{
		return karte(fp).isVerwendet(fp);
	}

	public void verwende(FeldPosition fp)
	{
		setzeKarte(fp, karte(fp).verwende(fp));
	}

	public Begehbar begehbar(FeldPosition fp)
	{
		if(existiertKarte(fp))
			return karte(fp).begehbar(fp);
		else
			return Begehbar.NEIN;
	}

	public void erstelleMittelWeg(MittelMapKartenset set, Consumer<? super List<MapKarte>> reihenfolgeAnpassen)
	{
		kartenFelder[yhMap - 1][xmMap] = new KarteInMap(set.start(), false).verwende(0).verwende(1);
		kartenFelder[0][xmMap] = new KarteInMap(set.ziel(), false);
		List<MapKarte> mittelWeg = set.hauptWeg(yhMap - 2);
		reihenfolgeAnpassen.accept(mittelWeg);
		for(int i = 0; i < yhMap - 2; i++)
		{
			KarteInMap feld = new KarteInMap(mittelWeg.get(i), false);
			if(!mittelBossOrte.contains(i + 1))
				kartenFelder[yhMap - 2 - i][xmMap] = feld.verwende(0);
			else
				kartenFelder[yhMap - 2 - i][xmMap] = feld;
		}
	}

	public boolean kartePasst(MapKarte karte, MapKartenPosition mkp, boolean verkehrt)
	{
		if(verkehrt && !karte.umdrehbar())
			return false;
		for(MapRichtung seite : MapRichtung.values())
		{
			if(karte.anschluss(seite, verkehrt) + anschlussAussen(mkp, seite) == 0)
				return false;
		}
		return true;
	}

	public int anschlussAussen(MapKartenPosition mkp, MapRichtung seite)
	{
		MapKartenPosition k1 = MapKartenPosition1.addieren(mkp, seite.y, seite.x);
		int re = 0;
		if(existiertKarte(k1))
		{
			re = karte(k1).anschluss(MapRichtung.values()[(seite.r + 2) % 4]);
		}
		if(re == 0 && mkp.xk() >= xmMap - immerWegW && mkp.xk() <= xmMap + immerWegW)
		{
			MapKartenPosition k2 = MapKartenPosition1.addieren(mkp, seite.y, -seite.x);
			if(existiertKarte(k2) && karte(k2).anschluss(seite) == 1)
			{
				re = 1;
			}
		}
		return re;
	}

	public FeldPosition startPosition()
	{
		return FeldPosition1.mkfp(yhMap, xmMap, -1, FeldPosition.xm);
	}

	public void forsche(MapKartenPosition mkp, Kartenstapel<MapKarte> mapStapel)
	{
		while(true)
		{
			Optional<MapKarte> karte0 = mapStapel.erhalteKarteNurDeck();
			if(karte0.isPresent())
			{
				MapKarte karte = karte0.get();
				boolean ok0 = kartePasst(karte, mkp, false);
				boolean ok1 = kartePasst(karte, mkp, true);
				if(!ok0 && !ok1)
				{
					mapStapel.ablage(karte);
				}
				else if(ok0)
				{
					setzeKarte(mkp, new KarteInMap(karte, false));
					break;
				}
				else
				{
					setzeKarte(mkp, new KarteInMap(karte, true));
					break;
				}
			}
			else
			{
				throw new RuntimeException("Map-Karten-Stapel leer");
			}
		}
	}
}