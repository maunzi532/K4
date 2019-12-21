package effektkarten.effekte.eigenschaften;

public enum StartTriggerSeite
{
	EIGENE(true, false),
	GEGNER(false, true),
	BEIDE(true, true);

	StartTriggerSeite(boolean eigeneOK, boolean gegnerOK)
	{
		this.eigeneOK = eigeneOK;
		this.gegnerOK = gegnerOK;
	}

	public boolean eigeneOK;
	public boolean gegnerOK;
}