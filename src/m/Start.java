package m;

import java.awt.*;
import java.io.*;
import java.util.*;
import plane.*;
import util.*;

public class Start
{
	public static void start(CDevice device, boolean subpixels, boolean drawMode, DrawSetting drawSetting, Game game)
	{
		try
		{
			boolean drawModeStart = device.getFormatter() != null;
			drawMode = drawMode && drawModeStart;
			Dimension xy = device.startDimension();
			PlaneRenderer planeRenderer = new PlaneRenderer(xy.height, xy.width, 0x0);
			game.init(planeRenderer);
			label68: while(true)
			{
				ArrayList<Plane> planes = new ArrayList();
				ArrayList<PlaneFrame> frames = new ArrayList<>();
				game.fillLists(planes, frames);
				if(drawMode)
					device.toScreen(planeRenderer.renderImage(device.getFormatter(), drawSetting, planes, frames));
				else
					device.toScreen(planeRenderer.renderPlanes(subpixels, planes, frames), subpixels);

				Thread.sleep(30);
				int c = device.getInput();
				switch(c)
				{
					case '1':
						break label68;
					case 'm':
						subpixels = !subpixels;
						break;
					case 'n':
						if(drawModeStart)
							drawMode = !drawMode;
						break;
					case '7':
						if(drawModeStart)
							drawSetting = DrawSetting.T;
						break;
					case '8':
						if(drawModeStart)
							drawSetting = DrawSetting.E;
						break;
					case '9':
						if(drawModeStart)
							drawSetting = DrawSetting.A;
						break;
					default:
						game.handleInput(c);
				}
			}
		}catch(InterruptedException | IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				device.end();
			}catch(IOException | InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}