package dungeonmap;

public enum MapTeil
{
	NICHTS(false, '\u2588', '\u2588', Begehbar.NEIN, Begehbar.NEIN),
	WEG(false, ' ', ' ', Begehbar.GEHT, Begehbar.GEHT),
	GEGNER(true, 'G', 'g', Begehbar.NURBETRETEN, Begehbar.GEHT),
	TRANK(true, 'T', 't', Begehbar.GEHT, Begehbar.GEHT),
	WAFFENKISTE(true, 'W', 'w', Begehbar.GEHT, Begehbar.GEHT),
	HAENDLER(true, 'h', 'H', Begehbar.GEHT, Begehbar.GEHT),
	WAND(true, '|', '-', Begehbar.NURBETRETEN, Begehbar.GEHT),
	MITTELBOSSGEGNER(true, 'B', 'b', Begehbar.NURBETRETEN, Begehbar.GEHT),
	BOSSGEGNER(true, 'B', 'b', Begehbar.NURBETRETEN, Begehbar.NURBETRETEN),
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
	}

	public boolean hatModifier;
	public char zeichen0;
	public char zeichen1;
	public Begehbar begehbar0;
	public Begehbar begehbar1;
}