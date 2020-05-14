package effektkarten.kartebild;

public final class Rahmen3
{
	private static final char[][] c1 = {
			{'┌', '─', '┐', '┬'},
			{'│', ' ', '│', '│'},
			{'└', '─', '┘', '┴'},
			{'├', '─', '┤', ' '}
	};
	private static final char[][] c2 = {
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