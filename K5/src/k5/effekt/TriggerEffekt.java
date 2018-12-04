package k5.effekt;

import k5.effekt.bedingung.*;
import java.util.*;
import k5.kampf.*;
import k5.karten.*;

public abstract class TriggerEffekt extends KartenEffekt
{
	public final StartTrigger startTrigger;
	public final StartTriggerSeite startSeite;
	public final List<Bedingung> bedingungen;

	public TriggerEffekt(String typ, String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite, List<Bedingung> bedingungen)
	{
		super(typ, text, num);
		this.startTrigger = startTrigger;
		this.startSeite = startSeite;
		this.bedingungen = bedingungen;
	}

	public StartTrigger getStartTrigger()
	{
		return startTrigger;
	}

	public StartTriggerSeite getStartSeite()
	{
		return startSeite;
	}

	public List<Bedingung> getBedingungen()
	{
		return bedingungen;
	}

	public abstract void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit);
}