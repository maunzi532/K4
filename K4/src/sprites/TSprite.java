package sprites;

import plane.*;

public class TSprite extends Sprite
{
	public TextPlane plane;

	public TSprite(int y, int x, int ysp, int xsp, int z, TextPlane plane)
	{
		super(y, x, ysp, xsp, z);
		this.plane = plane;
	}

	public int yLEdge()
	{
		return y - ysp;
	}

	public int xLEdge()
	{
		return x - xsp;
	}

	public int yHEdge()
	{
		return y + plane.getYSize() * 2 - ysp;
	}

	public int xHEdge()
	{
		return x + plane.getXSize() * 2 - xsp;
	}

	public void updatePosition(int yScroll, int xScroll)
	{
		plane.setYShift((yLEdge() + yScroll) / 2);
		plane.setXShift((xLEdge() + xScroll) / 2);
	}

	@Override
	public Plane getPlane()
	{
		return plane;
	}
}