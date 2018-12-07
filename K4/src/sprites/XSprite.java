package sprites;

import plane.*;

public class XSprite extends Sprite
{
	public SubPixelPlane plane;

	public XSprite(int y, int x, int ysp, int xsp, int z, SubPixelPlane plane)
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
		return y + plane.getSubYSize() - ysp;
	}

	public int xHEdge()
	{
		return x + plane.getSubXSize() - xsp;
	}

	public void updatePosition(int yScroll, int xScroll)
	{
		plane.setSubYShift(yLEdge() + yScroll);
		plane.setSubXShift(xLEdge() + xScroll);
	}

	@Override
	public Plane getPlane()
	{
		return plane;
	}
}