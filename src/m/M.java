package m;

import java.io.*;
import m.emulate.*;
import m.terminal.*;
import util.*;
import game.*;

public class M
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		Lader7.inJarCheck();
		CDevice device = null;
		boolean subpixels = false;
		if(args.length > 0)
		{
			int c = 0;
			if("sub".equals(args[0]))
			{
				subpixels = true;
				c++;
			}
			if(args.length > 2)
			{
				device = new FrameDevice(Integer.parseInt(args[c]),
						Integer.parseInt(args[c + 1]), 16, 8, Integer.parseInt(args[c + 2]));
			}
		}
		if(device == null)
		{
			device = new TerminalDevice();
		}
		Start.start(device, subpixels, false, DrawSetting.T, new A());
	}
}