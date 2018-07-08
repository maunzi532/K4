package util;

import java.io.*;
import org.junit.*;

public class ImgNerfTest
{
	@Test
	public void imgNerfTest()
	{
		Lader7.inJarCheck();
		ImgNerf.convertImage("resources" + File.separator + "Char6_N.png");
	}
}