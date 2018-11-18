package effekt;

import effekt.bedingung.*;
import java.util.*;
import kampf.*;
import karten.*;

public abstract class TriggerEffekt extends Effekt
{
	public final StartTrigger2 startTrigger;
	public final TriggerSeite startSeite;
	public final List<Bedingung> bedingungen;

	public TriggerEffekt(String typ, String text, int num, StartTrigger2 startTrigger,
			TriggerSeite startSeite, List<Bedingung> bedingungen)
	{
		super(typ, text, num);
		this.startTrigger = startTrigger;
		this.startSeite = startSeite;
		this.bedingungen = bedingungen;
	}

	public StartTrigger2 getStartTrigger()
	{
		return startTrigger;
	}

	public TriggerSeite getStartSeite()
	{
		return startSeite;
	}

	public List<Bedingung> getBedingungen()
	{
		return bedingungen;
	}

	public abstract void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit);
}