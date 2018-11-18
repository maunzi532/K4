package effekt;

public enum TriggerSeite
{
	EIGENE(true, false),
	GEGNER(false, true),
	BEIDE(true, true);

	TriggerSeite(boolean eigeneOK, boolean gegnerOK)
	{
		this.eigeneOK = eigeneOK;
		this.gegnerOK = gegnerOK;
	}

	public boolean eigeneOK;
	public boolean gegnerOK;
}