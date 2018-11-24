package sets;

import effekt.*;
import effekt.bedingung.*;
import effekt.direktwirkung.*;
import effekt.wirkung.*;
import karten.*;
import kartenset.*;

public class SetV2Waffen extends Kartenset<Waffenkarte>
{
	public SetV2Waffen()
	{
		super();
		neueKarte(new Waffenkarte("Stange", 5, 4, 1, Karte.alleKlassen));
		neueKarte(new Waffenkarte("Großer Stock", 6, 4, 3, 0b11011011));
		neueKarte(new Waffenkarte("Eisenschwert", 6, 3, 5, 0b10001000));
		neueKarte(new Waffenkarte("Spiegelsplitter", 8, 1, 9, 0b00001100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET)
						.setBedingungen(new BHauptwaffe()).setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(-2))
						.setEndTrigger(EndTrigger.VERWENDET).createZTE(),
				new ZTEBuilder(NKartentyp.WAFFE).setNum(1).setStartTrigger(StartTrigger.IMMER_NACH)
						.setBedingungen(new BHauptwaffe()).setWirkung(StatWirkung.verteidigung(-1))
						.setBetrifftGegner(true).setAn(NKartentyp.CHARAKTER).setDauer(3).createZTE()));
		neueKarte(new Waffenkarte("Ast", 9, 4, 1, 0b00000001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.extraangriffe(1)).createZTE()));
		neueKarte(new Waffenkarte("Fortges. Waffe"/*"Fortgeschrittene Waffe"*/, 12, 7, 5, Karte.alleKlassen));
		neueKarte(new Waffenkarte("Fortg. Ausl. stab"/*"Fortgeschrittener Auslassstab"*/, 13, 4, 6, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_VOR).setBedingungen(new BMagieAusgeben())
						.setWirkung(new StatWirkung(2, 2, 2, 0, 2))
						.createZTE()));
		neueKarte(new Waffenkarte("Giftbogen", 12, 0, 8, 0b01000001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.IMMER_NACH)
						.setBetrifftGegner(true).setAn(NKartentyp.CHARAKTER).setDauer(2)
						.setWirkung(new StatWirkung(0, -2, -2))
						.createZTE()));
		neueKarte(new Waffenkarte("Fortg. Mag. Stab" /*"Fortgeschrittener Magischer Stab"*/,
				15, 6, 6, Karte.alleKlassen,
				new DTEBuilder().setStartTrigger(StartTrigger.ANFANG)
						.setDirektWirkung(new MagieWirkung(2, false)).createDTE(),
				new ZTEBuilder(NKartentyp.WAFFE).setNum(1).setStartTrigger(StartTrigger.VERWENDET)
						.setWirkung(StatWirkung.magie(1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		/*neueKarte(new Waffenkarte("Abenteuerschwert", 7, 4, 5, 0b10000000));
		neueKarte(new Waffenkarte("?Bogen", 10, 2, 6, 0b01000000));
		neueKarte(new Waffenkarte("Geladener Stab", 8, 2, 5, 0b00100000));
		neueKarte(new Waffenkarte("Schutzflügel", 7, 2, 4, 0b00010000));
		neueKarte(new Waffenkarte("Politurfaust", 7, 4, 4, 0b00001000));
		neueKarte(new Waffenkarte("Spuk", 8, 1, 6, 0b00000100));
		neueKarte(new Waffenkarte("?Wasser", 5, 2, 3, 0b00000010));
		neueKarte(new Waffenkarte("?Beschwörung", 8, 2, 4, 0b00000001));*/
	}
}