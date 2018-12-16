package charakter;

import plane.*;
import sprites.*;

public class MapCursor
{
	public Sprite cursorSprite;
	public int y, x;

	public MapCursor(int y, int x)
	{
		this.y = y;
		this.x = x;
		cursorSprite = new TSprite(0, 0, 2, new TextPlane(0x6, 0x5, "11111", "11111", "11111"));
	}
}