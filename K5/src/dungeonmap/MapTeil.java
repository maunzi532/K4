package dungeonmap;

public enum MapTeil
{
	NICHTS(false, 0, '▉', '▉'),
	WEG(false, 1, ' ', ' '),
	GEGNER(true, 2, 'G', 'g'),
	TRANK(true, 1, 'T', 't'),
	WAFFENKISTE(true, 1, 'W', 'w'),
	HAENDLER(true, 1, 'h', 'H'),
	WAND(true, 2, '|', '-'),
	MITTELBOSSGEGNER(true, 2, 'B', 'b'),
	BOSSGEGNER(true, 2, 'B', 'b'),
	START(false, 1, 'S', 'S'),
	ZIEL(false, 1, 'Z', 'Z');

	MapTeil(boolean hatModifier, int begehbar, char zeichen0, char zeichen1)
	{
		this.hatModifier = hatModifier;
		this.begehbar = begehbar;
		this.zeichen0 = zeichen0;
		this.zeichen1 = zeichen1;
	}

	public boolean hatModifier;
	public int begehbar;
	public char zeichen0;
	public char zeichen1;
}