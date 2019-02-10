package charakter;

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
	public DungeonMap map;
	public FeldLobby feldLobby;
	public Hauptklasse hauptklasse;

	public HeldSpieler(HeldMap heldMap, DungeonMap map, Hauptklasse hauptklasse,
			SpriteList spriteList, SpriteList spriteListMap)
	{
		this.heldMap = heldMap;
		this.map = map;
		this.hauptklasse = hauptklasse;
		this.spriteList = spriteList;
		spielerNummer = heldMap.spielerNummer;
		spielfigur = map.erstelleSpielfigur(spielerNummer);
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
				map.forsche(k, hauptklasse.mapStapel);
				hauptklasse.mapUpdate();
			}
			else
			{
				aufFeld(map.ort(spielfigur.getY(), spielfigur.getX()),
						map.verwendet(spielfigur.getY(), spielfigur.getX()));
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
					feldLobby.starten(this, hauptklasse.spieler.stream()
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
			case GEGNER ->
			{
				feldLobbyBeitreten();
			}
			case TRANK ->
			{
				if(!verwendet)
				{
					feldLobbyBeitreten();
				}
			}
			case WAFFENKISTE ->
			{
				if(!verwendet)
				{
					feldLobbyBeitreten();
				}
			}
			case HAENDLER ->
			{
				if(!verwendet)
				{
					//setze verwendet
				}
				//nur jeweils einmal
				feldLobbyBeitreten();
			}
			case WAND ->
			{
				feldLobbyBeitreten();
			}
			case MITTELBOSSGEGNER ->
			{
				feldLobbyBeitreten();
			}
			case BOSSGEGNER ->
			{
				feldLobbyBeitreten();
			}
			case START ->
			{
				System.out.println("W");
			}
			case ZIEL ->
			{
				System.out.println("Ziel");
			}
		}
	}

	public void feldLobbyBeitreten()
	{
		for(HeldSpieler spieler : hauptklasse.spieler)
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
		new FeldLobby(map, spielfigur.getY(), spielfigur.getX()).beitreten(this);
	}
}