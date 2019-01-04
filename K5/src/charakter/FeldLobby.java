package charakter;

import dungeonmap.*;
import kartenset.*;
import plane.*;
import sprites.*;

public class FeldLobby
{
	private static final int rwy = 10;
	private static final int rwx = 30;

	private DungeonMap map;
	private int yk, xk;
	private int yt, xt;
	private int modifier;
	private MapTeil typ;
	private boolean verwendet;
	private TSprite sprite;
	private Rahmen3 rahmen;

	public FeldLobby(DungeonMap map, int y, int x, Rahmen3 rahmen, SpriteList spriteList)
	{
		this.map = map;
		yk = y / MapKarte.yw;
		xk = x / MapKarte.xw;
		yt = y % MapKarte.yw;
		xt = x % MapKarte.xw;
		modifier = map.getFeld(yk, xk).ortM(yt, xt);
		typ = map.ort(y, x);
		verwendet = map.verwendet(y, x);
		sprite = new TSprite(new TextPlane(0x7, 0x0, "Wugu", "Wugu"), 3);
		spriteList.addSprite(sprite);
		this.rahmen = rahmen;
		updateSprite();
	}

	public void entfernen(SpriteList spriteList)
	{
		spriteList.removeSprite(sprite);
	}

	public boolean istEnthalten(int y, int x)
	{
		int yk1 = y / MapKarte.yw;
		int xk1 = x / MapKarte.xw;
		int yt1 = y % MapKarte.yw;
		int xt1 = x % MapKarte.xw;
		int modifier1 = map.getFeld(yk1, xk1).ortM(yt1, xt1);
		if(yk1 == yk && xk1 == xk)
		{
			if(modifier1 < 0)
			{
				return yt1 == yt && xt1 == xt;
			}
			else
			{
				return modifier1 == modifier;
			}
		}
		return false;
	}

	public void updateSprite()
	{
		char[][] bild = new char[rwy][rwx];
		rahmen(bild);
		char[] fName = typ.name().toCharArray();
		System.arraycopy(fName, 0, bild[1], 2, fName.length);
		sprite.plane.update(0x7, 0x0, bild);
		sprite.ysp = rwy;
		sprite.xsp = rwx;
	}

	private void rahmen(char[][] bild)
	{
		for(int iy = 0; iy < rwy; iy++)
		{
			int iyc;
			if(iy == 0)
				iyc = 0;
			else if(iy >= rwy - 1)
				iyc = 2;
			else
				iyc = 1;
			for(int ix = 0; ix < rwx; ix++)
			{
				int ixc;
				if(ix == 0)
					ixc = 0;
				else if(ix >= rwx - 1)
					ixc = 2;
				else
					ixc = 1;
				bild[iy][ix] = rahmen.c[iyc][ixc];
			}
		}
	}

	public TSprite getSprite()
	{
		return sprite;
	}
}