package dungeonmap;

import dungeonmap.karte.*;
import dungeonmap.map.*;
import dungeonmap.mapsets.*;
import gui.text.*;
import java.util.*;
import main.*;
import org.junit.*;
import stapelkarten.*;

public class KartenMapTest
{
	private Einstellungen e;
	private KartenMap kartenMap;
	private MittelMapKartenset mittelMapKartenset;
	private MapBild mapBild;

	@Before
	public void before()
	{
		e = Einstellungen.lies("Einstellungen", "1 Spieler");
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

			kartenMap.setzeKarte(KartenKoordinaten.k(i / mapBreite, i % mapBreite),
					new KarteInMap(new MapKarte("\nXX XX\nXX XX\n     \nXX XX\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		}
		kartenMap.setzeKarte(KartenKoordinaten.k(e.laengeHauptWeg - 1, e.maxSeitwaerts),
				new KarteInMap(new MapKarte("\nXX XX\nXX XX\n     \nXG HX\nXXSXX\n00000\n00000\n00000\n00000\n00000"), false));
		kartenMap.setzeKarte(KartenKoordinaten.k(0, e.maxSeitwaerts),
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
		spielfigur.geheZu(FeldKoordinaten.add(spielfigur.getFA(), -FeldKoordinaten.ym, -FeldKoordinaten.xm), () -> System.currentTimeMillis() % 2 == 0);
		while(spielfigur.inBewegung())
		{
			spielfigur.bewege();
		}
		Optional<FeldKoordinaten> f = spielfigur.kannForschen();
		kartenMap.forsche(f.orElseThrow(), mapStapel);
		System.out.println(mapBild.erstelleKleinBild(mapBild.felder(kartenMap, List.of())));
	}
}