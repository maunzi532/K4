package m;

import java.awt.*;
import java.io.*;
import m.emulate.*;
import org.junit.*;
import plane.*;
import sprites.*;
import util.*;

public class EmulationTest
{
	public CDevice device;

	public void init()
	{
		Lader7.inJarCheck();
		device = new FrameDevice(50, 100, 0);
	}

	@Test
	public void test0()
	{
		boolean subpixels = true;
		init();
		try
		{
			Dimension xy = device.startDimension();
			PlaneRenderer planeRenderer = new PlaneRenderer(xy.height, xy.width, 0x0);
			SpriteList spriteList = new SpriteList(new PlaneFrame(3, 6, xy.height - 3, xy.width - 6));
			spriteList.addSprite(new XSprite(0, 0, 0, 0, 0, new SubPixelPlane().init(Lader7.imageResource("N1C.png"))));
			SubPixelPlane character = new SubPixelPlane().init(Lader7.imageResource("NormalC.png"));
			spriteList.addSprite(new XSprite(xy.height * 3 / 2, xy.width, character.getSubYSize(), character.getSubXSize() / 2, 1, character));
			spriteList.addSprite(new TSprite(0, 0, 0, 0, 2, new TextPlane(15, 0, "ARGH wugu ---", "ffcxgxhdx")));
			int ys = 0;
			int xs = 0;
			while(true)
			{
				spriteList.updatePositions(ys * -5, xs * -9);
				planeRenderer.renderPlanes(subpixels, spriteList.planes(), spriteList.planeFrames());
				device.toScreen(planeRenderer.chars2, subpixels);
				int c = device.getInput();
				if(c < 0)
					continue;
				if(c == 49)
					break;
				else if(c == 'w')
					ys--;
				else if(c == 's')
					ys++;
				else if(c == 'a')
					xs--;
				else if(c == 'd')
					xs++;
				else if(c == 'm')
					subpixels = !subpixels;
			}
		}catch(InterruptedException | IOException e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		finally
		{
			try
			{
				device.end();
			}catch(IOException | InterruptedException e)
			{
				e.printStackTrace();
				Assert.fail();
			}
		}
	}
}