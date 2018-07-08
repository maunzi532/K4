package util;

import java.awt.image.*;

public class ImgNerf
{
	private BufferedImage input;
	private int height;
	private int width;
	public BufferedImage converted;

	public ImgNerf(BufferedImage input)
	{
		this.input = input;
		height = input.getHeight();
		width = input.getWidth();
	}

	public void convert()
	{
		converted = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY);
		Raster r0 = input.getRaster();
		WritableRaster r1 = converted.getRaster();
		int[] buffer0 = new int[r0.getNumBands()];
		int[] buffer1 = new int[r1.getNumBands()];
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				buffer1[0] = nearestColor(r0.getPixel(ix, iy, buffer0), TerminalColors.termColors0);
				r1.setPixel(ix, iy, buffer1);
			}
	}

	public BufferedImage reconvert(int[] conv)
	{
		BufferedImage reconverted = new BufferedImage(width, height * 2, BufferedImage.TYPE_INT_ARGB);
		Raster r0 = converted.getRaster();
		WritableRaster r1 = reconverted.getRaster();
		int[] buffer0 = new int[r0.getNumBands()];
		int[] buffer1 = new int[r1.getNumBands()];
		int[] buffer2 = new int[r1.getNumBands()];
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				r0.getPixel(ix, iy, buffer0);
				if(buffer0[0] == 0b00010000)
				{
					r1.setPixel(ix, iy * 2, buffer2);
					r1.setPixel(ix, iy * 2 + 1, buffer2);
				}
				else
				{
					buffer1[0] = conv[buffer0[0]];
					buffer1[2] = buffer1[0] & 0xff;
					buffer1[1] = (buffer1[0] >>> 8) & 0xff;
					buffer1[0] = buffer1[0] >>> 16;
					buffer1[3] = 0xff;
					r1.setPixel(ix, iy * 2, buffer1);
					r1.setPixel(ix, iy * 2 + 1, buffer1);
				}
			}
		return reconverted;
	}

	private int nearestColor(int[] clr0, int[] termColors)
	{
		if(clr0.length >= 4 && clr0[3] <= 0)
			return 0b00010000;
		if(clr0.length == 1)
		{
			return clr0[0];
		}
		int a = -1;
		int low = 768;
		for(int i = 0; i < termColors.length; i++)
		{
			int l1 = Math.abs(clr0[0] - (termColors[i] >>> 16)) +
					Math.abs(clr0[1] - ((termColors[i] >>> 8) & 255)) +
					Math.abs(clr0[2] - (termColors[i] & 255));
			if(l1 < low)
			{
				a = i;
				low = l1;
			}
		}
		return a;
	}

	public static void main(String[] args)
	{
		Lader7.inJarCheck();
		convertImage(args[0]);
	}

	public static void convertImage(String s)
	{
		ImgNerf nerf = new ImgNerf(Lader7.imageSavedata(s));
		nerf.convert();
		if(s.endsWith(".png"))
			s = s.substring(0, s.length() - 4);
		Lader7.imageStore(s + "C.png", nerf.converted);
		Lader7.imageStore(s + "T.png", nerf.reconvert(TerminalColors.termColors0));
		Lader7.imageStore(s + "E.png", nerf.reconvert(TerminalColors.termColors1));
	}
}