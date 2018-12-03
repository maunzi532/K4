package charakter;

import dungeonmap.*;
import java.util.*;
import karten.*;
import kartenset.*;

public class M
{
	public Einstellungen e;
	public Kartenstapel<MapKarte> mapStapel;
	public Kartenset<Charakterkarte> klassenSet;
	public Kartenstapel<Charakterkarte> gegnerStapel;
	public Kartenstapel<Waffenkarte> waffenStapel;
	public Kartenstapel<Aktionskarte> aktionenStapel;
	public DungeonMap dungeonMap;
	public List<HeldMap> spieler;

	public M(Einstellungen e, MittelMapKartenset mittelMapSet, AKartenset<MapKarte> mapSet,
			Kartenset<Charakterkarte> klassenSet, Kartenset<Charakterkarte> gegnerSet,
			Kartenset<Waffenkarte> waffenSet, Kartenset<Aktionskarte> aktionenSet)
	{
		this.e = e;
		this.klassenSet = klassenSet;
		mapStapel = new Kartenstapel<>(mapSet.karten);
		gegnerStapel = new Kartenstapel<>(gegnerSet);
		waffenStapel = new Kartenstapel<>(waffenSet);
		aktionenStapel = new Kartenstapel<>(gegnerSet);
		dungeonMap = new DungeonMap(e.yhMap, e.xmMap, e.mittelBossOrte);
		dungeonMap.erstelleMittelWeg(mittelMapSet);
	}
}