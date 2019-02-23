package sprites;

import java.util.*;
import plane.*;

public class SpriteList
{
	private List<SpriteList> spriteLists = new ArrayList<>();
	private List<Sprite> sprites = new ArrayList<>();
	public PlaneFrame planeFrame;
	public int yScroll;
	public int xScroll;
	public int z;
	public boolean visible = true;

	public SpriteList(PlaneFrame planeFrame, int z)
	{
		this.planeFrame = planeFrame;
		this.z = z;
	}

	public SpriteList(SpriteList auf, int z)
	{
		this.planeFrame = auf.planeFrame;
		this.z = z;
		auf.addSpriteList(this);
	}

	public SpriteList(SpriteList auf, int z, boolean visible)
	{
		this.planeFrame = auf.planeFrame;
		this.z = z;
		this.visible = visible;
		auf.addSpriteList(this);
	}

	public void centerMid(PlaneFrame center)
	{
		yScroll = (center.startY + center.endY) / 4 * 2;
		xScroll = (center.startX + center.endX) / 4 * 2;
	}

	public void addSpriteList(SpriteList spriteList)
	{
		int k = spriteLists.size();
		while(k > 0 && spriteLists.get(k - 1).z < spriteList.z)
		{
			k--;
		}
		spriteLists.add(k, spriteList);
	}

	public void addSprite(Sprite sprite)
	{
		int k = sprites.size();
		while(k > 0 && sprites.get(k - 1).z < sprite.z)
		{
			k--;
		}
		sprites.add(k, sprite);
	}

	public void removeSprite(Sprite sprite)
	{
		sprites.remove(sprite);
	}

	public void removeAll()
	{
		sprites.clear();
	}

	public void updatePositions()
	{
		updatePositions(0, 0);
	}

	private void updatePositions(int ys, int xs)
	{
		if(visible)
		{
			int ys2 = ys + yScroll;
			int xs2 = xs + xScroll;
			for(SpriteList spriteList : spriteLists)
			{
				spriteList.updatePositions(ys2, xs2);
			}
			for(Sprite sprite : sprites)
			{
				sprite.updatePosition(ys2, xs2);
			}
		}
	}

	public List<Plane> planes()
	{
		if(!visible)
			return Collections.EMPTY_LIST;
		List<Plane> re = new ArrayList<>();
		spriteLists.forEach(e -> re.addAll(e.planes()));
		for(Sprite sprite : sprites)
		{
			re.add(sprite.getPlane());
		}
		return re;
	}

	public List<PlaneFrame> planeFrames()
	{
		if(!visible)
			return Collections.EMPTY_LIST;
		ArrayList re = new ArrayList();
		spriteLists.forEach(e -> re.addAll(e.planeFrames()));
		for(int i = 0; i < sprites.size(); i++)
		{
			re.add(planeFrame);
		}
		return re;
	}
}