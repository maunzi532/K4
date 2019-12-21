package effektkarten.effekte.effekt;

import effektkarten.effekte.*;
import effektkarten.effekte.bedingung.*;
import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.wirkung.*;
import java.util.*;

public class DirektTriggerEffekt extends TriggerEffekt
{
	private final DirektWirkung direktWirkung;

	public DirektTriggerEffekt(String text, int num, StartTrigger startTrigger,
			StartTriggerSeite startSeite,
			List<Bedingung> bedingungen, DirektWirkung direktWirkung)
	{
		super(startTrigger.symbol, text, num, startTrigger, startSeite, bedingungen);
		this.direktWirkung = direktWirkung;
	}

	@Override
	public void triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		direktWirkung.triggere(n, ziel, mit);
	}
}