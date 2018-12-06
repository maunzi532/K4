package k4.plane;

public class ClrFuse
{
	/*
	0b0000  0x0020
	0b0001  0x2597
	0b0010  0x2596
	0b0011  0x2584
	0b0100  0x259d
	0b0101  0x2590
	0b0110  0x259e
	0b0111  0x259f
	0b1000  0x2598
	0b1001  0x259a
	0b1010  0x258c
	0b1011  0x2599
	0b1100  0x2580
	0b1101  0x259c
	0b1110  0x259b
	0b1111  0x2588
	 */
	private static int[] fuseChars = new int[]
			{
					0x0020, 0x2597, 0x2596, 0x2584, 0x259d, 0x2590, 0x259e, 0x259f,
					0x2598, 0x259a, 0x258c, 0x2599, 0x2580, 0x259c, 0x259b, 0x2588
			};

	private static int[] halfChars = new int[]
			{
					0x0020, 0x2591, 0x2592, 0x2593, 0x2588
			};

	private static boolean flag = false;

	public static int[] extractclrs(int clr)
	{
		int ok = 4 - Integer.bitCount(clr & 0x10101010);
		int[] arr = new int[ok];
		int c = 0;
		for(int i = 0; i < 4; i++)
		{
			int n1 = (clr >>> (i * 8)) & 0x1f;
			if((n1 & 0x10) == 0)
			{
				arr[c] = n1;
				c++;
			}
		}
		return arr;
	}

	public static int toBg(int clr)
	{
		if(clr == 0b1000)
			return 0b0111;
		return clr & 0b0111;
	}

	public static int avgclr(boolean bg, int... clrs)
	{
		flag = false;
		if(clrs.length == 0)
			return 0x0000;
		boolean ok1 = true;
		for(int i = 1; i < clrs.length; i++)
			if(clrs[i] != clrs[0])
				ok1 = false;
		if(ok1)
		{
			if(!bg && ((clrs[0] & 0b1000) > 0))
				flag = true;
			if(bg)
			{
				if(clrs[0] == 0b1000)
					return 0b0111;
				return clrs[0] & 0b0111;
			}
			return clrs[0];
		}
		int[] rgb = new int[3];
		for(int i = 0; i < clrs.length; i++)
		{
			rgb[0] += (clrs[i] & 0x1) + (clrs[i] >>> 3) - 1;
			rgb[1] += ((clrs[i] & 0x2) >>> 1) + (clrs[i] >>> 3) - 1;
			rgb[2] += ((clrs[i] & 0x4) >>> 2) + (clrs[i] >>> 3) - 1;
		}
		int light = rgb[0] + rgb[1] + rgb[2];
		if(!bg && light >= 0)
			flag = true;
		int re0;
		if(rgb[0] == rgb[1])
		{
			if(rgb[0] == rgb[2])
			{
				if(bg)
					return light < 0 ? 0b0000 : 0b0111;
				else if(light <= 0)
					return light < 0 ? 0b0000 : 0b1000;
				else
					return 0b1111;
			}
			else if(rgb[0] > rgb[2])
				re0 = 0b0011;
			else
				re0 = 0b0100;
		}
		else if(rgb[0] == rgb[2])
		{
			if(rgb[0] > rgb[1])
				re0 = 0b0101;
			else
				re0 = 0b0010;
		}
		else if(rgb[1] == rgb[2])
		{
			if(rgb[2] > rgb[0])
				re0 = 0b0110;
			else
				re0 = 0b0001;
		}
		else if(rgb[0] > rgb[1] && rgb[0] > rgb[2])
			re0 = 0b0001;
		else if(rgb[1] > rgb[0] && rgb[1] > rgb[2])
			re0 = 0b0010;
		else if(rgb[2] > rgb[0] && rgb[2] > rgb[1])
			re0 = 0b0100;
		else
			return 0b0111;
		if(light < 0 || bg)
			return re0;
		else
			return re0 | 0b1000;
	}

	private static int clrDiff(int c1, int c2)
	{
		if((c1 == 0x7 && c2 == 0x8) || (c1 == 0x8 && c2 == 0x7))
			return 1;
		return Integer.bitCount(c1 ^ c2);
	}

