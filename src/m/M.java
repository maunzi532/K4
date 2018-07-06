package m;

import java.awt.*;
import java.io.*;
import m.emulate.*;
import m.terminal.*;
import plane.*;
import sprites.*;
import util.*;

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
						Integer.parseInt(args[c + 1]), Integer.parseInt(args[c + 2]));
			}
		}
		if(device == null)
		{
			device = new TerminalDevice();
		}
		try
		{
			wugu(device, subpixels);
		}catch(InterruptedException | IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			device.end();
		}
	}

	public static void wugu(CDevice device, boolean subpixels) throws IOException, InterruptedException
	{
		Dimension xy = device.startDimension();
		PlaneRenderer planeRenderer = new PlaneRenderer(xy.height, xy.width, 0x0);
		SpriteList spriteList = new SpriteList(new PlaneFrame(3, 6, xy.height - 3, xy.width - 6));
		spriteList.addSprite(new XSprite(0, 0, 0, 0, 0, new SubPixelPlane().init(Lader7.imageResource("N1_IC.png"))));
		SubPixelPlane character = new SubPixelPlane().init(Lader7.imageResource("Char4_IC.png"));
		spriteList.addSprite(new XSprite(xy.height * 3 / 2, xy.width, character.getSubYSize(), character.getSubXSize() / 2, 1, character));
		spriteList.addSprite(new TSprite(0, 0, 0, 0, 2, new TextPlane(15, 0, "ARGH wugu ---", "ffcxgxhdx")));
		int ys = 0;
		int xs = 0;
		label68: while(true)
		{
			spriteList.updatePositions(ys * -5, xs * -9);
			planeRenderer.renderPlanes(subpixels, spriteList.planes(), spriteList.planeFrames());
			device.toScreen(planeRenderer.chars2, subpixels);
			int c = -1;
			while(c < 0)
				c = device.getInput();
			switch(c)
			{
				case '1':
					break label68;
				case 'w':
					ys--;
					break;
				case 's':
					ys++;
					break;
				case 'a':
					xs--;
					break;
				case 'd':
					xs++;
					break;
				case 'm':
					subpixels = !subpixels;
					break;
			}
		}
	}
}