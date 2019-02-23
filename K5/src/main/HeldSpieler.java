package main;

import karteAnsicht.*;
import logik.*;
import dungeonmap.*;
import java.util.stream.*;
import plane.*;
import sprites.*;

public class HeldSpieler
{
	public final int spielerNummer;
	public SpriteList spriteList;
	public Spielfigur spielfigur;
	public Sprite mapSprite;
	public HeldMap heldMap;
	public MapCursor cursor;
	public FeldLobby feldLobby;
	public Hauptklasse hk;

	public HeldSpieler(HeldMap heldMap, Hauptklasse hk,
			SpriteList spriteList, SpriteList spriteListMap)
	{
		this.heldMap = heldMap;
		this.hk = hk;
		this.spriteList = spriteList;
		spielerNummer = heldMap.spielerNummer;
		spielfigur = hk.dungeonMap.erstelleSpielfigur(spielerNummer);
		cursor = new MapCursor(spielfigur.getY(), spielfigur.getX());
		spriteListMap.addSprite(cursor.cursorSprite);
		mapSprite = new TSprite(0, 0, 1, new TextPlane(0x6, 0x0, "11111", "11111", "11111"));
		spriteListMap.addSprite(mapSprite);
	}

	public void tick(PlaneRenderer screen)
	{
		spielfigur.bewege();
		if(spielfigur.bewegungFertig())
		{
			KoordinatenNum k = spielfigur.kannForschen();
			if(k != null)
			{
				hk.dungeonMap.forsche(k, hk.mapStapel);
				hk.mapUpdate();
			}
			else
			{
				aufFeld(hk.dungeonMap.ort(spielfigur.getY(), spielfigur.getX()),
						hk.dungeonMap.verwendet(spielfigur.getY(), spielfigur.getX()));
			}
		}
		mapSprite.y = spielfigur.getY() * MapBild.yc * 2;
		mapSprite.x = spielfigur.getX() * MapBild.xc * 2;
		cursor.cursorSprite.y = cursor.y * MapBild.yc * 2;
		cursor.cursorSprite.x = cursor.x * MapBild.xc * 2;
		if(feldLobby != null)
		{
			feldLobby.getSprite().y = screen.height;
			feldLobby.getSprite().x = screen.width;
		}
	}

	public void handleInput(int input)
	{
		if(feldLobby != null)
		{
			if(input == ' ')
			{
				if(feldLobby.auswahl == 1)
				{
					feldLobby.starten(this, hk.spieler.stream()
							.filter(e -> e.feldLobby == feldLobby).collect(Collectors.toList()));
				}
				feldLobby.entfernen(spriteList);
				feldLobby = null;
			}
			else if(input == 'a')
			{
				feldLobby.auswahl = 1;
				feldLobby.updateSprite();
			}
			else if(input == 'd')
			{
				feldLobby.auswahl = 0;
				feldLobby.updateSprite();
			}
		}
		else
		{
			if(input == 'w')
				cursor.y -= 1;
			else if(input == 's')
				cursor.y += 1;
			else if(input == 'a')
				cursor.x -= 1;
			else if(input == 'd')
				cursor.x += 1;
			else if(input == ' ')
				spielfigur.geheZu(cursor.y, cursor.x);
		}
	}

	public void aufFeld(MapTeil typ, boolean verwendet)
	{
		switch(typ)
		{
			case WEG, START -> {}
			case GEGNER -> feldLobbyBeitreten(false);
			case TRANK, WAFFENKISTE, WAND, MITTELBOSSGEGNER, BOSSGEGNER -> feldLobbyBeitreten(verwendet);
			case HAENDLER ->
			{
				if(!verwendet)
				{
					hk.aktiveHaendler++;
					hk.dungeonMap.setVerwendet(spielfigur.getY(), spielfigur.getX());
					hk.mapUpdate();
				}
				if(hk.haendlerAktiv)
				{
					feldLobbyBeitreten(false);
				}
			}
			case ZIEL -> System.out.println("Ziel");
			default -> throw new RuntimeException();
		}
	}

	public void feldLobbyBeitreten(boolean dochNicht)
	{
		if(dochNicht)
			return;
		for(HeldSpieler spieler : hk.spieler)
		{
			if(spieler.feldLobby != null)
			{
				if(spieler.feldLobby.istEnthalten(spielfigur.getY(), spielfigur.getX()))
				{
					spieler.feldLobby.beitreten(this);
					return;
				}
			}
		}
		new FeldLobby(hk, spielfigur.getY(), spielfigur.getX()).beitreten(this);
	}
}