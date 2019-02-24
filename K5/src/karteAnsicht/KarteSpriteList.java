package karteAnsicht;

import java.util.*;
import kartenset.*;
import plane.*;
import sprites.*;

public class KarteSpriteList
{
	public SpriteList spriteList;
	public KarteBild3 kb;
	public List<KarteSprite> karteSprites;

	public KarteSpriteList(SpriteList v, List<Karte> karten, KarteBild3 kb)
	{
		this.kb = kb;
		spriteList = new SpriteList(v, 2);
		karteSprites = new ArrayList<>();
		for(int i = 0; i < karten.size(); i++)
		{
			KarteSprite karteSprite = new KarteSprite(zielY(i), zielX(i), 0, kb, karten.get(i));
			karteSprites.add(karteSprite);
			spriteList.addSprite(karteSprite);
		}
	}

	public KarteSpriteList(PlaneFrame frame, List<Karte> karten, KarteBild3 kb)
	{
		this.kb = kb;
		spriteList = new SpriteList(frame, 2);
		karteSprites = new ArrayList<>();
		for(int i = 0; i < karten.size(); i++)
		{
			KarteSprite karteSprite = new KarteSprite(zielY(i), zielX(i), 0, kb, karten.get(i));
			karteSprites.add(karteSprite);
			spriteList.addSprite(karteSprite);
		}
	}

	public int zielX(int num)
	{
		return num * 50;
	}

	public int zielY(int num)
	{
		return 0;
	}

	public void tick()
	{
		for(int i = 0; i < karteSprites.size(); i++)
		{
			KarteSprite karteSprite = karteSprites.get(i);
			if(Math.abs(karteSprite.y - zielY(i)) >= Math.abs(karteSprite.x - zielX(i)))
			{
				if(karteSprite.y > zielY(i))
					karteSprite.y -= 2;
				else if(karteSprite.y < zielY(i))
					karteSprite.y += 2;
			}
			else
			{
				if(karteSprite.x > zielX(i))
					karteSprite.x -= 2;
				else if(karteSprite.x < zielX(i))
					karteSprite.x += 2;
			}
		}
	}
}