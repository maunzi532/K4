package dungeonmap;

public enum MapTeil
{
	NICHTS(0, 'X', 'X'),
	WEG(1, ' ', ' '),
	GEGNER(2, 'G', 'g'),
	TRANK(1, 'T', 't'),
	WAFFENKISTE(1, 'W', 'w'),
	HAENDLER(1, 'h', 'H'),
	WAND(2, '|', '-'),
	MITTELBOSSGEGNER(2, 'B', 'b'),
	BOSSGEGNER(2, 'B', 'b'),
	START(1, 'S', 'S'),
	ZIEL(1, 'Z', 'Z');

	MapTeil(int begehbar, char zeichen0, char zeichen1)
	{
		this.begehbar = begehbar;
		this.zeichen0 = zeichen0;
		this.zeichen1 = zeichen1;
	}

	public int begehbar;
	public char zeichen0;
	public char zeichen1;
}