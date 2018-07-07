package util;

import java.io.*;
import org.junit.*;

public class ImgNerfTest
{
	@Before
	public void argh()
	{
		Lader7.inJarCheck();
	}

	@Test
	public void imgNerfTest()
	{
		try
		{
			ImgNerf.convertImage("resources" + File.separator + "Char6_N.png");
		}catch(IOException e)
		{
			Assert.fail();
		}
	}
}