package charakter;

import dungeonmap.*;
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
		mapSprite.y = spielfigur.getY() * MapBild.yc * 2;
		mapSprite.x = spielfigur.getX() * MapBild.xc * 2;
		cursor.cursorSprite.y = cursor.y * MapBild.yc * 2;
		cursor.cursorSprite.x = cursor.x * MapBild.xc * 2;
	}

	public void handleInput(int input)
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
		else if(input == 'f')
		{
			if(spielfigur.bereit())
			{
				KoordinatenNum k = spielfigur.kannForschen();
				if(k != null)
				{
					map.forsche(k, hauptklasse.mapStapel);
					hauptklasse.mapUpdate();
				}
			}
		}
		else if(input == 'e')
		{
			if(spielfigur.bereit())
			{
				aufFeld(map.ort(spielfigur.getY(), spielfigur.getX()),
						map.verwendet(spielfigur.getY(), spielfigur.getX()));
			}
		}
	}

	public void aufFeld(MapTeil typ, boolean verwendet)
	{
		switch(typ)
		{
			case GEGNER ->
			{

			}
			case TRANK ->
			{
				if(!verwendet)
				{

				}
			}
			case WAFFENKISTE ->
			{
				if(!verwendet)
				{

				}
			}
			case HAENDLER ->
			{
				//setze verwendet
			}
			case WAND ->
			{

			}
			case MITTELBOSSGEGNER ->
			{

			}
			case BOSSGEGNER ->
			{

			}
			case START ->
			{

			}
			case ZIEL ->
			{

			}
		}
	}
}