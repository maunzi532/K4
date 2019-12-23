package dungeonmap.karte;

import java.util.*;

public enum MapTeil
{
	NICHTS(false, '\u2588', '\u2588', Begehbar.NEIN, Begehbar.NEIN),
	WEG(false, ' ', ' ', Begehbar.GEHT, Begehbar.GEHT),
	GEGNER(true, 'G', 'g', Begehbar.NURBETRETEN, Begehbar.GEHT, "Gegner", "Gegner", "Kampf", "Kampf"),
	TRANK(true, 'T', 't', Begehbar.GEHT, Begehbar.GEHT, "Trank", null, "Trinken", null),
	WAFFENKISTE(true, 'W', 'w', Begehbar.GEHT, Begehbar.GEHT, "Waffenkiste", null, "Öffnen", null),
	HAENDLER(true, 'h', 'H', Begehbar.GEHT, Begehbar.GEHT, null, "Händler", null, "Handeln"),
	WAND(true, '|', '-', Begehbar.NURBETRETEN, Begehbar.GEHT, "Wand", null, "Aufbrechen", null),
	MITTELBOSSGEGNER(true, 'B', 'b', Begehbar.NURBETRETEN, Begehbar.GEHT, "Mittelbossgegner", null, "Kampf", null),
	BOSSGEGNER(true, 'B', 'b', Begehbar.NURBETRETEN, Begehbar.NURBETRETEN, "Bossgegner", null, "Kampf", null),
	START(false, 'S', 'S', Begehbar.GEHT, Begehbar.GEHT),
	ZIEL(false, 'Z', 'Z', Begehbar.GEHT, Begehbar.GEHT);

	MapTeil(boolean hatModifier, char zeichen0, char zeichen1, Begehbar begehbar0,
			Begehbar begehbar1)
	{
		this.hatModifier = hatModifier;
		this.zeichen0 = zeichen0;
		this.zeichen1 = zeichen1;
		this.begehbar0 = begehbar0;
		this.begehbar1 = begehbar1;
		titel0 = null;
		titel1 = null;
		ok0 = null;
		ok1 = null;
	}

	MapTeil(boolean hatModifier, char zeichen0, char zeichen1, Begehbar begehbar0, Begehbar begehbar1,
			String titel0, String titel1, String ok0, String ok1)
	{
		this.hatModifier = hatModifier;
		this.zeichen0 = zeichen0;
		this.zeichen1 = zeichen1;
		this.begehbar0 = begehbar0;
		this.begehbar1 = begehbar1;
		this.titel0 = titel0;
		this.titel1 = titel1;
		this.ok0 = ok0;
		this.ok1 = ok1;
	}

	public final boolean hatModifier;
	public final char zeichen0;
	public final char zeichen1;
	public final Begehbar begehbar0;
	public final Begehbar begehbar1;
	public final String titel0;
	public final String titel1;
	public final String ok0;
	public final String ok1;

	public static Map<Character, MapTeil> charZuMapTeil = Map.ofEntries(Map.entry('X', NICHTS),
			Map.entry(' ', WEG), Map.entry('G', GEGNER), Map.entry('T', TRANK),
			Map.entry('H', HAENDLER), Map.entry('W', WAFFENKISTE), Map.entry('|', WAND),
			Map.entry('S', START), Map.entry('Z', ZIEL),
			Map.entry('M', MITTELBOSSGEGNER), Map.entry('B', BOSSGEGNER));
}