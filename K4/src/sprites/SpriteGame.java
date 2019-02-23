package sprites;

import java.util.*;
import m.*;
import plane.*;

public abstract class SpriteGame implements Game
{
	protected PlaneRenderer screen;
	protected SpriteList spriteList;

	@Override
	public void init(PlaneRenderer screen)
	{
		this.screen = screen;
		spriteList = new SpriteList(defaultFrame(), 0);
	}

	protected PlaneFrame defaultFrame()
	{
		return new PlaneFrame(0, 0, screen.height, screen.width);
	}

	@Override
	public void fillLists(List<Plane> planes, List<PlaneFrame> frames)
	{
		tick();
		spriteList.updatePositions();
		planes.addAll(spriteList.planes());
		frames.addAll(spriteList.planeFrames());
	}

	public abstract void tick();
}