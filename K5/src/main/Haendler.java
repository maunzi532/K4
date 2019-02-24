package main;

import karteAnsicht.*;
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