package util;

public class CharTest
{
	public static void main(String[] args)
	{
		CharTest.charTest(args[0], args[1], args[2]);
	}

	public static void charTest(String c1, String start, String end)
	{
		boolean bold = false;
		if(c1.startsWith("x"))
		{
			bold = true;
			c1 = c1.substring(1);
		}
		int clr0 = Integer.parseUnsignedInt(c1, 16);
		int start0 = Integer.parseUnsignedInt(start, 16);
		int end0 = Integer.parseUnsignedInt(end, 16);
		StringBuilder sb = new StringBuilder();
		sb.append("\u001b[").append(bold ? "1;" : "21;").append(clr0 + 30).append('m');
		//sb.append("\u001b[").append(47).append('m');
		for(int i = start0; i < end0; i++)
		{
			sb.append((char) i);
		}
		sb.append("\u001b[0m");
		System.out.println(sb);
	}
}