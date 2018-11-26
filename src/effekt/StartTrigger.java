package effekt;

public enum StartTrigger
{
	ANFANG("K "),
	VERWENDET("V "),
	GES_VOR("A "),
	GES_NACH("A "),
	EINMAL_VOR("|> "),
	EINMAL_NACH("> "),
	IMMER_VOR("|* "),
	IMMER_NACH("* "),
	ZUGENDE("");

	public String symbol;

	StartTrigger(String symbol)
	{
		this.symbol = symbol;
	}
}