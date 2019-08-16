package map2;

import dungeonmap.*;
import java.util.*;
import logik.*;

public class Vorrat
{
	public DungeonMap map;
	public List<Spielfigur> spielfiguren;
	public List<Held2> helden;
	public List<WaffeMap> waffen;
	public Map<ExpTrank, Integer> traenke;
	public int unaufgeteilteExp;
	public int bossgegnerBesiegt;
	public int waendeBeworfen;
	public int anzahlHaendler;
}