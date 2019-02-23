package charakter;

import java.util.*;
import karten.*;
import kartenset.*;
import plane.*;
import sprites.*;

public class Haendler implements WUGU
{
	private SpriteList spriteList;
	private Kartenstapel<Waffenkarte> waffenStapel;
	private TeamItems teamItems;
	private List<Waffenkarte> angebot;
	private KarteSpriteList unten;
	private KarteSpriteList oben;

	public Haendler(PlaneFrame planeFrame, Kartenstapel<Waffenkarte> waffenStapel,
			List<HeldSpieler> teilnehmer, TeamItems teamItems)
	{
		this.waffenStapel = waffenStapel;
		this.teamItems = teamItems;
		spriteList = new SpriteList(planeFrame, 2);
		spriteList.centerMid(planeFrame);

	}
}