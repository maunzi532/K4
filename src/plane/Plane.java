package plane;

public interface Plane
{
	int codec(int y, int x);

	int getYShift();

	int getXShift();

	int getYSize();

	int getXSize();

	void setYShift(int yShift);

	void setXShift(int xShift);
}