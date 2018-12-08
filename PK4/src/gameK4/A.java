package gameK4;

import plane.*;
import sprites.*;

public class A extends SpriteGame
{
	private SpriteList spriteList;
	private XSprite floor;
	private XChar character;

	@Override
	public void init(PlaneRenderer screen)
	{
		super.init(screen);
		spriteList = spriteLists.get(0);
		//spriteList.addSprite(new XSprite(0, 0, 0, 0, 0, new SubPixelPlane().init("N1_I", "N1")));
		floor = new XSprite(-100, -100, 0, 0, 0, new SubPixelPlane().init("BodenT0"));
		spriteList.addSprite(floor);
		character = new XChar();
		spriteList.addSprite(character.sprite);
		//spriteList.addSprite(new TSprite(0, 0, 0, 0, 2, new TextPlane(15, 0, "ARGH wugu ---", "ffcxgxhdx")));
	}

	@Override
	protected int spriteListCount()
	{
		return 1;
	}

	@Override
	public PlaneFrame defaultFrame(int i)
	{
		return new PlaneFrame(3, 0, screen.height, screen.width);
	}

	@Override
	public void tick()
	{
		/*boolean[][] col = D2Compare.colorCollisions(character.sprite, floor);
		//System.out.println(arghargh(col));
		argharghD(col);*/
		//boolean[][] col = D2Compare.colorCollisions(floor.plane, character.sprite.plane);
		//argharghD(col);
		character.update();
		spriteList.yScroll = screen.height - (character.sprite.yLEdge() + character.sprite.yHEdge()) / 2;
		spriteList.xScroll = screen.width - character.getX();
	}

	@Override
	public void handleInput(int input)
	{
		character.setInput(input);
	}

	private static void argharghD(boolean[][] argh)
	{
		StringBuilder sb = new StringBuilder();
		for(int i0 = 0; i0 < 17; i0++)
		{
			if(!argh[i0][17])
				continue;
			for(int i1 = 0; i1 < 17; i1++)
			{
				if(!argh[17][i1])
					continue;
				sb.append(argh[i0][i1] ? 'W' : '*');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}