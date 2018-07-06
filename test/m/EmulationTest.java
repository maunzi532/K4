package m;

import java.io.*;
import m.emulate.*;
import org.junit.*;
import util.*;

public class EmulationTest
{
	private CDevice device;

	public void init()
	{
		Lader7.inJarCheck();
		device = new FrameDevice(50, 100, 0);
	}

	@Test
	public void test0()
	{
		init();
		try
		{
			M.wugu(device, true);
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