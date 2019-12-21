package mapsets;

import dungeonmap.*;
import java.util.*;
import stapelkarten.*;

public class SetV2MittelMapKarten extends AKartenset<MapKarte> implements MittelMapKartenset
{
	public SetV2MittelMapKarten()
	{
		super();
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n     \nXG HX\nXXSXX"));
		neueKarte(new MapKarte("N\nXXZXX\nX   X\nXBBBX\nX   X\nXX XX\n00000\n00000\n01110\n00000\n00000"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  M  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\nXXM  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  MXX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\nXXMXX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\nXXM  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  MXX\nXX XX\nXX XX"));
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