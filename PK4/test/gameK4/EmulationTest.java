package gameK4;

import m.*;
import m.emulate.*;
import util.*;
import org.junit.*;

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
		device = new FrameDevice(48, 170, 12, 6, 0);
		Start.start(device, true, false, DrawSetting.T, new A());
	}

	@Test
	public void drawMode()
	{
		device = new FrameDevice(48, 170, 16, 8, 0);
		Start.start(device, true, true, DrawSetting.A, new A());
	}
}