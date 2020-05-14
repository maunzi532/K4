package effektkarten.effekte.effekt;

public enum StartTriggerSeite
{
	EIGENE(true, false),
	GEGNER(false, true),
	BEIDE(true, true);

	public final boolean eigeneOK;
	public final boolean gegnerOK;

	StartTriggerSeite(boolean eigeneOK, boolean gegnerOK)
	{
		this.eigeneOK = eigeneOK;
		this.gegnerOK = gegnerOK;
	}
}