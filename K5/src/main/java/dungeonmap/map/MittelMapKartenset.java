package dungeonmap.map;

import dungeonmap.karte.*;
import java.util.*;

public class MittelMapKartenset
{
	private final List<MapKarte> karten;

	public MittelMapKartenset(List<MapKarte> karten)
	{
		this.karten = karten;
	}

	public MapKarte start()
	{
		return karten.get(0);
	}

	public MapKarte ziel()
	{
		return karten.get(1);
	}

	public List<MapKarte> hauptWeg(int anz)
	{
		List<MapKarte> re = new ArrayList<>(karten.subList(2, anz / 3 * 3 + 2));
		switch(anz % 3)
		{
			case 1 -> re.add(karten.get(anz + 1));
			case 2 ->
			{
				re.add(karten.get(anz + 1));
				re.add(karten.get(anz + 2));
			}
		}
		return re;
	}
}