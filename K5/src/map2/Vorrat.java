package map2;

import dungeonmap.*;
import java.util.*;
import logik.*;
import main.*;

public class Vorrat
{
	public Einstellungen e;
	public DungeonMap map;
	public List<Spielfigur> spielfiguren;
	public List<Held2> helden;
	public List<WaffeMap> waffen;
	public Map<ExpTrank, Integer> traenke;
	public int unaufgeteilteExp;
	public int bossgegnerBesiegt;
	public int waendeBeworfen;
	public int anzahlHaendler;

	public Vorrat(Einstellungen e)
	{
		this.e = e;
		map = new DungeonMap(e);
		spielfiguren = new ArrayList<>();
		helden = new ArrayList<>();
		waffen = new ArrayList<>();
		traenke = new HashMap<>();
		unaufgeteilteExp = 0;
		bossgegnerBesiegt = 0;
		waendeBeworfen = 0;
		anzahlHaendler = 0;
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			spielfiguren.add(map.erstelleSpielfigur());
		}
	}

	public void erstelleHeld(Held2 h1)
	{
		helden.add(h1);
		if(h1.hauptwaffe != null)
			waffen.add(h1.hauptwaffe);
		if(h1.nebenwaffe != null)
			waffen.add(h1.nebenwaffe);
		erhalteTraenke(e.traenkeProSpieler, e.trankExp.get(0));
	}

	public void erhalteTraenke(int anzahl, int exp)
	{
		ExpTrank expTrank = new ExpTrank(exp);
		if(traenke.containsKey(expTrank))
		{
			traenke.put(expTrank, traenke.get(expTrank) + anzahl);
		}
		else
		{
			traenke.put(expTrank, anzahl);
		}
	}
}