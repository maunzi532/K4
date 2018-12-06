package k4.m.emulate;

import java.awt.*;
import java.awt.image.*;
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
	public Dimension startDimension()
	{
		return new Dimension(xs, ys);
	}

	@Override
	public int getInput()
	{
		return tasten.firstKey();
	}

	@Override
	public void toScreen(int[][] chars2, boolean subpixels)
	{
		format.format(frameActivate.reset(), chars2, subpixels);
		frameActivate.updateFrame();
	}

	@Override
	public void toScreen(BufferedImage image)
	{
		frameActivate.updateFrame(image);
	}

	@Override
	public void end()
	{
		frameActivate.end();
	}

	@Override
	public FrameFormatter getFormatter()
	{
		return format;
	}
}