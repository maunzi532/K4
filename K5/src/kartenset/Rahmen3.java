package kartenset;

public class Rahmen3
{
	public final char[][] c;
	public final char lo;
	public final char mo;
	public final char ro;
	public final char lm;
	public final char li;
	public final char rm;
	public final char ri;
	public final char lu;
	public final char mu;
	public final char ru;
	public final char inn;
	public final char ino;
	public final char inm;
	public final char inu;
	public final char bg;

	public Rahmen3()
	{
		lo = '╔';
		mo = '═';
		ro = '╗';
		lm = '║';
		li = '╟';
		rm = '║';
		ri = '╢';
		lu = '╚';
		mu = '═';
		ru = '╝';
		inn = '─';
		ino = '┬';
		inm = '│';
		inu = '┴';
		bg = ' ';
		c = new char[][]
				{
						{'╔', '═', '╗', '┬'},
						{'║', ' ', '║', '│'},
						{'╚', '═', '╝', '┴'},
						{'╟', '─', '╢', ' '}
				};
	}
}