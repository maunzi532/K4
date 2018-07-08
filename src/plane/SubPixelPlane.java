package plane;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import m.emulate.*;
import util.*;

public class SubPixelPlane implements Plane
{
	public static final int INVISIBLE = 0x10;

	private int subYShift;
	private int subXShift;
	private int subYSize;
	private int subXSize;
	private int yAdd;
	private int xAdd;
	private boolean flippedY;
	private boolean flippedX;
	private int[][] data;
	private ImageLoader loader;

	public SubPixelPlane init(String baseLocation, String... extra)
	{
		loader = new ImageLoader(baseLocation, extra);
		BufferedImage image = loader.getImage(DrawSetting.C);
		WritableRaster raster = image.getRaster();
		int numBands = raster.getNumBands();
		int[] temp = new int[numBands];
		int yl = image.getHeight();
		int xl = image.getWidth();
		data = new int[yl + 2][xl + 2];
		Arrays.fill(data[0], INVISIBLE);
		for(int iy = 0; iy < yl; iy++)
		{
			int[] d1 = data[iy + 1];
			d1[0] = INVISIBLE;
			for(int ix = 0; ix < xl; ix++)
			{
				d1[ix + 1] = raster.getPixel(ix, iy, temp)[0];
			}
			d1[xl + 1] = INVISIBLE;
		}
		Arrays.fill(data[yl + 1], INVISIBLE);
		setSubYSize(yl);
		setSubXSize(xl);
		return this;
	}

	public SubPixelPlane init(int[][] data0)
	{
		int yl = data0.length;
		int xl = yl == 0 ? 0 : data0[0].length;
		data = new int[yl + 2][xl + 2];
		Arrays.fill(data[0], INVISIBLE);
		for(int iy = 0; iy < yl; iy++)
		{
			int[] d1 = data[iy + 1];
			d1[0] = INVISIBLE;
			System.arraycopy(data0[iy], 0, d1, 1, xl);
			d1[xl + 1] = INVISIBLE;
		}
		Arrays.fill(data[yl + 1], INVISIBLE);
		setSubYSize(yl);
		setSubXSize(xl);
		return this;
	}

	@Override
	public int codec(int y, int x)
	{
		y = y * 2 - subYShift;
		x = x * 2 - subXShift;
		return 0x40000000 | (data(y, x) << 24) | (data(y, x + 1) << 16) |
				(data(y + 1, x) << 8) | data(y + 1, x + 1);
	}

	private int data(int y, int x)
	{
		return data[flippedY ? subYSize - y : y + 1][flippedX ? subXSize - x : x + 1];
	}

	@Override
	public int getYShift()
	{
		return halfDown(subYShift);
	}

	@Override
	public int getXShift()
	{
		return halfDown(subXShift);
	}

	@Override
	public int getYSize()
	{
		return halfUp(subYSize + yAdd);
	}

	@Override
	public int getXSize()
	{
		return halfUp(subXSize + xAdd);
	}

	private void calcYAdd()
	{
		yAdd = subYSize % 2 != 0 ? 1 : (subYShift % 2 == 0) ? 0 : 2;
	}

	private void calcXAdd()
	{
		xAdd = subXSize % 2 != 0 ? 1 : (subXShift % 2 == 0) ? 0 : 2;
	}

	public int getSubYShift()
	{
		return subYShift;
	}

	public void setSubYShift(int subYShift)
	{
		this.subYShift = subYShift;
		calcYAdd();
	}

	@Override
	public void setYShift(int yShift)
	{
		subYShift = Math.abs(subYShift) % 2 + yShift;
	}

	public int getSubXShift()
	{
		return subXShift;
	}

	public void setSubXShift(int subXShift)
	{
		this.subXShift = subXShift;
		calcXAdd();
	}

	@Override
	public void setXShift(int xShift)
	{
		subXShift = Math.abs(subXShift) % 2 + xShift;
	}

	public int getSubYSize()
	{
		return subYSize;
	}

	public void setSubYSize(int subYSize)
	{
		this.subYSize = subYSize;
		calcYAdd();
	}

	public int getSubXSize()
	{
		return subXSize;
	}

	public void setSubXSize(int subXSize)
	{
		this.subXSize = subXSize;
		calcXAdd();
	}

	private int halfUp(int num)
	{
		if(num > 0)
			return (num + 1) / 2;
		else
			return num / 2;
	}

	private int halfDown(int num)
	{
		if(num >= 0)
			return num / 2;
		else
			return (num - 1) / 2;
	}

	public boolean isFlippedY()
	{
		return flippedY;
	}

	public boolean isFlippedX()
	{
		return flippedX;
	}

	public void setFlippedY(boolean flippedY)
	{
		this.flippedY = flippedY;
	}

	public void setFlippedX(boolean flippedX)
	{
		this.flippedX = flippedX;
	}

	@Override
	public void draw(Graphics2D gd, FrameFormatter format, DrawSetting drawSetting)
	{
		BufferedImage image0 = loader.getImage(drawSetting);
		gd.drawImage(image0, subXShift * format.xchar / 2, subYShift * format.ychar / 2,
				(subXShift + subXSize) * format.xchar / 2, (subYShift + subYSize) * format.ychar / 2,
				flippedX ? image0.getWidth() : 0, flippedY ? image0.getHeight() : 0,
				flippedX ? 0 : image0.getWidth(), flippedY ? 0 : image0.getHeight(), null);
	}
}