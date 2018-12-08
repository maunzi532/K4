package sprites;

import java.util.*;
import m.*;
import plane.*;

public abstract class SpriteGame implements Game
{
	protected PlaneRenderer screen;
	protected List<SpriteList> spriteLists;

	@Override
	public void init(PlaneRenderer screen)
	{
		this.screen = screen;
		spriteLists = new ArrayList<>();
		for(int i = 0; i < spriteListCount(); i++)
			spriteLists.add(new SpriteList(defaultFrame(i)));
	}

	protected abstract int spriteListCount();

	protected PlaneFrame defaultFrame(int i)
	{
		return new PlaneFrame(0, 0, screen.height, screen.width);
	}

	@Override
	public void fillLists(List<Plane> planes, List<PlaneFrame> frames)
	{
		tick();
		for(var spriteList : spriteLists)
		{
			if(spriteList.visible)
			{
				spriteList.updatePositions();
				planes.addAll(spriteList.planes());
				frames.addAll(spriteList.planeFrames());
			}
		}
	}

	public abstract void tick();
}