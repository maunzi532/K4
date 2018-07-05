package m.terminal;

import java.awt.*;
import java.io.*;
import m.*;

public class TerminalDevice implements CDevice
{
	@Override
	public Dimension startDimension() throws IOException, InterruptedException
	{
		return TerminalActivate.start();
	}

	@Override
	public int getInput() throws IOException
	{
		if(System.in.available() == 0)
			return -1;
		return System.in.read();
	}

	@Override
	public void toScreen(int[][] chars2, boolean subpixels)
	{
		System.out.print(TerminalFormatter.format(chars2));
	}

	@Override
	public void end() throws IOException, InterruptedException
	{
		TerminalActivate.end();
	}
}