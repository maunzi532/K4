package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;

public class AnEffekt
{
	public final Wirkung wirkung;
	public final EndTrigger endTrigger;
	public final int dauer;

	public AnEffekt(Wirkung wirkung, EndTrigger endTrigger, int dauer)
	{
		this.wirkung = wirkung;
		this.endTrigger = endTrigger;
		this.dauer = dauer;
	}
}