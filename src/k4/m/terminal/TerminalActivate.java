package k4.m.terminal;

import java.awt.*;
import java.io.*;

public class TerminalActivate
{
	private static String terminalReset;

	public static Dimension start() throws IOException, InterruptedException
	{
		terminalReset = exec(stty("-g")).trim();
		int lines, cols;
		try
		{
			lines = Integer.parseInt(exec(tput("lines")).trim());
			cols = Integer.parseInt(exec(tput("cols")).trim());
		}catch(NumberFormatException e)
		{
			lines = 80;
			cols = 10;
		}
		exec(stty("-icanon min 1"));
		exec(stty("-echo"));
		return new Dimension(cols, lines);
	}

	public static void end() throws IOException, InterruptedException
	{
		if(terminalReset != null)
			exec(stty(terminalReset));
	}

	private static String[] tput(String parameter)
	{
		return new String[]{"bash", "-c", "tput " + parameter + " 2> /dev/tty"};
	}

	private static String[] stty(String parameter)
	{
		return new String[]{"sh", "-c", "stty " + parameter + " < /dev/tty"};
	}

	private static String exec(String... args) throws IOException, InterruptedException
	{
		Process process = new ProcessBuilder(args).redirectErrorStream(true).start();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = process.getInputStream();
		int c;
		while(true)
		{
			c = in.read();
			if(c == -1)
				break;
			out.write(c);
		}
		process.waitFor();
		return out.toString();
	}
}