	public static int fuse(int[] arr, boolean subpixels)
	{
		int code2 = determineDoubles(arr);
		int na = Integer.bitCount(code2);
		int[] arr0 = new int[4 - na];
		int k0 = 0;
		int[] arr1 = new int[na];
		int k1 = 0;
		for(int i = 0; i < 4; i++)
			if(((code2 >>> i) & 0x1) == 0)
			{
				arr0[k0] = arr[i];
				k0++;
			}
			else
			{
				arr1[k1] = arr[i];
				k1++;
			}
		int cl0 = arr0.length == 0 ? 0 : avgclr(false, arr0);
		boolean li0 = flag;
		int cl1 = arr1.length == 0 ? 0 : avgclr(false, arr1);
		boolean li1 = flag;
		if(subpixels || arr0.length == 2)
		{
			int fg;
			int bg;
			if(li1)
			{
				fg = cl1;
				if(li0)
					bg = avgclr(true, arr0);
				else
					bg = toBg(cl0);
			}
			else if(li0)
			{
				code2 ^= 0b1111;
				fg = cl0;
				bg = toBg(cl1);
			}
			else
			{
				fg = cl1;
				bg = toBg(cl0);
			}
			return (bg << 24) | (fg << 16) | fuseChars(code2, subpixels, k1);
		}
		else if(k1 == 3)
		{
			if(li1)
				cl1 = avgclr(true, arr1);
			code2 ^= 0b1111;
			return (cl1 << 24) | (cl0 << 16) | fuseChars(code2, false, k0);
		}
		else
		{
			return (cl1 << 16) | fuseChars(code2, false, k1);
		}
	}

	private static int fuseChars(int code, boolean subpixels, int d1)
	{
		if(subpixels)
			return fuseChars[code];
		return halfChars[d1];
	}

	private static int determineDoubles(int[] arr)
	{
		if(arr[0] == arr[1])
		{
			if(arr[0] == arr[2])
			{
				if(arr[0] == arr[3])
					return 0b1111;
				else
					return 0b1110;
			}
			else
			{
				if(arr[0] == arr[3])
					return 0b1101;
				else if(arr[2] == arr[3])
					return 0b1100;
				else
					return fuseD3(arr, 0b1100);
			}
		}
		else
		{
			if(arr[0] == arr[2])
			{
				if(arr[0] == arr[3])
					return 0b1011;
				else if(arr[1] == arr[3])
					return 0b1010;
				else
					return fuseD3(arr, 0b1010);
			}
			else
			{
				if(arr[0] == arr[3])
				{
					if(arr[1] == arr[2])
						return 0b1001;
					else
						return fuseD3(arr, 0b1001);
				}
				else
				{
					if(arr[1] == arr[2])
					{
						if(arr[1] == arr[3])
							return 0b0111;
						else
							return fuseD3(arr, 0b0110);
					}
					else
					{
						if(arr[1] == arr[3])
							return fuseD3(arr, 0b0101);
						else if(arr[2] == arr[3])
							return fuseD3(arr, 0b0011);
						else
							return 0b1001; //Too complex
					}
				}
			}
		}
	}

	private static int fuseD3(int[] arr, int code)
	{
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		boolean a = true;
		for(int i = 0; i < 4; i++)
		{
			if(((code >>> (3 - i)) & 0x1) > 0)
				c1 = arr[i];
			else if(a)
			{
				c2 = arr[i];
				a = false;
			}
			else
				c3 = arr[i];
		}
		int d12 = clrDiff(c1, c2);
		int d13 = clrDiff(c1, c3);
		int d23 = clrDiff(c2, c3);
		if(d23 <= d12 && d23 <= d13)
			return code;
		if(d12 <= d13)
		{
			for(int i = 0; i < 4; i++)
				if(((code >>> (3 - i)) & 0x1) == 0)
					return code | (1 << (3 - i));
		}
		else
		{
			for(int i = 0; i < 4; i++)
				if(((code >>> i) & 0x1) == 0)
					return code | (1 << i);
		}
		return code;
	}
}