package m;

import java.awt.*;
import java.util.List;

public interface CFormatter
{
	int ychar();
	int xchar();
	List<Color> getColors();
	void emulateFormat(Graphics2D gd, int ysh, int xsh, int ysize, int xsize, int[][] data);
}