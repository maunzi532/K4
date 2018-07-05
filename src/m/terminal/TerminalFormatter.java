package m.terminal;

public class TerminalFormatter
{
	private static final String SEQ = "\u001b[";

	public static String format(int[][] chars2)
	{
		int height = chars2.length;
		if(height <= 0)
			return "";
		int width = chars2[0].length;
		StringBuilder sb = new StringBuilder();
		int lastFg = -1;
		int lastBg = -1;
		for(int iy = 0; iy < height; iy++)
		{
			sb.append(SEQ).append(iy + 1).append('H');
			for(int ix = 0; ix < width; ix++)
			{
				int c2 = chars2[iy][ix];
				int fg1 = (c2 >>> 16) & 255;
				int bg1 = (c2 >>> 24);
				if(fg1 != lastFg)
				{
					sb.append(SEQ).append(cnumFg(fg1)).append('m');
					lastFg = fg1;
				}
				if(bg1 != lastBg)
				{
					sb.append(SEQ).append(cnumBg(bg1)).append('m');
					lastBg = bg1;
				}
				sb.append((char) (c2 & 65535));
			}
		}
		sb.append(SEQ).append("0m");
		return sb.toString();
	}

	private static String cnumFg(int num)
	{
		return (num < 8 ? "21;" : "1;") + ((num % 8) + 30);
	}

	private static String cnumBg(int num)
	{
		return String.valueOf((num % 8) + 40);
	}
}