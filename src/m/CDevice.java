package m;

import java.awt.*;
import java.io.*;

public interface CDevice
{
	Dimension startDimension() throws IOException, InterruptedException;

	int getInput() throws IOException;

	void toScreen(int[][] chars2, boolean subpixels);

	void end() throws IOException, InterruptedException;
}