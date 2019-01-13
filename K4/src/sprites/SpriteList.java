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
		if(visible)
		{
			spriteLists.forEach(SpriteList::updatePositions);
			for(Sprite sprite : sprites)
				sprite.updatePosition(yScroll, xScroll);
		}
	}

	public List<Plane> planes()
	{
		if(!visible)
			return Collections.EMPTY_LIST;
		List<Plane> re = new ArrayList<>();
		spriteLists.forEach(e -> re.addAll(e.planes()));
		sprites.stream().map(Sprite::getPlane).forEach(e -> re.add(e));
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