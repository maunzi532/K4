package charakter;

import dungeonmap.*;
import sprites.*;

public class HeldSpieler
{
	public final int spielerNummer;
	public SpriteList spriteList;
	public Spielfigur spielfigur;
	public HeldMap heldMap;

	public HeldSpieler(HeldMap heldMap, DungeonMap dungeonMap)
	{
		this.heldMap = heldMap;
		spielerNummer = heldMap.spielerNummer;
		spielfigur = dungeonMap.erstelleSpielfigur(spielerNummer);
	}
}