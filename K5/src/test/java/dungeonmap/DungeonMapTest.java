package dungeonmap;

import main.*;
import mapsets.*;
import org.junit.*;
import stapelkarten.*;

public class DungeonMapTest
{
	private Einstellungen e;
	private DungeonMap dungeonMap;
	private MittelMapKartenset mittelMapKartenset;

	@Before
	public void before()
	{
		e = new Einstellungen();
		dungeonMap = new DungeonMap(e);
		mittelMapKartenset = new MittelMapKartenset(new SetV2MittelMapKarten().fertig());
	}

	@Test
	public void test()
	{
		int mapBreite = e.maxSeitwaerts * 2 + 1;
		for(int i = 0; i < e.laengeHauptWeg * mapBreite; i++)
		{
			dungeonMap.setFeld(i / mapBreite, i % mapBreite,
					new MapFeld(new MapKarte("\nXX XX\nXX XX\n     \nXX XX\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		}
		dungeonMap.setFeld(e.laengeHauptWeg - 1, e.maxSeitwaerts,
				new MapFeld(new MapKarte("\nXX XX\nXX XX\n     \nXG HX\nXXSXX\n00000\n00000\n00000\n00000\n00000"), false));
		dungeonMap.setFeld(0, e.maxSeitwaerts,
				new MapFeld(new MapKarte("\nXXZXX\nX   X\nXBBBX\nX   X\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		System.out.println(dungeonMap.toString());
	}

	@Test
	public void mittelMapTest()
	{
		dungeonMap.erstelleMittelWeg(mittelMapKartenset);
		System.out.println(dungeonMap.toString());
	}

	@Test
	public void forschenTest()
	{
		Kartenstapel<MapKarte> mapStapel = new MischKartenstapel<>(new SetV2MapKarten().fertig());
		dungeonMap.erstelleMittelWeg(mittelMapKartenset);
		Spielfigur spielfigur = dungeonMap.erstelleSpielfigur();
		spielfigur.geheZu(spielfigur.getY() - MapKarte.ym, spielfigur.getX() - MapKarte.xm);
		while(!spielfigur.bewegungFertig())
		{
			spielfigur.bewege();
		}
		KoordinatenNum kn = spielfigur.kannForschen();
		dungeonMap.forsche(kn, mapStapel);
		System.out.println(dungeonMap);
	}
}