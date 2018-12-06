package plane;

import java.awt.*;
import m.emulate.*;
import util.*;

public interface Plane
{
	int codec(int y, int x);

	int getYShift();

	int getXShift();

	int getYSize();

	int getXSize();

	void setYShift(int yShift);

	void setXShift(int xShift);

	void draw(Graphics2D gd, FrameFormatter format, DrawSetting drawSetting);

	default int[][] collisionData()
	{
		throw new RuntimeException();
	}
}