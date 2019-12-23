package mapsets;

import dungeonmap.karte.*;

public class SetV2MapKarten extends KartenlisteBuilder<MapKarte>
{
	public SetV2MapKarten()
	{
		//2 Oben/Unten
		neueKarte(new MapKarte("N\nXX XX\nXXTXX\nXX|XX\nXXWXX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXXWXX\nXX|XX\nXXTXX\nXX XX"));
		//4 L/R
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n     \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n  T  \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n  H  \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n  G  \nXXXXX\nXXXXX"));
		//3 U/L
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n   XX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n  TXX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\n  GXX\nXX XX\nXX XX"));
		//3 U/R
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\nXX   \nXX XX\nXX XX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\nXXT  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("\nXXXXX\nXXXXX\nXXG  \nXX XX\nXX XX"));
		//2 L-R
		neueKarte(new MapKarte("\nXXXXX\nX   X\n  T X\nX   X\nXXXXX"));
		neueKarte(new MapKarte("\nXXXXX\nX   X\nX W  \nX   X\nXXXXX"));
		//2 U-O
		neueKarte(new MapKarte("\nXXXXX\nX   X\nX T X\nX   X\nXX XX"));
		neueKarte(new MapKarte("\nXX XX\nX   X\nX W X\nX   X\nXXXXX"));
		//2 Oben/Rechts/Unten
		neueKarte(new MapKarte("N\nXXTXX\nXX|XX\nXX   \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXXWXX\nXX|XX\nXX   \nXX XX\nXX XX"));
		//2 Oben/Links/Unten
		neueKarte(new MapKarte("N\nXXTXX\nXX|XX\n   XX\nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n   XX\nXX|XX\nXXWXX"));
		//7 LR/Unten
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n     \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n  G  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n G   \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n   G \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n     \nXXGXX\nXX XX"));
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n  H  \nXX XX\nXX XX"));
		neueKarte(new MapKarte("N\nXXXXX\nXXXXX\n     \nXX|XX\nXXTXX"));
		//7 Oben/LR
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n     \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  G  \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n G   \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n   G \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("N\nXX XX\nXXGXX\n     \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n  H  \nXXXXX\nXXXXX"));
		neueKarte(new MapKarte("N\nXXTXX\nXX|XX\n     \nXXXXX\nXXXXX"));
		//6 Alle Seiten
		neueKarte(new MapKarte("\nXX XX\nX  XX\n  |  \nXX  X\nXX XX"));
		neueKarte(new MapKarte("\nXX XX\nXX  X\n  |  \nX  XX\nXX XX"));
		neueKarte(new MapKarte("\nXX XX\nXG |X\n  X  \nX| GX\nXX XX\n00000\n01030\n00000\n03020\n00000"));
		neueKarte(new MapKarte("\nXX XX\nX| GX\n  X  \nXG |X\nXX XX\n00000\n03010\n00000\n02030\n00000"));
		neueKarte(new MapKarte("N\nXX XX\nXX XX\n     \nXX|XX\nXXTXX"));
		neueKarte(new MapKarte("N\nXXWXX\nXX|XX\n     \nXX XX\nXX XX"));
	}
}