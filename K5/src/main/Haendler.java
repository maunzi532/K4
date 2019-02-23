package main;

import karteAnsicht.*;
import logik.*;
import java.util.*;
import karten.*;
import plane.*;
import sprites.*;

public class Haendler implements WUGU
{
	private Hauptklasse hk;
	private SpriteList spriteList;
	private TeamItems teamItems;
	private List<Waffenkarte> angebot;
	private KarteSpriteList unten;
	private KarteSpriteList oben;

	public Haendler(Hauptklasse hk, PlaneFrame planeFrame,
			List<HeldSpieler> teilnehmer, TeamItems teamItems)
	{
		this.hk = hk;
		this.teamItems = teamItems;
		spriteList = new SpriteList(planeFrame, 2);
		spriteList.centerMid(planeFrame);

	}
}