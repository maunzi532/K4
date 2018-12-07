package sprites;

import plane.*;

public abstract class Sprite<T extends Plane>
{
	public int y;
	public int x;
	public int ysp;
	public int xsp;
	public int z;

	public Sprite(int ysp, int xsp, int z)
	{
		y = 0;
		x = 0;
		this.ysp = ysp;
		this.xsp = xsp;
		this.z = z;
	}

	public Sprite(int y, int x, int ysp, int xsp, int z)
	{
		this.y = y;
		this.x = x;
		this.ysp = ysp;
		this.xsp = xsp;
		this.z = z;
	}

	public abstract int yLEdge();

	public abstract int xLEdge();

	public abstract int yHEdge();

	public abstract int xHEdge();

	public abstract void updatePosition(int yScroll, int xScroll);

	public abstract T getPlane();
}