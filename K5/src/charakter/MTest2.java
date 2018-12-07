package charakter;

import dungeonmap.*;
import kartenset.*;
import plane.*;
import sets.*;
import sprites.*;

public class MTest2 extends SpriteGame
{
	private Hauptklasse hauptklasse;
	private MapBild mapBild;

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
				new TextPlane(0xf, 0x0, mapBild.erstelleTextBild1(hauptklasse.figuren, 0))));
		spriteList.addSprite(new KarteSprite(screen.height + 4, screen.width + 4, 1,
				new KarteBild2(), hauptklasse.klassenSet.gibKarte("Krieger")));
	}

	@Override
	public void handleInput(int input)
	{
		if(input == 'w')
			yscroll += 2;
		else if(input == 's')
			yscroll -= 2;
		else if(input == 'a')
			xscroll += 2;
		else if(input == 'd')
			xscroll -= 2;
	}
}