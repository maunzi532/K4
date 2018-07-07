package util;

import java.awt.image.*;
import java.util.*;

public class ImageLoader
{
	private String baseLocation;
	private List<String> extra;
	private Map<String, BufferedImage> images;

	public ImageLoader(String baseLocation, String... extra)
	{
		this.baseLocation = baseLocation;
		this.extra = Arrays.asList(extra);
		images = new HashMap<>();
	}

	private BufferedImage loadGet(String code)
	{
		if(images.containsKey(code))
			return images.get(code);
		BufferedImage image = Lader7.imageResource(code);
		images.put(code, image);
		return image;
	}

	public BufferedImage bySuffix(String suffix)
	{
		return loadGet(baseLocation + suffix + ".png");
	}

	public BufferedImage byExtra(int num)
	{
		return loadGet(extra.get(num) + ".png");
	}
}