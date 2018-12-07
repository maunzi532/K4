package kartenset;

import plane.*;
import sprites.*;

public class KarteSprite extends TSprite
{
	private KarteBild2 kb;
	private Karte karte;

	public KarteSprite(int y, int x, int z, KarteBild2 kb, Karte karte)
	{
		super(y, x, kb.hoehe(), kb.breite(), z, new TextPlane(0xf, 0x0, kb.karteBildA(karte)));
		System.out.println("y = " + y);
		System.out.println("ysp = " + ysp);
		System.out.println("x = " + x);
		System.out.println("xsp = " + xsp);
		System.out.println("xLEdge() = " + xLEdge());
		this.kb = kb;
		this.karte = karte;
	}
}