package gui.text;

import dungeonmap.karte.*;
import java.util.*;
import map2.*;

public class FeldBild
{
	private final boolean inMap;
	private final boolean existiert;
	private final MapTeil mapTeil;
	private final boolean verwendet;
	private final List<Spieler> faS;
	private final List<Spieler> zielS;

	public FeldBild(boolean inMap)
	{
		this.inMap = inMap;
		existiert = false;
		mapTeil = MapTeil.NICHTS;
		verwendet = false;
		faS = List.of();
		zielS = List.of();
	}

	public FeldBild(MapTeil mapTeil, boolean verwendet, List<Spieler> faS, List<Spieler> zielS)
	{
		inMap = true;
		existiert = true;
		this.mapTeil = mapTeil;
		this.verwendet = verwendet;
		this.faS = faS;
		this.zielS = zielS;
	}

	public char klein()
	{
		return verwendet ? mapTeil.zeichen1 : mapTeil.zeichen0;
	}

	public char[][] bild(MapBild2Daten daten)
	{
		char[][] bild = new char[daten.yb()][daten.xb()];
		for(int iy = 0; iy < daten.yb(); iy++)
		{
			for(int ix = 0; ix < daten.xb(); ix++)
			{
				char c = verwendet ? mapTeil.zeichen1 : mapTeil.zeichen0;
				int n1 = iy * daten.xb() + ix;
				if(n1 < faS.size())
					c = (char) (faS.get(n1).nummer() + '1');
				int n2 = daten.yb() * daten.xb() - 1 - n1;
				if(n2 < zielS.size())
					c = 'z';
				bild[iy][ix] = c;
			}
		}
		return bild;
	}
}