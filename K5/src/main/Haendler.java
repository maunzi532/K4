package main;

import java.util.stream.*;
import karteAnsicht.*;
import kartenset.*;
import logik.*;
import java.util.*;
import karten.*;
import plane.*;
import sprites.*;

public class Haendler implements XFenster
{
	private Hauptklasse hk;
	private SpriteList spriteList;
	private TeamItems teamItems;
	private List<Waffenkarte> angebot;
	private KarteSpriteList unten;
	private KarteSpriteList oben;

	public Haendler(Hauptklasse hk, PlaneFrame planeFrame,
			List<HeldSpieler> teilnehmer)
	{
		this.hk = hk;
		teamItems = hk.teamItems;
		spriteList = new SpriteList(planeFrame, 2);
		spriteList.centerMid(planeFrame);
		unten = new KarteSpriteList(spriteList.planeFrame, teamItems.waffenVorrat.stream()
				.map(e -> e.karte).collect(Collectors.toList()), new KarteBild3());
		spriteList.addSpriteList(unten.spriteList);
	}

	@Override
	public void handleInput(int input)
	{

	}

	@Override
	public SpriteList getSpriteList()
	{
		return spriteList;
	}

	@Override
	public void update()
	{

	}
}