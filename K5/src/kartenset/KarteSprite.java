package kartenset;

import plane.*;
import sprites.*;

public class KarteSprite extends TSprite
{
	private KarteBild2 kb;
	private Karte karte;

	public KarteSprite(int z, KarteBild2 kb, Karte karte)
	{
		super(kb.hoehe(), kb.breite(), z, new TextPlane(0x7, 0x0, kb.karteBildA(karte)));
		this.kb = kb;
		this.karte = karte;
	}

	public KarteSprite(int y, int x, int z, KarteBild2 kb, Karte karte)
	{
		super(y, x, kb.hoehe(), kb.breite(), z, new TextPlane(0x7, 0x0, kb.karteBildA(karte)));
		this.kb = kb;
		this.karte = karte;
	}
}