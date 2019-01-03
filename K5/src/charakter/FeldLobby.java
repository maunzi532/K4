package charakter;

import dungeonmap.*;
import plane.*;
import sprites.*;

public class FeldLobby
{
	private DungeonMap map;
	private int yk, xk;
	private int yt, xt;
	private int modifier;
	private MapTeil typ;
	private boolean verwendet;
	private TSprite sprite;

	public FeldLobby(PlaneRenderer screen, DungeonMap map, int y, int x)
	{
		this.map = map;
		yk = y / MapKarte.yw;
		xk = x / MapKarte.xw;
		yt = y % MapKarte.yw;
		xt = x % MapKarte.xw;
		modifier = map.getFeld(yk, xk).ortM(yt, xt);
		typ = map.ort(y, x);
		verwendet = map.verwendet(y, x);
		sprite = new TSprite(new TextPlane(0x0, 0x7, "Wugu"), screen.height, screen.width, 0);
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
		//sprite.plane.update();
	}

	public TSprite getSprite()
	{
		return sprite;
	}
}