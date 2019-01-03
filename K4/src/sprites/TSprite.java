package sprites;

import plane.*;

public class TSprite extends Sprite<TextPlane>
{
	public TextPlane plane;

	public TSprite(int ysp, int xsp, int z, TextPlane plane)
	{
		super(ysp, xsp, z);
		this.plane = plane;
	}

	public TSprite(int y, int x, int ysp, int xsp, int z, TextPlane plane)
	{
		super(y, x, ysp, xsp, z);
		this.plane = plane;
	}

	public TSprite(TextPlane plane, int z)
	{
		super(plane.getYSize(), plane.getXSize(), z);
		this.plane = plane;
	}

	public TSprite(TextPlane plane, int y, int x, int z)
	{
		super(y, x, plane.getYSize(), plane.getXSize(), z);
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
	public TextPlane getPlane()
	{
		return plane;
	}
}