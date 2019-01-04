package charakter;

import dungeonmap.*;
import java.util.*;
import karten.*;
import kartenset.*;
import plane.*;
import sprites.*;

public class Hauptklasse
{
	public Einstellungen e;
	public Kartenstapel<MapKarte> mapStapel;
	public Kartenset<Charakterkarte> klassenSet;
	public Kartenstapel<Charakterkarte> gegnerStapel;
	public Kartenstapel<Waffenkarte> waffenStapel;
	public Kartenstapel<Aktionskarte> aktionenStapel;
	public DungeonMap dungeonMap;
	public List<HeldSpieler> spieler;
	public int spielerAktuell;
	private SpriteList spriteListMap;
	private MapBild mapBild;
	private TSprite mapSprite;

	public Hauptklasse(Einstellungen e, MittelMapKartenset mittelMapSet, AKartenset<MapKarte> mapSet,
			Kartenset<Charakterkarte> klassenSet, Kartenset<Charakterkarte> gegnerSet,
			Kartenset<Waffenkarte> waffenSet, Kartenset<Aktionskarte> aktionenSet)
	{
		this.e = e;
		this.klassenSet = klassenSet;
		mapStapel = new Kartenstapel<>(mapSet.karten);
		gegnerStapel = new Kartenstapel<>(gegnerSet);
		waffenStapel = new Kartenstapel<>(waffenSet);
		aktionenStapel = new Kartenstapel<>(gegnerSet);
		spieler = new ArrayList<>();
		dungeonMap = new DungeonMap(e.yhMap, e.xmMap, e.immerWegW, e.mittelBossOrte);
		dungeonMap.erstelleMittelWeg(mittelMapSet);
	}

	public void initSpriteList(PlaneRenderer screen, SpriteList spriteListMap)
	{
		this.spriteListMap = spriteListMap;
		mapBild = new MapBild(dungeonMap);
		spriteListMap.addSprite(new KarteSprite(screen.height, screen.width, 3,
				new KarteBild3(), klassenSet.gibKarte("Krieger")));
		mapSprite = new TSprite(0, 0, 0, 0, 0, new TextPlane(0x7, 0x0, mapBild.erstelleTextBild1()));
		spriteListMap.addSprite(mapSprite);
		spriteListMap.visible = true;
	}

	public HeldSpieler heldSpielerAktuell()
	{
		return spieler.get(spielerAktuell);
	}

	public void klassenAuswahl(List<SpriteList> spriteLists, String... kl0)
	{
		List<Klasse> klassen = new ArrayList<>(Arrays.asList(Klasse.values()));
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			int i1 = i;
			spieler.add(new HeldSpieler(new HeldMap(i, klassen.stream()
					.filter(e1 -> e1.name().equalsIgnoreCase(kl0[i1])).findFirst().orElseThrow(),
					klassenSet, waffenStapel), dungeonMap, this, spriteLists.get(i), spriteListMap));
		}
	}

	public void tick(PlaneRenderer screen)
	{
		for(var heldSpieler : spieler)
		{
			heldSpieler.tick(screen);
		}
		spriteListMap.yScroll = (screen.height - heldSpielerAktuell().cursor.cursorSprite.y - MapBild.yc) / 2 * 2;
		spriteListMap.xScroll = (screen.width - heldSpielerAktuell().cursor.cursorSprite.x - MapBild.xc) / 2 * 2;
	}

	public void handleInput(int input)
	{
		if(input == 'q')
		{
			spielerAktuell++;
			if(spielerAktuell >= spieler.size())
				spielerAktuell = 0;
		}
		else
			heldSpielerAktuell().handleInput(input);
	}

	public void mapUpdate()
	{
		spieler.forEach(e -> e.spielfigur.erstelleBewegungsgraph());
		mapSprite.getPlane().update(0x7, 0x0, mapBild.erstelleTextBild1());
	}
}