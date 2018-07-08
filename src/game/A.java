package game;

import java.util.*;
import m.*;
import plane.*;
import sprites.*;

public class A implements Game
{
	private PlaneRenderer screen;
	private SpriteList spriteList;
	private XChar character;
	private int yscroll;
	private int xscroll;

	@Override
	public void init(PlaneRenderer screen)
	{
		this.screen = screen;
		spriteList = new SpriteList(new PlaneFrame(3, 0, screen.height, screen.width));
		spriteList.addSprite(new XSprite(0, 0, 0, 0, 0, new SubPixelPlane().init("N1_I", "N1")));
		character = new XChar();
		spriteList.addSprite(character.sprite);
		//spriteList.addSprite(new TSprite(0, 0, 0, 0, 2, new TextPlane(15, 0, "ARGH wugu ---", "ffcxgxhdx")));
	}

	@Override
	public void fillLists(List<Plane> planes, List<PlaneFrame> frames)
	{
		spriteList.updatePositions(yscroll, xscroll);
		planes.addAll(spriteList.planes());
		frames.addAll(spriteList.planeFrames());
	}

	@Override
	public void handleInput(int input)
	{
		character.handleInput(input);
		yscroll = screen.height - (character.sprite.yLEdge() + character.sprite.yHEdge()) / 2;
		xscroll = screen.width - character.getX();
	}
}