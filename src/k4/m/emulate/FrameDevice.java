package k4.m.emulate;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import k4.m.*;

public class FrameDevice implements CDevice
{
	private int ys, xs;
	private FrameInput tasten;
	private FrameFormatter format;
	private FrameActivate frameActivate;

	public FrameDevice(int ys, int xs, int ychar, int xchar, int colorSet)
	{
		this.ys = ys;
		this.xs = xs;
		tasten = new FrameInput();
		format = new FrameFormatter(ychar, xchar, colorSet);
		frameActivate = new FrameActivate(xs, ys, tasten, format);
	}

	@Override
	public Dimension startDimension() throws IOException, InterruptedException
	{
		return new Dimension(xs, ys);
	}

	@Override
	public int getInput() throws IOException
	{
		return tasten.firstKey();
	}

	@Override
	public void toScreen(int[][] chars2, boolean subpixels)
	{
		format.format(frameActivate.reset(), chars2, subpixels);
		frameActivate.imageToFrame();
	}

	@Override
	public void toScreen(BufferedImage image)
	{
		frameActivate.imageToFrame(image);
	}

	@Override
	public void end() throws IOException, InterruptedException
	{
		frameActivate.end();
	}

	@Override
	public FrameFormatter getFormatter()
	{
		return format;
	}
}