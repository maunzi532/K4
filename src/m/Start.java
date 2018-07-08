package m;

import java.awt.*;
import java.io.*;
import java.util.*;
import plane.*;

public class Start
{
	public static void start(CDevice device, boolean subpixels, boolean drawMode, Game game)
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
					device.toScreen(planeRenderer.renderImage(device.getFormatter(), planes, frames));
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