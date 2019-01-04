package charakter;

import dungeonmap.*;
import kartenset.*;
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
		spriteList.visible = true;
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
				feldLobby.entfernen(spriteList);
				feldLobby = null;
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
				erstelleFeldLobby();
			}
			case TRANK ->
			{
				if(!verwendet)
				{
					erstelleFeldLobby();
				}
			}
			case WAFFENKISTE ->
			{
				if(!verwendet)
				{
					erstelleFeldLobby();
				}
			}
			case HAENDLER ->
			{
				//setze verwendet

				//nur jeweils einmal
				erstelleFeldLobby();
			}
			case WAND ->
			{
				erstelleFeldLobby();
			}
			case MITTELBOSSGEGNER ->
			{
				erstelleFeldLobby();
			}
			case BOSSGEGNER ->
			{
				erstelleFeldLobby();
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

	public void erstelleFeldLobby()
	{
		feldLobby = new FeldLobby(map, spielfigur.getY(), spielfigur.getX(), new Rahmen3(), spriteList);
	}
}