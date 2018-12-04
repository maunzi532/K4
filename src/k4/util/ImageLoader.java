package k4.util;

import java.awt.image.*;
import java.util.*;

public class ImageLoader
{
	public static final int C = 0;
	public static final int T = 1;
	public static final int E = 2;
	public static final int A = 3;

	private List images;

	public ImageLoader(String baseLocation, String... extra)
	{
		images = new ArrayList();
		images.add(baseLocation + "C.png");
		images.add(baseLocation + "T.png");
		images.add(baseLocation + "E.png");
		Arrays.stream(extra).map(e -> e + ".png").forEach(images::add);
	}

	public BufferedImage getImage(DrawSetting n)
	{
		Object object = images.get(n.ordinal());
		if(object instanceof BufferedImage)
			return (BufferedImage) object;
		if(object instanceof String)
		{
			BufferedImage image = Lader7.imageResource((String) object);
			images.set(n.ordinal(), image);
			return image;
		}
		throw new RuntimeException();
	}
}