package k5.effekt;

public enum StartTrigger
{
	ANFANG("▰ "),
	VERWENDET("▰ "),
	GES_VOR("▲ "),
	GES_NACH("▲ "),
	EINMAL_VOR("│▶ "),
	EINMAL_NACH("▶ "),
	IMMER_VOR("│✱ "),
	IMMER_NACH("✱ "),
	ZUGENDE("");

	public String symbol;

	StartTrigger(String symbol)
	{
		this.symbol = symbol;
	}
}