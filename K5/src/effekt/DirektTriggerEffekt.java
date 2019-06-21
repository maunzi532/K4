package effekt;

import effekt.bedingung.*;
import effekt.direktwirkung.*;
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
	public void triggere(NTI n, NTI ziel, W mit)
	{
		direktWirkung.triggere(n, ziel, mit);
	}
}