package effekt;

public enum StartTrigger2
{
	ANFANG(""),
	VERWENDET(""),
	GES_VOR("A"),
	GES_NACH("A"),
	EINMAL_VOR("|>"),
	EINMAL_NACH(">"),
	IMMER_VOR("|*"),
	IMMER_NACH("*");

	public String symbol;

	StartTrigger2(String symbol)
	{
		this.symbol = symbol;
	}
}