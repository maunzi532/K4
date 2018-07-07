package m;

import java.io.*;
import m.emulate.*;
import org.junit.*;
import util.*;

public class EmulationTest
{
	//F6 48*170 - 16*8 - 768*1366
	//TE 62*212 - 17*9 - 1056*1920
	//JF 58*213 - 18*9 - 1056*1920
	//JF 48*170 - 22*11 - 1056*1920

	private CDevice device;

	@Before
	public void init()
	{
		Lader7.inJarCheck();
	}

	@Test
	public void normal()
	{
		device = new FrameDevice(48, 170, 0);
		init();
		try
		{
			M.wugu(device, true, false);
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

	@Test
	public void drawMode()
	{
		device = new FrameDevice(48, 170, 0);
		init();
		try
		{
			M.wugu(device, true, true);
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