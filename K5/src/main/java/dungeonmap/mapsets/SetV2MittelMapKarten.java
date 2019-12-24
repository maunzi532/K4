package dungeonmap.mapsets;

import dungeonmap.karte.*;

public class SetV2MittelMapKarten extends KartenlisteBuilder<MapKarte>
{
	public SetV2MittelMapKarten()
	{
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n     \nXG HX\nXXSXX"));
		neueKarte(new MapKarte("N\nXXZXX\nX   X\nXBBBX\nX   X\nXX XX\n00000\n00000\n01110\n00000\n00000"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  M  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\nXXM  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  MXX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\nXXMXX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\nXXM  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  MXX\nXX XX\nXX XX"));
	}
}