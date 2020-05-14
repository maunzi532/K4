package effektkarten.effekte.effekt;

import effektkarten.effekte.bedingung.Bedingung;
import effektkarten.effekte.wirkung.DirektWirkung;
import effektkarten.effekte.ziel.*;
import java.util.*;

public final class DirektTriggerEffekt extends TriggerEffekt
{
	private final DirektWirkung direktWirkung;

	public DirektTriggerEffekt(String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite, List<Bedingung> bedingungen, DirektWirkung direktWirkung)
	{
		super(startTrigger.symbol, text, num, startTrigger, startSeite, bedingungen);
		this.direktWirkung = direktWirkung;
	}

	@Override
	public void triggere(EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		direktWirkung.triggere(sender, ziel);
	}
}