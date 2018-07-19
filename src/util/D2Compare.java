package util;

public class D2Compare
{
	public static int[] clipArea(int ys0, int xs0, int yw0, int xw0, int ys1, int xs1, int yw1, int xw1)
	{
		//ys0, xs0, ys1, xs1, yw, xw
		int ys = Math.max(ys0, ys1);
		int xs = Math.max(xs0, ys1);
		int ye = Math.min(ys0 + yw0, ys1 + yw1);
		int xe = Math.min(xs0 + xw0, xs1 + xw1);
		return new int[]{ys - ys0, xs - xs0, ys - ys1, xs - xs1, ye - ys, xe - xs};
	}

	public static boolean[][] colorCollisions(int ys0, int xs0, int[][] a0, int ys1, int xs1, int[][] a1)
	{
		if(a0.length == 0 || a0[0].length == 0 || a1.length == 0 || a1[0].length == 0)
			return new boolean[0][0];
		int[] ar = clipArea(ys0, xs0, a0.length, a0[0].length, ys1, xs1, a1.length, a1[0].length);
		boolean[][] re = new boolean[ar[4]][ar[5]];
		for(int iy = 0; iy < ar[4]; iy++)
		{
			for(int ix = 0; ix < ar[5]; ix++)
			{
				int n0 = a0[ar[0] + iy][ar[1] + ix];
				int n1 = a1[ar[2] + iy][ar[3] + ix];
				re[n0][n1] = true;
			}
		}
		return re;
	}
}