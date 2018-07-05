package m.emulate;

import java.awt.*;
import java.util.*;
import java.util.List;
import util.*;

public class FrameFormatter
{
	public static final int YCHAR = 18;
	public static final int XCHAR = 9;

	private static Font fN = new Font("Monospace", Font.PLAIN, FrameFormatter.YCHAR * 2 / 3);
	private static Font fB = new Font("Monospace", Font.BOLD, FrameFormatter.YCHAR * 2 / 3);

	private static List<Integer> fuseChars = Arrays.asList(
			0x0020, 0x2597, 0x2596, 0x2584, 0x259d, 0x2590, 0x259e, 0x259f,
			0x2598, 0x259a, 0x258c, 0x2599, 0x2580, 0x259c, 0x259b, 0x2588);

	private static List<Integer> halfChars = Arrays.asList(
			0x0020, 0x2591, 0x2592, 0x2593, 0x2588);

	public static void format(Graphics2D gd, int[][] chars2, int colorSet, boolean subpixels)
	{
		int height = chars2.length;
		if(height <= 0)
			return;
		int width = chars2[0].length;
		int[] colors = colorSet > 0 ? TerminalColors.termColors1 : TerminalColors.termColors0;
		int lastBg = -1;
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				int bg1 = (chars2[iy][ix] >>> 24);
				if(bg1 != lastBg && bg1 < colors.length)
				{
					gd.setColor(new Color(colors[bg1]));
					lastBg = bg1;
				}
				gd.fillRect(XCHAR * ix, YCHAR * iy, XCHAR, YCHAR);
			}
		int lastFg = -1;
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				int c2 = chars2[iy][ix];
				int fg1 = (c2 >>> 16) & 255;
				if(fg1 >= colors.length)
					continue;
				if(fg1 != lastFg)
				{
					gd.setColor(new Color(colors[fg1]));
					gd.setFont(fg1 >= 8 ? fB : fN);
					lastFg = fg1;
				}
				int c3 = c2 & 65535;
				if(subpixels)
				{
					int index = fuseChars.indexOf(c3);
					if(index >= 0)
					{
						if((index & 0b1000) > 0)
							gd.fillRect(XCHAR * ix, YCHAR * iy, XCHAR / 2, YCHAR / 2);
						if((index & 0b0100) > 0)
							gd.fillRect(XCHAR * ix + XCHAR / 2, YCHAR * iy, XCHAR - XCHAR / 2, YCHAR / 2);
						if((index & 0b0010) > 0)
							gd.fillRect(XCHAR * ix, YCHAR * iy + YCHAR / 2, XCHAR / 2, YCHAR - YCHAR / 2);
						if((index & 0b0001) > 0)
							gd.fillRect(XCHAR * ix + XCHAR / 2, YCHAR * iy + YCHAR / 2, XCHAR - XCHAR / 2, YCHAR - YCHAR / 2);
					}
					else
					{
						gd.drawString(String.valueOf((char) c3), XCHAR * ix, YCHAR * iy + YCHAR * 2 / 3);
					}
				}
				else
				{
					int index = halfChars.indexOf(c3);
					if(index > 0)
					{
						gd.setColor(new Color(gd.getColor().getRed(), gd.getColor().getGreen(), gd.getColor().getBlue(), 255 * index / 4));
						gd.fillRect(XCHAR * ix, YCHAR * iy, XCHAR, YCHAR);
					}
					else if(index < 0)
					{
						gd.drawString(String.valueOf((char) c3), XCHAR * ix, YCHAR * iy + YCHAR * 2 / 3);
					}
				}

			}
	}
}