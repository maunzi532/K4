package k4.util;

import java.io.*;
import org.junit.*;

public class ImgNerfTest
{
	@Test
	public void imgNerfTest()
	{
		Lader7.inJarCheck();
		ImgNerf.convertImage("resources" + File.separator + "BodenT0.png");
	}
}