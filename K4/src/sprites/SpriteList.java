package sprites;

import java.util.*;
import java.util.stream.*;
import plane.*;

public class SpriteList
{
	private List<Sprite> sprites = new ArrayList<>();
	public PlaneFrame planeFrame;
	public int yScroll;
	public int xScroll;

	public SpriteList(PlaneFrame planeFrame)
	{
		this.planeFrame = planeFrame;
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
		for(Sprite sprite : sprites)
			sprite.updatePosition(yScroll, xScroll);
	}

	public List<Plane> planes()
	{
		return sprites.stream().map(Sprite::getPlane).collect(Collectors.toList());
	}

	public List<PlaneFrame> planeFrames()
	{
		ArrayList al = new ArrayList();
		for(int i = 0; i < sprites.size(); i++)
		{
			al.add(planeFrame);
		}
		return al;
	}
}