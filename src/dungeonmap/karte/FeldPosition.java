package dungeonmap.karte;

public interface FeldPosition extends MapKartenPosition
{
	int ym = 2;
	int xm = 2;

	int yf();

	int xf();

	int yfInnen();

	int xfInnen();
}