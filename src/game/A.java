package game;

import java.util.*;
import m.*;
import plane.*;
import sprites.*;

public class A implements Game
{
	private PlaneRenderer screen;
	private SpriteList spriteList;
	private XSprite character;

	@Override
	public void init(PlaneRenderer screen)
	{
		this.screen = screen;
		spriteList = new SpriteList(new PlaneFrame(3, 6, screen.height - 3, screen.width - 6));
		spriteList.addSprite(new XSprite(0, 0, 0, 0, 0, new SubPixelPlane().init("N1_I", "N1_IT")));
		SubPixelPlane cPlane = new SubPixelPlane().init("Char6_N", "Char6_NT");
		character = new XSprite(screen.height + cPlane.getSubYSize() / 2, screen.width, cPlane.getSubYSize(), cPlane.getSubXSize() / 2, 1, cPlane);
		spriteList.addSprite(character);
		spriteList.addSprite(new TSprite(0, 0, 0, 0, 2, new TextPlane(15, 0, "ARGH wugu ---", "ffcxgxhdx")));
	}

	@Override
	public void fillLists(List<Plane> planes, List<PlaneFrame> frames)
	{
		spriteList.updatePositions(0, 0);
		planes.addAll(spriteList.planes());
		frames.addAll(spriteList.planeFrames());
	}

	@Override
	public void handleInput(int input)
	{
		switch(input)
		{
			case 'w':
				//ys--;
				break;
			case 's':
				//ys++;
				break;
			case 'a':
				//xs--;
				character.x -= 9;
				character.plane.setFlippedX(true);
				break;
			case 'd':
				//xs++;
				character.x += 9;
				character.plane.setFlippedX(false);
				break;
		}
	}
}