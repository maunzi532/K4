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

	public void setzeKarte(KartenKoordinaten k, KarteInMap feld)
	{
		kartenFelder[k.yk()][k.xk()] = feld;
	}

	public KarteInMap karte(KartenKoordinaten k)
	{
		return kartenFelder[k.yk()][k.xk()];
	}

	public boolean isInMap(KartenKoordinaten k)
	{
		return k.yk() >= 0 && k.xk() >= 0 && k.yk() < yhMap && k.xk() < xwMap;
	}

	public boolean existiertKarte(KartenKoordinaten k)
	{
		return isInMap(k) && karte(k) != null;
	}

	public MapTeil ort(FeldKoordinaten f)
	{
		return karte(f).ort(f);
	}

	public boolean isVerwendet(FeldKoordinaten f)
	{
		return karte(f).isVerwendet(f);
	}

	public void verwende(FeldKoordinaten f)
	{
		setzeKarte(f, karte(f).verwende(f));
	}

	public Begehbar begehbar(FeldKoordinaten f)
	{
		if(existiertKarte(f))
			return karte(f).begehbar(f);
		else
			return Begehbar.NEIN;
	}

	public void erstelleMittelWeg(MittelMapKartenset set, Consumer<List<MapKarte>> reihenfolgeAnpassen)
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

	public boolean kartePasst(MapKarte karte, KartenKoordinaten k, boolean verkehrt)
	{
		if(verkehrt && !karte.umdrehbar())
			return false;
		for(MapRichtung seite : MapRichtung.values())
		{
			if(karte.anschluss(seite, verkehrt) + anschlussAussen(k, seite) == 0)
				return false;
		}
		return true;
	}

	public int anschlussAussen(KartenKoordinaten k, MapRichtung seite)
	{
		KartenKoordinaten k1 = KartenKoordinaten.add(k, seite.y, seite.x);
		int re = 0;
		if(existiertKarte(k1))
		{
			re = karte(k1).anschluss(MapRichtung.values()[(seite.r + 2) % 4]);
		}
		if(re == 0 && k.xk() >= xmMap - immerWegW && k.xk() <= xmMap + immerWegW)
		{
			KartenKoordinaten k2 = KartenKoordinaten.add(k, seite.y, -seite.x);
			if(existiertKarte(k2) && karte(k2).anschluss(seite) == 1)
			{
				re = 1;
			}
		}
		return re;
	}

	public FeldKoordinaten startPosition()
	{
		return FeldKoordinaten.k(yhMap, xmMap, -1, FeldKoordinaten.xm);
	}

	public void forsche(FeldKoordinaten f, Kartenstapel<MapKarte> mapStapel)
	{
		while(true)
		{
			Optional<MapKarte> karte0 = mapStapel.erhalteKarteNurDeck();
			if(karte0.isPresent())
			{
				MapKarte karte = karte0.get();
				boolean ok0 = kartePasst(karte, f, false);
				boolean ok1 = kartePasst(karte, f, true);
				if(!ok0 && !ok1)
				{
					mapStapel.ablage(karte);
				}
				else if(ok0)
				{
					setzeKarte(f, new KarteInMap(karte, false));
					break;
				}
				else
				{
					setzeKarte(f, new KarteInMap(karte, true));
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