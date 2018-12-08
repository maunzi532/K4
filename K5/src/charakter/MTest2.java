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
	private TSprite mapSprite;
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
		spriteLists.get(1).addSprite(new KarteSprite(screen.height, screen.width, 3,
				new KarteBild2(), hauptklasse.klassenSet.gibKarte("Krieger")));
		mapSprite = new TSprite(0, 0, 0, 0, 0, new TextPlane(0x7, 0x0, mapBild.erstelleTextBild1()));
		spriteLists.get(0).addSprite(mapSprite);
		cursorSprite = new TSprite(0, 0, 2, new TextPlane(0x6, 0x5, "11111", "11111", "11111"));
		spriteLists.get(0).addSprite(cursorSprite);
		spielfiguren = new ArrayList<>();
		List<Spielfigur> figuren = hauptklasse.figuren;
		for(int i = 0; i < figuren.size(); i++)
		{
			Sprite sprite = new TSprite(0, 0, 1, new TextPlane(0x6, 0x0, "11111", "11111", "11111"));
			spielfiguren.add(sprite);
			spriteLists.get(0).addSprite(sprite);
		}
		spriteLists.get(0).visible = true;
		cursorY = hauptklasse.spielfigurAktuell().getY();
		cursorX = hauptklasse.spielfigurAktuell().getX();
	}

	@Override
	protected int spriteListCount()
	{
		return 2;
	}

	public Sprite spriteAktuell()
	{
		return spielfiguren.get(hauptklasse.spielerAktuell);
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
		spriteLists.get(0).yScroll = (screen.height - cursorSprite.y - MapBild.yc) / 2 * 2;
		spriteLists.get(0).xScroll = (screen.width - cursorSprite.x - MapBild.xc) / 2 * 2;
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
			hauptklasse.spielfigurAktuell().geheZu(cursorY, cursorX);
		else if(input == 'f')
		{
			if(hauptklasse.spielfigurAktuell().bereit())
			{
				KoordinatenNum k = hauptklasse.spielfigurAktuell().kannForschen();
				if(k != null)
				{
					hauptklasse.dungeonMap.forsche(k, hauptklasse.mapStapel);
					hauptklasse.figuren.forEach(Spielfigur::erstelleBewegungsgraph);
					mapSprite.getPlane().update(0x7, 0x0, mapBild.erstelleTextBild1());
				}
			}
		}
	}
}