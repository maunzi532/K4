package m.emulate;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;
import m.*;
import util.*;

public class FrameFormatter implements CFormatter
{
	private static final String fontName = "Ubuntu Mono";

	private static final List<Integer> fuseChars = Arrays.asList(
			0x0020, 0x2597, 0x2596, 0x2584, 0x259d, 0x2590, 0x259e, 0x259f,
			0x2598, 0x259a, 0x258c, 0x2599, 0x2580, 0x259c, 0x259b, 0x2588);

	private static final List<Integer> halfChars = Arrays.asList(
			0x0020, 0x2591, 0x2592, 0x2593, 0x2588);

	public int ychar;
	public int xchar;
	private List<Color> colors;
	private Font fN;
	private Font fB;

	public FrameFormatter(int ychar, int xchar, int colorSet)
	{
		this.ychar = ychar;
		this.xchar = xchar;
		int[] colors0 = colorSet > 0 ? TerminalColors.termColors1 : TerminalColors.termColors0;
		colors = Arrays.stream(colors0).mapToObj(Color::new).collect(Collectors.toList());
		fN = new Font(fontName, Font.PLAIN, ychar);
		fB = new Font(fontName, Font.BOLD, ychar);
	}

	public void format(Graphics2D gd, int[][] chars2, boolean subpixels)
	{
		int height = chars2.length;
		if(height <= 0)
			return;
		int width = chars2[0].length;
		int lastBg = -1;
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				int bg1 = (chars2[iy][ix] >>> 24);
				if(bg1 >= colors.size())
					continue;
				if(bg1 != lastBg)
				{
					gd.setColor(colors.get(bg1));
					lastBg = bg1;
				}
				gd.fillRect(xchar * ix, ychar * iy, xchar, ychar);
			}
		int lastFg = -1;
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				int c2 = chars2[iy][ix];
				int fg1 = (c2 >>> 16) & 255;
				if(fg1 >= colors.size())
					continue;
				if(fg1 != lastFg)
				{
					gd.setColor(colors.get(fg1));
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
							gd.fillRect(xchar * ix, ychar * iy, xchar / 2, ychar / 2);
						if((index & 0b0100) > 0)
							gd.fillRect(xchar * ix + xchar / 2, ychar * iy, xchar - xchar / 2, ychar / 2);
						if((index & 0b0010) > 0)
							gd.fillRect(xchar * ix, ychar * iy + ychar / 2, xchar / 2, ychar - ychar / 2);
						if((index & 0b0001) > 0)
							gd.fillRect(xchar * ix + xchar / 2, ychar * iy + ychar / 2, xchar - xchar / 2, ychar - ychar / 2);
					}
					else
					{
						gd.drawString(String.valueOf((char) c3), xchar * ix, ychar * iy + ychar);
					}
				}
				else
				{
					int index = halfChars.indexOf(c3);
					if(index > 0)
					{
						gd.setColor(new Color(gd.getColor().getRed(), gd.getColor().getGreen(), gd.getColor().getBlue(), 255 * index / 4));
						gd.fillRect(xchar * ix, ychar * iy, xchar, ychar);
					}
					else if(index < 0)
					{
						gd.drawString(String.valueOf((char) c3), xchar * ix, ychar * iy + ychar);
					}
				}

			}
	}

	public void emulateFormat(Graphics2D gd, int ysh, int xsh, int ysize, int xsize, int[][] data)
	{
		int lastBg = -1;
		for(int iy = 0; iy < ysize; iy++)
			for(int ix = 0; ix < xsize; ix++)
			{
				int bg1 = (data[iy][ix] >>> 24);
				if(bg1 >= colors.size())
					continue;
				if(bg1 != lastBg)
				{
					gd.setColor(colors.get(bg1));
					lastBg = bg1;
				}
				gd.fillRect(xchar * (xsh + ix), ychar * (ysh + iy), xchar, ychar);
			}
		int lastFg = -1;
		for(int iy = 0; iy < ysize; iy++)
			for(int ix = 0; ix < xsize; ix++)
			{
				int c2 = data[iy][ix];
				int fg1 = (c2 >>> 16) & 255;
				if(fg1 >= colors.size())
					continue;
				if(fg1 != lastFg)
				{
					gd.setColor(colors.get(fg1));
					gd.setFont(fg1 >= 8 ? fB : fN);
					lastFg = fg1;
				}
				gd.drawString(String.valueOf((char) (c2 & 65535)), xchar * (xsh + ix), ychar * (ysh + iy) + ychar * 2 / 3);
			}
	}

	@Override
	public int ychar()
	{
		return ychar;
	}

	@Override
	public int xchar()
	{
		return xchar;
	}

	public List<Color> getColors()
	{
		return colors;
	}
}