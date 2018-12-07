package sprites;

import java.util.*;
import m.*;
import plane.*;

public abstract class SpriteGame implements Game
{
	protected PlaneRenderer screen;
	protected SpriteList spriteList;
	protected int yscroll;
	protected int xscroll;

	@Override
	public void init(PlaneRenderer screen)
	{
		this.screen = screen;
		spriteList = new SpriteList(defaultFrame());
	}

	public PlaneFrame defaultFrame()
	{
		return new PlaneFrame(0, 0, screen.height, screen.width);
	}

	@Override
	public void fillLists(List<Plane> planes, List<PlaneFrame> frames)
	{
		spriteList.updatePositions(yscroll, xscroll);
		planes.addAll(spriteList.planes());
		frames.addAll(spriteList.planeFrames());
	}
}