package effektkarten.kartebild;

public class Rahmen3
{
	public static final char[][] c1 = new char[][]
			{
					{'┌', '─', '┐', '┬'},
					{'│', ' ', '│', '│'},
					{'└', '─', '┘', '┴'},
					{'├', '─', '┤', ' '}
			};
	public static final char[][] c2 = new char[][]
			{
					{'╔', '═', '╗', '╤'},
					{'║', ' ', '║', '│'},
					{'╚', '═', '╝', '╧'},
					{'╟', '─', '╢', ' '}
			};

	public final char[][] c;

	public Rahmen3(int linien)
	{
		c = switch(linien)
		{
			case 1 -> c1;
			case 2 -> c2;
			default -> throw new RuntimeException("Rahmen existiert nicht (" + linien + " linien)");
		};
	}
}