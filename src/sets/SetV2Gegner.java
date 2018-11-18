package sets;

import effekt.*;
import effekt.wirkung.*;
import karten.*;
import kartenset.*;

public class SetV2Gegner extends Kartenset<Charakterkarte>
{
	public SetV2Gegner()
	{
		super();
		neueKarte(new Charakterkarte("Gegner", 8, 7, 6, 6, 10));
		neueKarte(new Charakterkarte("Blocko", 7, 7, 3, 8, 12,
				new ZTEBuilder(EffektAn.CHARAKTER).setStartTrigger(StartTrigger2.IMMER_NACH).setStartSeite(TriggerSeite.GEGNER)
						.setWirkung(StatWirkung.verteidigung(3)).generateText().createZTE()));
		neueKarte(new Charakterkarte("Schleimo", 6, 7, 7, 7, 15));
	}
}