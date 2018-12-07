package plane;

import java.awt.*;
import java.util.*;
import m.emulate.*;
import util.*;

public class TextPlane implements Plane
{
	private static final int INVISIBLECONV = 0x10000000;

	private int ysh;
	private int xsh;
	private int ysize;
	private int xsize;
	private int[][] data;

	public TextPlane(int fg, int bg, String... text)
	{
		ysize = text.length;
		System.out.println("ysize = " + ysize);
		xsize = Arrays.stream(text).mapToInt(String::length).max().orElse(0);
		System.out.println("xsize = " + xsize);
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
	public void draw(Graphics2D gd, FrameFormatter format, DrawSetting drawSetting)
	{
		format.emulateFormat(gd, ysh, xsh, ysize, xsize, data);
	}
}