package plane;

import java.awt.*;
import java.util.*;
import java.util.List;
import m.emulate.*;

public class TextPlane implements Plane
{
	private static Font fN = new Font("Monospace", Font.PLAIN, FrameFormatter.YCHAR * 2 / 3);
	private static Font fB = new Font("Monospace", Font.BOLD, FrameFormatter.YCHAR * 2 / 3);

	private static final int INVISIBLECONV = 0x10000000;

	private int ysh;
	private int xsh;
	private int ysize;
	private int xsize;
	private int[][] data;

	public TextPlane(int fg, int bg, String... text)
	{
		ysize = text.length;
		xsize = Arrays.stream(text).mapToInt(String::length).max().orElse(0);
		data = new int[ysize][xsize];
		for(int iy = 0; iy < ysize; iy++)
			for(int ix = 0; ix < xsize; ix++)
			{
				data[iy][ix] = ix < text[iy].length() ? ((int) text[iy].charAt(ix)) | fg << 16 | bg << 24 : INVISIBLECONV;
			}
	}

	public int codec(int y, int x)
	{
		return data[y - ysh][x - xsh];
	}

	@Override
	public int getYShift()
	{
		return ysh;
	}

	@Override
	public int getXShift()
	{
		return xsh;
	}

	@Override
	public int getYSize()
	{
		return ysize;
	}

	@Override
	public int getXSize()
	{
		return xsize;
	}

	@Override
	public void setYShift(int yShift)
	{
		this.ysh = yShift;
	}

	@Override
	public void setXShift(int xShift)
	{
		this.xsh = xShift;
	}

	@Override
	public void draw(Graphics2D gd, int yc, int xc, List<Color> colors, boolean subpixels)
	{
		int lastBg = -1;
		for(int iy = 0; iy < ysize; iy++)
			for(int ix = 0; ix < xsize; ix++)
			{
				int bg1 = (data[iy][ix] >>> 24);
				if(bg1 != lastBg)
				{
					gd.setColor(colors.get(bg1));
					lastBg = bg1;
				}
				gd.fillRect(xc * (xsh + ix), yc * (ysh + iy), xc, yc);
			}
		int lastFg = -1;
		for(int iy = 0; iy < ysize; iy++)
			for(int ix = 0; ix < xsize; ix++)
			{
				int c2 = data[iy][ix];
				int fg1 = (c2 >>> 16) & 255;
				if(fg1 >= colors.size() - 1)
					continue;
				if(fg1 != lastFg)
				{
					gd.setColor(colors.get(fg1));
					gd.setFont(fg1 >= 8 ? fB : fN);
					lastFg = fg1;
				}
				gd.drawString(String.valueOf((char) (c2 & 65535)), xc * (xsh + ix), yc * (ysh + iy) + yc * 2 / 3);
			}
	}
}