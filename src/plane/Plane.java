package plane;

import java.awt.*;
import java.util.List;

public interface Plane
{
	int codec(int y, int x);

	int getYShift();

	int getXShift();

	int getYSize();

	int getXSize();

	void setYShift(int yShift);

	void setXShift(int xShift);

	void draw(Graphics2D gd, int yc, int xc, List<Color> colors, boolean subpixels);
}