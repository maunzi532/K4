package plane;

import java.util.*;

public class PlaneRenderer
{
	public int height;
	public int width;
	public int bgCode;
	public int[][] chars2;
	public PlaneFrame standardFrame;

	public PlaneRenderer(int height, int width, int bg)
	{
		this.height = height;
		this.width = width;
		bgCode = (bg & 0xf) << 24;
		standardFrame = new PlaneFrame(0, 0, height, width);
	}

	public void renderPlanes(boolean subpixels, List<Plane> planes, List<PlaneFrame> frames)
	{
		/*for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
				chars2[iy][ix] = 0;*/
		chars2 = new int[height][width];
		for(int n = 0; n < planes.size(); n++)
		{
			Plane plane = planes.get(n);
			PlaneFrame frame = frames != null ? frames.get(n) : null;
			if(frame == null)
				frame = standardFrame;
			for(int iy = Math.max(frame.startY, plane.getYShift()); iy < Math.min(frame.endY, plane.getYShift() + plane.getYSize()); iy++)
				for(int ix = Math.max(frame.startX, plane.getXShift()); ix < Math.min(frame.endX, plane.getXShift() + plane.getXSize()); ix++)
					chars2[iy][ix] = mergeData(chars2[iy][ix], plane.codec(iy, ix));
		}
		for(int iy = 0; iy < height; iy++)
			for(int ix = 0; ix < width; ix++)
			{
				chars2[iy][ix] = mergeData(chars2[iy][ix], bgCode);
				chars2[iy][ix] = prepare(chars2[iy][ix], subpixels);
			}
	}

	public static int mergeData(int prev, int add)
	{
		//Culling
		if((prev & 0x00800000) > 0)
			return prev;
		//Text already exists
		if((prev & 0x00400000) > 0)
		{
			if(detectUseless1(add))
				return prev;
			prev |= 0x00800000;
			if((add & 0x40000000) > 0)
			{
				//Nerf to bg
				return prev | (ClrFuse.avgclr(true, ClrFuse.extractclrs(add)) << 24);
			}
			else
				//Add bgColor
				return prev | (add & 0x0f000000);
		}
		//Subpixels already exist
		if((prev & 0x40000000) > 0)
		{
			if(detectUseless1(add))
				return prev;
			if((add & 0x40000000) > 0)
			{
				//Add missing subpixels
				if((prev & 0x10000000) > 0)
					prev |= add & 0x1f000000;
				if((prev & 0x00100000) > 0)
					prev |= add & 0x001f0000;
				if((prev & 0x00001000) > 0)
					prev |= add & 0x00001f00;
				if((prev & 0x00000010) > 0)
					prev |= add & 0x0000001f;
				if((prev & 0x10101010) == 0)
					return prev | 0x00800000;
				return prev;
			}
			else
			{
				//Override leftover subpixels
				if((add & 0x10000000) == 0)
				{
					int add1 = (add & 0x0f000000) >>> 24;
					if((prev & 0x10000000) > 0)
						prev |= add1 << 24;
					if((prev & 0x00100000) > 0)
						prev |= add1 << 16;
					if((prev & 0x00001000) > 0)
						prev |= add1 << 8;
					if((prev & 0x00000010) > 0)
						prev |= add1;
					prev &= 0xefefefef;
					return prev | 0x00800000;
				}
				return prev;
			}
		}
		else
		{
			//Subpixels
			if((add & 0x40000000) > 0)
			{
				if((add & 0x10101010) == 0x10101010)
					return prev;
				if((add & 0x10101010) == 0)
					return add | 0x00800000;
				return add;
			}
			//Text
			else if((add & 0x0000ffff) > 0)
			{
				if((add & 0x10000000) == 0)
					return add | 0x00c00000;
				return add | 0x00400000;
			}
			//Normal pixel
			else
			{
				if((add & 0x10000000) > 0)
					return prev;
				return add | 0x00800000;
			}
		}
	}

	public static boolean detectUseless1(int add)
	{
		return ((add & 0x50101010) == 0x50101010) ||
				(((add & 0x40000000) == 0) && (add & 0x10000000) > 0);
	}

	public static int prepare(int prev, boolean subpixels)
	{
		if((prev & 0x00400000) > 0)
		{
			//Nerf bg
			if(((prev >>> 24) ^ 0x08) == 0)
				return (prev & 0x070fffff) | 0x07000000;
			return prev & 0x070fffff;
		}
		if((prev & 0x40000000) > 0)
		{
			//Nerf to fg and bg
			return ClrFuse.fuse(ClrFuse.extractclrs(prev), subpixels);
		}
		//bg to fg
		return ((prev & 0x0f000000) >>> 8) | 0x2588;
	}
}