package dungeonmap;

import dungeonmap.karte.*;
import dungeonmap.map.*;
import dungeonmap.mapsets.*;
import gui.text.*;
import java.util.*;
import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stapelkarten.*;

public final class KartenMapTest
{
	private Einstellungen e;
	private KartenMap kartenMap;
	private MittelMapKartenset mittelMapKartenset;
	private MapBild mapBild;

	@BeforeEach
	public void before()
	{
		e = Einstellungen.lies("Einstellungen", "1_Spieler");
		kartenMap = new KartenMap(e);
		mittelMapKartenset = new MittelMapKartenset(new SetV2MittelMapKarten().fertig());
		mapBild = new MapBild();
	}

	@Test
	public void test()
	{
		int mapBreite = e.maxSeitwaerts * 2 + 1;
		for(int i = 0; i < e.laengeHauptWeg * mapBreite; i++)
		{

			kartenMap.setzeKarte(MapKartenPosition1.mkp(i / mapBreite, i % mapBreite),
					new KarteInMap(new MapKarte("\nXX XX\nXX XX\n     \nXX XX\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		}
		kartenMap.setzeKarte(MapKartenPosition1.mkp(e.laengeHauptWeg - 1, e.maxSeitwaerts),
				new KarteInMap(new MapKarte("\nXX XX\nXX XX\n     \nXG HX\nXXSXX\n00000\n00000\n00000\n00000\n00000"), false));
		kartenMap.setzeKarte(MapKartenPosition1.mkp(0, e.maxSeitwaerts),
				new KarteInMap(new MapKarte("\nXXZXX\nX   X\nXBBBX\nX   X\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		System.out.println(mapBild.erstelleKleinBild(mapBild.felder(kartenMap, List.of())));
	}

	@Test
	public void mittelMapTest()
	{
		kartenMap.erstelleMittelWeg(mittelMapKartenset, Collections::shuffle);
		System.out.println(mapBild.erstelleKleinBild(mapBild.felder(kartenMap, List.of())));
	}

	@Test
	public void forschenTest()
	{
		Kartenstapel<MapKarte> mapStapel = new MischKartenstapel<>(new SetV2MapKarten().fertig());
		kartenMap.erstelleMittelWeg(mittelMapKartenset, Collections::shuffle);
		Spielfigur spielfigur = new Spielfigur(kartenMap, kartenMap.startPosition());
		spielfigur.geheZu(FeldPosition1.addieren(spielfigur.getFA(), -FeldPosition.ym, -FeldPosition.xm), () -> System.currentTimeMillis() % 2 == 0);
		while(spielfigur.inBewegung())
		{
			spielfigur.bewege();
		}
		Optional<MapKartenPosition> mkp = spielfigur.kannForschen();
		kartenMap.forsche(mkp.orElseThrow(), mapStapel);
		System.out.println(mapBild.erstelleKleinBild(mapBild.felder(kartenMap, List.of())));
	}
}