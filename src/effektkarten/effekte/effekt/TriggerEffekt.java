package effektkarten.effekte.effekt;

import effektkarten.ansichtkarte.*;
import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

public abstract class TriggerEffekt extends KartenEffekt
{
	public final StartTrigger startTrigger;
	public final StartTriggerSeite startSeite;
	public final List<Bedingung> bedingungen;

	protected TriggerEffekt(String typ, String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite, List<Bedingung> bedingungen)
	{
		super(typ, text, num);
		this.startTrigger = startTrigger;
		this.startSeite = startSeite;
		this.bedingungen = bedingungen;
	}

	public abstract void triggere(EffektZielCharakter n, EffektZielCharakter ziel);
}