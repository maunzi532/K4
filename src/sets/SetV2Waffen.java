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

		neueKarte(new Waffenkarte("Fortges. Hammer", 12, 11, 2, Karte.alleKlassen));

		neueKarte(new Waffenkarte("Giftbogen", 12, 0, 8, 0b01000001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.IMMER_NACH)
						.setBetrifftGegner(true).setAn(NKartentyp.CHARAKTER).setDauer(2)
						.setWirkung(new StatWirkung(0, -2, -2)).createZTE()));
		neueKarte(new Waffenkarte("Fortg. Ausl. stab"/*"Fortgeschrittener Auslassstab"*/, 13, 4, 6, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_VOR).setBedingungen(new BMagieAusgeben())
						.setWirkung(new StatWirkung(2, 2, 2, 0, 2)).createZTE()));
		neueKarte(new Waffenkarte("Luftwaffe", 13, 4, 8, 0b00010100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH).setBedingungen(new BMehrAG(5))
						.setWirkung(StatWirkung.angriff(4)).createZTE()));
		neueKarte(new Waffenkarte("Kristall der Macht", 14, 9, 4, 0b00100100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH).setBedingungen(new BWenigerBasisAngriff())
						.setWirkung(StatWirkung.angriff(4)).createZTE()));
		neueKarte(new Waffenkarte("Steinschleuder", 11, 3, 7, 0b01010011,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.angriff(-3)).createZTE()));
		neueKarte(new Waffenkarte("Fortges. Waffe"/*"Fortgeschrittene Waffe"*/, 12, 7, 5, Karte.alleKlassen));
		neueKarte(new Waffenkarte("Stange", 5, 4, 1, Karte.alleKlassen));
		neueKarte(new Waffenkarte("Speer", 11, 4, 6, 0b11010001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET).setBedingungen(new BHauptwaffe())
						.setWirkung(StatWirkung.angriff(3)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Politurfaust", 7, 4, 3, 0b00001000,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH).setBedingungen(new BMagieAusgeben())
						.setWirkung(StatWirkung.mindestschutz(1)).createZTE()));
		neueKarte(new Waffenkarte("Schleimkanone", 9, 2, 4, 0b00000110,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.geschwindigkeit(-5)).setDauer(2).createZTE()));
		neueKarte(new Waffenkarte("Ozonsplitter", 12, 0, 12, 0b00011110,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET)
						.setBedingungen(new BHauptwaffe()).setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(-3))
						.setEndTrigger(EndTrigger.VERWENDET).createZTE(),
				new ZTEBuilder(NKartentyp.WAFFE).setNum(1).setStartTrigger(StartTrigger.IMMER_NACH)
						.setBedingungen(new BHauptwaffe()).setWirkung(StatWirkung.mindestschutz(-1))
						.setBetrifftGegner(true).setAn(NKartentyp.CHARAKTER).setDauer(2).createZTE()));
		neueKarte(new Waffenkarte("Etwas kleiner Stock", 9, 2, 7, Karte.alleKlassen));
		neueKarte(new Waffenkarte("Anfängerschwert", 5, 2, 3, Karte.alleKlassen));

		neueKarte(new Waffenkarte("Hammer", 7, 7, 3, 0b11001000));

		neueKarte(new Waffenkarte("Windstab", 7, 3, 4, 0b00110001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_VOR).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.geschwindigkeit(-3)).createZTE()));
		neueKarte(new Waffenkarte("Ast", 9, 4, 1, 0b00000001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.extraangriffe(1)).createZTE()));
		neueKarte(new Waffenkarte("Spuk", 8, 1, 6, 0b00000100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(-3)).setDauer(2).createZTE()));
		neueKarte(new Waffenkarte("Wasserkugel", 5, 2, 2, 0b00000010,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(-2)).setDauer(2).createZTE()));

		neueKarte(new Waffenkarte("Wasserorb", 10, 6, 7, 0b00100011,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET)
						.setWirkung(StatWirkung.magie(-1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Minischwert", 9, 2, 6, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH).setBedingungen(new BWenigerBasisAngriff())
						.setWirkung(StatWirkung.angriff(2)).createZTE()));

		neueKarte(new Waffenkarte("Schild", 8, 2, 4, 0b11111001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET).setBedingungen(new BHauptwaffe())
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(2)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Wasserstein", 8, 2, 3, 0b00010010,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true).setAn(NKartentyp.CHARAKTER)
						.setWirkung(new StatWirkung(0, -2, -2)).setDauer(2).createZTE()));
		neueKarte(new Waffenkarte("Minispeer", 8, 2, 5, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET).setBedingungen(new BHauptwaffe())
						.setWirkung(StatWirkung.angriff(1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Eisenschwert", 6, 3, 5, 0b10001000));

		neueKarte(new Waffenkarte("Magische Kugel", 7, 2, 4, 0b00100110));

		neueKarte(new Waffenkarte("Minischild", 10, 2, 6, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET).setBedingungen(new BHauptwaffe())
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(2)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Großer Stock", 7, 4, 3, 0b11011011));
		neueKarte(new Waffenkarte("Kristall der Stärke", 7, 5, 1, 0b00100100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH).setBedingungen(new BWenigerBasisAngriff())
						.setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Waffenkarte("Stärkungsbogen", 10, 4, 7, 0b01000000,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.IMMER_NACH).setBedingungen(new BHauptwaffe())
						.setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Waffenkarte("Angriffskeule", 5, 1, 2, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET).setBedingungen(new BHauptwaffe())
						.setWirkung(StatWirkung.angriff(3)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Auslassstab", 6, 2, 3, 0b11101001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_VOR).setBedingungen(new BMagieAusgeben())
						.setWirkung(new StatWirkung(2, 2, 0)).createZTE()));
		neueKarte(new Waffenkarte("Spiegelsplitter", 8, 1, 9, 0b00001100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET)
						.setBedingungen(new BHauptwaffe()).setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(-2))
						.setEndTrigger(EndTrigger.VERWENDET).createZTE(),
				new ZTEBuilder(NKartentyp.WAFFE).setNum(1).setStartTrigger(StartTrigger.IMMER_NACH)
						.setBedingungen(new BHauptwaffe()).setWirkung(StatWirkung.verteidigung(-1))
						.setBetrifftGegner(true).setAn(NKartentyp.CHARAKTER).setDauer(3).createZTE()));
		neueKarte(new Waffenkarte("Pflanzenranke", 6, 2, 3, 0b00010001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.geschwindigkeit(-3)).setDauer(2).createZTE()));
		neueKarte(new Waffenkarte("Geladener Stab", 8, 2, 5, 0b00100000,
				new DTEBuilder().setStartTrigger(StartTrigger.ANFANG).setBedingungen(new BHauptwaffe())
						.setDirektWirkung(new MagieWirkung(2, false)).createDTE()));

		neueKarte(new Waffenkarte("Silberdolch", 14, 3, 9, 0b11110110,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_VOR).setBedingungen(new BWenigerBasisAngriff())
						.setWirkung(StatWirkung.geschwindigkeit(5)).createZTE()));

		neueKarte(new Waffenkarte("Fortg. Mag. Stab" /*"Fortgeschrittener Magischer Stab"*/,
				15, 6, 6, Karte.alleKlassen,
				new DTEBuilder().setStartTrigger(StartTrigger.ANFANG).setBedingungen(new BHauptwaffe())
						.setDirektWirkung(new MagieWirkung(2, false)).createDTE(),
				new ZTEBuilder(NKartentyp.WAFFE).setNum(1).setStartTrigger(StartTrigger.VERWENDET)
						.setWirkung(StatWirkung.magie(1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Schutzflügel", 6, 2, 4, 0b00010000,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.IMMER_NACH).setBedingungen(new BHauptwaffe())
						.setWirkung(StatWirkung.verteidigung(2)).createZTE()));
		neueKarte(new Waffenkarte("Kleiner Stock", 9, 1, 8, Karte.alleKlassen));

		neueKarte(new Waffenkarte("Wasserstab", 11, 8, 4, 0b00100010));

		neueKarte(new Waffenkarte("Magischer Stab", 10, 5, 5, 0b00100100,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.VERWENDET)
						.setWirkung(StatWirkung.magie(1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Waffenkarte("Schleimstab", 7, 3, 4, 0b01000111,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.angriff(-2)).createZTE()));
		neueKarte(new Waffenkarte("Minilaserschwert", 10, 5, 4, Karte.alleKlassen,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.mindestschaden(2)).createZTE()));
		neueKarte(new Waffenkarte("Dolch", 8, 3, 6, 0b11101000));
		neueKarte(new Waffenkarte("Abenteuerschwert", 7, 4, 5, 0b10000000));
		neueKarte(new Waffenkarte("Stahlschwert", 10, 6, 5, 0b10001010));
		neueKarte(new Waffenkarte("Beschwörungsstab", 8, 4, 3, 0b00000001,
				new ZTEBuilder(NKartentyp.WAFFE).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.angriff(2)).setDauer(2).createZTE()));
	}
}