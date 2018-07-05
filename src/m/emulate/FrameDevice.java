package m.emulate;

import java.awt.*;
import java.io.*;
import m.*;

public class FrameDevice implements CDevice
{
	private int ys, xs;
	private int colorSet;
	private FrameActivate frameActivate;
	private FrameInput tasten;

	public FrameDevice(int ys, int xs, int colorSet)
	{
		this.ys = ys;
		this.xs = xs;
		this.colorSet = colorSet;
		tasten = new FrameInput();
		frameActivate = new FrameActivate(xs, ys, tasten);
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
		FrameFormatter.format(frameActivate.reset(), chars2, colorSet, subpixels);
		frameActivate.imageToFrame();
	}

	@Override
	public void end() throws IOException, InterruptedException
	{
		frameActivate.end();
	}
}