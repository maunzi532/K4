package m;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public interface CDevice
{
	Dimension startDimension() throws IOException, InterruptedException;

	int getInput() throws IOException;

	void toScreen(int[][] chars2, boolean subpixels);

	void toScreen(BufferedImage image);

	void end() throws IOException, InterruptedException;
}