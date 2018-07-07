package plane;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class PlaneRenderer
{
	public int height;
	public int width;
	public int bgCode;
	public PlaneFrame standardFrame;
	private int cHeight;
	private int cWidth;
	private List<Color> colors;
	private BufferedImage image;
	private Graphics2D gd;

	public PlaneRenderer(int height, int width, int bg)
	{
		this.height = height;
		this.width = width;
		bgCode = (bg & 0xf) << 24;
		standardFrame = new PlaneFrame(0, 0, height, width);
	}

	public int[][] renderPlanes(boolean subpixels, List<Plane> planes, List<PlaneFrame> frames)
	{
		int[][] chars2 = new int[height][width];
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
		return chars2;
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

	public void argh(int cHeight, int cWidth, int[] colors)
	{
		this.cHeight = cHeight;
		this.cWidth = cWidth;
		this.colors = new ArrayList<>();
		this.colors.addAll(Arrays.stream(colors).mapToObj(Color::new).collect(Collectors.toList()));
		this.colors.add(new Color(0, 0, 0, 0));
		image = new BufferedImage(width * cWidth, height * cHeight, BufferedImage.TYPE_INT_ARGB);
		gd = image.createGraphics();
		gd.setFont(new Font("Monospace", Font.PLAIN, cHeight * 2 / 3));
	}

	public BufferedImage renderImage(boolean subpixels, List<Plane> planes, List<PlaneFrame> frames)
	{
		gd.setColor(colors.get(bgCode >> 24));
		gd.fillRect(0, 0, width * cWidth, height * cHeight);
		for(int n = planes.size() - 1; n >= 0; n--)
		{
			Plane plane = planes.get(n);
			PlaneFrame frame = frames != null ? frames.get(n) : null;
			if(frame == null)
				frame = standardFrame;
			gd.setClip(frame.startX * cWidth, frame.startY * cHeight,
					(frame.endX - frame.startX) * cWidth, (frame.endY - frame.startY) * cHeight);
			if(Math.max(frame.startY, plane.getYShift()) < Math.min(frame.endY, plane.getYShift() + plane.getYSize())
				&& Math.max(frame.startX, plane.getXShift()) < Math.min(frame.endX, plane.getXShift() + plane.getXSize()))
			{
				plane.draw(gd, cHeight, cWidth, colors, subpixels);
			}
		}
		gd.setClip(null);
		return image;
	}
}