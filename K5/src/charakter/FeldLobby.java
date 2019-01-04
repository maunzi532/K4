package charakter;

import dungeonmap.*;
import kartenset.*;
import plane.*;
import sprites.*;

public class FeldLobby implements Zeichner3
{
	private static final int rwy = 10;
	private static final int rwx = 40;

	private DungeonMap map;
	private int yk, xk;
	private int yt, xt;
	private int modifier;
	private MapTeil typ;
	private boolean verwendet;
	private TSprite sprite;
	private Rahmen3 rahmen2;
	private Rahmen3 rahmen1;

	public FeldLobby(DungeonMap map, int y, int x, SpriteList spriteList)
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
		rahmen2 = new Rahmen3(2);
		rahmen1 = new Rahmen3(1);
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

	@Override
	public int yw()
	{
		return rwy;
	}

	@Override
	public int xw()
	{
		return rwx;
	}

	public void updateSprite()
	{
		char[][] bild = erstellen();
		rahmen(bild, rahmen2, 2);
		char[] fName = verwendet ? typ.titel1.toCharArray() : typ.titel0.toCharArray();
		System.arraycopy(fName, 0, bild[1], 2, fName.length);
		rahmenInnen(bild, rahmen1, rwy - 4, rwy - 1, 3, rwx / 2 - 2);
		textEinpassen(bild, verwendet ? typ.ok1 : typ.ok0, rwy - 3, 3, rwx / 2 - 2);
		rahmenInnen(bild, rahmen2, rwy - 4, rwy - 1, rwx / 2 + 2, rwx - 3);
		textEinpassen(bild, "Zurück", rwy - 3, rwx / 2 + 2, rwx - 3);
		sprite.plane.update(0x7, 0x0, bild);
		sprite.ysp = rwy;
		sprite.xsp = rwx;
	}

	public TSprite getSprite()
	{
		return sprite;
	}
}