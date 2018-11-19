package sets;

import effekt.*;
import effekt.bedingung.*;
import effekt.direktwirkung.*;
import effekt.wirkung.*;
import karten.*;
import kartenset.*;

public class SetV2Aktionen extends Kartenset<Aktionskarte>
{
	public SetV2Aktionen()
	{
		super();
		neueKarte(new Aktionskarte("Standardangriff", 2, 0, 0, false));
		neueKarte(new Aktionskarte("Hauptwaffe", 3, 0, 0, true));
		neueKarte(new Aktionskarte("Powerangriff", 1, 3, -3, false));
		neueKarte(new Aktionskarte("Schnellangriff", 1, -3, 3, false));
		neueKarte(new Aktionskarte("Zertrümmerer", 1, 4, -4, true));
		neueKarte(new Aktionskarte("Ladeangriff", 4, -3, 0, false));
		neueKarte(new Aktionskarte("Zeitlupe", -4, 0, 6, false));
		neueKarte(new Aktionskarte("Brecher", -1, 2, -3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setDauer(2).setBetrifftGegner(true).setAn(NKartentyp.WAFFE).setZielWaffe(W.HW)
						.setWirkung(StatWirkung.angriff(-3)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Disruptorangriff", -3, -2, 3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true).setAn(
						NKartentyp.CHARAKTER)
						.setWirkung(StatWirkung.angriff(-2)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Auslassschlag", -8, 4, 4, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(4)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Schnellschlag", -3, -2, 3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BMehrAG()).setWirkung(StatWirkung.extraangriffe(1)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Doppelschlag", -1, -2, 3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.setzeangriffe(2)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Magischer Angriff", 0, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.mindestschaden(2)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Risiko", 2, 3, 1, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(-3)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Schutzangriff", 1, 1, -5, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).generateText().createZTE()));
		neueKarte(new Aktionskarte("Magieklatsch", 0, -2, 0, false,
				new DTEBuilder().setStartTrigger(StartTrigger.IMMER_NACH)
						.setDirektWirkung(new MagieWirkung(2, false)).generateText().createDTE()));
	}
}