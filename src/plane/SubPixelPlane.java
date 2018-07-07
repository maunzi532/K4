package plane;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.util.List;

public class SubPixelPlane implements Plane
{
	public static final int INVISIBLE = 0x10;

	private int subYShift;
	private int subXShift;
	private int subYSize;
	private int subXSize;
	private int yAdd;
	private int xAdd;
	private int[][] data;
	private BufferedImage image;

	public SubPixelPlane init(BufferedImage image)
	{
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

	public void drawModeImage(BufferedImage image)
	{
		this.image = image;
	}

	@Override
	public int codec(int y, int x)
	{
		y = y * 2 - subYShift + 1;
		x = x * 2 - subXShift + 1;
		return 0x40000000 | (data[y][x] << 24) | (data[y][x + 1] << 16) |
				(data[y + 1][x] << 8) | data[y + 1][x + 1];
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

	@Override
	public void draw(Graphics2D gd, int yc, int xc, List<Color> colors, boolean subpixels)
	{
		gd.drawImage(image, subXShift * xc / 2, subYShift * yc / 2,
				subXSize * xc / 2, subYSize * yc / 2, null);
	}
}