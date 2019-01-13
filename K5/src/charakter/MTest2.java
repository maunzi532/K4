package charakter;

import plane.*;
import sets.*;
import sprites.*;

public class MTest2 extends SpriteGame
{
	private Hauptklasse hauptklasse;

	@Override
	public void init(PlaneRenderer screen)
	{
		super.init(screen);
		hauptklasse = new Hauptklasse(new Einstellungen(), new SetV2MittelMapKarten(), new SetV2MapKarten(),
				new SetV2Klassen(), new SetV2Gegner(), new SetV2Waffen(), new SetV2Aktionen());
		hauptklasse.initSpriteList(screen, new SpriteList(spriteList, 0));
		//m.klassenAuswahl();
		hauptklasse.klassenAuswahl(spriteList, "K", "B");
	}

	@Override
	protected int spriteListCount()
	{
		return 3;
	}

	@Override
	public void tick()
	{
		hauptklasse.tick(screen);
	}

	@Override
	public void handleInput(int input)
	{
		hauptklasse.handleInput(input);
	}
}