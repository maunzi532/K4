package charakter;

import dungeonmap.*;
import java.util.*;
import kartenset.*;
import plane.*;
import sets.*;
import sprites.*;

public class MTest2 extends SpriteGame
{
	private Hauptklasse hauptklasse;
	private MapBild mapBild;
	private int cursorY;
	private int cursorX;
	private Sprite cursorSprite;
	private List<Sprite> spielfiguren;

	@Override
	public void init(PlaneRenderer screen)
	{
		super.init(screen);
		hauptklasse = new Hauptklasse(new Einstellungen(), new SetV2MittelMapKarten(), new SetV2MapKarten(),
				new SetV2Klassen(), new SetV2Gegner(), new SetV2Waffen(), new SetV2Aktionen());

		//m.klassenAuswahl();
		hauptklasse.klassenAuswahl("K", "B");
		mapBild = new MapBild(hauptklasse.dungeonMap);
		//System.out.println(mapBild.erstelleTextBild(hauptklasse.figuren, 0));
		//hauptklasse.zielAngeben(mapBild);
		spriteList.addSprite(new TSprite(0, 0, 0, 0, 0,
				new TextPlane(0x7, 0x0, mapBild.erstelleTextBild1())));
		spriteList.addSprite(new KarteSprite(screen.height + 4, screen.width + 4, 3,
				new KarteBild2(), hauptklasse.klassenSet.gibKarte("Krieger")));
		cursorSprite = new TSprite(0, 0, 2, new TextPlane(0x6, 0x5, "11111", "11111", "11111"));
		spriteList.addSprite(cursorSprite);
		spielfiguren = new ArrayList<>();
		List<Spielfigur> figuren = hauptklasse.figuren;
		for(int i = 0; i < figuren.size(); i++)
		{
			Sprite sprite = new TSprite(0, 0, 1, new TextPlane(0x6, 0x0, "11111", "11111", "11111"));
			spielfiguren.add(sprite);
			spriteList.addSprite(sprite);
		}
	}

	@Override
	public void tick()
	{
		hauptklasse.gehe();
		for(int i = 0; i < spielfiguren.size(); i++)
		{
			spielfiguren.get(i).y = hauptklasse.figuren.get(i).getY() * MapBild.yc * 2;
			spielfiguren.get(i).x = hauptklasse.figuren.get(i).getX() * MapBild.xc * 2;
		}
		cursorSprite.y = cursorY * MapBild.yc * 2;
		cursorSprite.x = cursorX * MapBild.xc * 2;
		yscroll = screen.height - cursorSprite.y;
		xscroll = screen.width - cursorSprite.x;
	}

	@Override
	public void handleInput(int input)
	{
		if(input == 'w')
			cursorY -= 1;
		else if(input == 's')
			cursorY += 1;
		else if(input == 'a')
			cursorX -= 1;
		else if(input == 'd')
			cursorX += 1;
		else if(input == ' ')
			hauptklasse.figuren.get(hauptklasse.spielerAktuell).geheZu(cursorY, cursorX);
	}
}