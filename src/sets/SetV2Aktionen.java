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
		neueKarte(new Aktionskarte("Zeitlupe", -4, 0, 6, false));
		neueKarte(new Aktionskarte("Disruptorangriff", -3, -2, 4, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.angriff(-2)).createZTE()));
		neueKarte(new Aktionskarte("Powerangriff", 1, 3, -3, false));
		neueKarte(new Aktionskarte("Ausholen", -3, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.ZUGENDE).setAn(NKartentyp.WAFFE)
						.setZielWaffe(W.HW).setWirkung(StatWirkung.angriff(6)).setDauer(2).createZTE()));
		neueKarte(new Aktionskarte("Adrenalin", 0, 1, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BLebenIst(Vergleich.KG, 10)).setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Aktionskarte("Magiehaltiger Angriff", 0, 0, -1, false, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WMagiehaltig(Wirkungswert.ANGRIFF)).createZTE()));
		neueKarte(new Aktionskarte("Brecher", -1, 2, -3, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setDauer(2).setBetrifftGegner(true).setAn(NKartentyp.WAFFE).setZielWaffe(W.HW)
						.setWirkung(StatWirkung.angriff(-3)).createZTE()));
		neueKarte(new Aktionskarte("Geladener Angriff", -5, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WWaffeAngriff(1)).createZTE()));
		neueKarte(new Aktionskarte("Verstärkender Angriff", -2, 1, 1, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(1)).createZTE()));
		neueKarte(new Aktionskarte("Ladeangriff", 4, -3, 0, false));
		neueKarte(new Aktionskarte("Magisches Stößchen", 2, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.mindestschaden(1)).createZTE()));
		neueKarte(new Aktionskarte("Rüstungspolitur", 4, 0, -5, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new StatWirkung(0, 0, 1, 0, 1)).createZTE()));
		neueKarte(new Aktionskarte("Extraschlag", -5, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.extraangriffe(1)).createZTE()));
		neueKarte(new Aktionskarte("Schnellangriff", 1, -3, 3, false));
		neueKarte(new Aktionskarte("Risiko", 2, 3, 1, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(-3)).createZTE()));
		neueKarte(new Aktionskarte("Hauptwaffe", 3, 0, 0, true));
		neueKarte(new Aktionskarte("Konter", 1, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_VOR).setBedingungen(new BWurdeAngegriffen())
						.setWirkung(StatWirkung.angriff(4)).setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new Aktionskarte("Dunkler Angriff", 3, -1, 0, true,
				new DTEBuilder().setStartTrigger(StartTrigger.EINMAL_NACH)
						.setDirektWirkung(new MagieWirkung(-2, true)).createDTE()));
		neueKarte(new Aktionskarte("Meisterhafte Verteidigung", -3, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(5)).createZTE()));
		neueKarte(new Aktionskarte("Magischer Angriff", 0, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.mindestschaden(2)).createZTE()));
		neueKarte(new Aktionskarte("Schutzangriff", 1, 1, -5, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).createZTE()));
		neueKarte(new Aktionskarte("Powerstoß", -2, 1, 6, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(5)).createZTE()));
		neueKarte(new Aktionskarte("Doppelschlag", -1, -2, 3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.setzeangriffe(2)).createZTE()));
		neueKarte(new Aktionskarte("Energieangriff", 4, 0, 3, false));
		neueKarte(new Aktionskarte("Zertrümmerer", 1, 4, -4, true));
		neueKarte(new Aktionskarte("Hieb", 1, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BMehrAG()).setWirkung(StatWirkung.angriff(2)).createZTE()));
		neueKarte(new Aktionskarte("Auslassschlag", -8, 4, 4, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(4)).createZTE()));
		neueKarte(new Aktionskarte("Wut", 1, 1, -2, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_VOR)
						.setBedingungen(new BLebenVergleich(Vergleich.K)).setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Aktionskarte("Blockschlag", 2, 0, -3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).createZTE()));
		neueKarte(new Aktionskarte("Rüstungsangriff", 1, 1, -1, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.verteidigung(2)).createZTE()));
		neueKarte(new Aktionskarte("Standardangriff", 2, 0, 0, false));
		neueKarte(new Aktionskarte("Kombination", 2, 2, -4, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_VOR).setBedingungen(new BZielVonTeamAngegriffen())
						.setWirkung(StatWirkung.angriff(4)).setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new Aktionskarte("Eisschlag", -1, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifftGegner(true)
						.setAn(NKartentyp.CHARAKTER).setWirkung(StatWirkung.geschwindigkeit(-2)).setDauer(3).createZTE()));
		neueKarte(new Aktionskarte("Schnellschlag", -3, -2, 3, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BMehrAG()).setWirkung(StatWirkung.extraangriffe(1)).createZTE()));
		neueKarte(new Aktionskarte("Magieklatsch", 0, -2, 0, false,
				new DTEBuilder().setStartTrigger(StartTrigger.IMMER_NACH)
						.setDirektWirkung(new MagieWirkung(2, false)).createDTE()));
		neueKarte(new Aktionskarte("Schnellschwung", 1, 0, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertIst(Basiswert.GESCHWINDIGKEIT, NKartentyp.WAFFE, Vergleich.GG, 5))
						.setWirkung(StatWirkung.angriff(2)).createZTE()));
		neueKarte(new Aktionskarte("Tritt", 3, 4, -2, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WWaffeAngriff(-1)).createZTE()));
		neueKarte(new Aktionskarte("Aufladung", 1, -8, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH).setAn(NKartentyp.CHARAKTER)
						.setWirkung(StatWirkung.angriff(3)).setDauer(3).createZTE()));
		neueKarte(new Aktionskarte("Illusion", 0, -4, 1, true,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).setEndTrigger(EndTrigger.NACH_ANGEGRIFFEN).createZTE()));
		neueKarte(new Aktionskarte("Hinterhalt", 0, 4, -2, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH)
						.setStartTriggerSeite(StartTriggerSeite.GEGNER).setWirkung(StatWirkung.angriff(-2)).createZTE()));
		neueKarte(new Aktionskarte("Ninjaschlag", 1, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WGesVorteilWirkung(Wirkungswert.ANGRIFF, 1, 1, 5)).createZTE()));
		neueKarte(new Aktionskarte("Schneller Laser", 1, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WGesVorteilWirkung(Wirkungswert.MINDESTSCHADEN, 1, 3, 3)).createZTE()));
		neueKarte(new Aktionskarte("Geschwindigkeitsbelohnung", 2, -2, 0, false,
				new ZTEBuilder(NKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertVergleich(Basiswert.GESCHWINDIGKEIT, NKartentyp.CHARAKTER, Vergleich.G))
						.setWirkung(StatWirkung.angriff(2)).createZTE(),
				new ZTEBuilder(NKartentyp.AKTION).setNum(1).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertVergleich(Basiswert.GESCHWINDIGKEIT, NKartentyp.WAFFE, Vergleich.G))
						.setWirkung(StatWirkung.angriff(2)).createZTE(),
				new ZTEBuilder(NKartentyp.AKTION).setNum(2).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertVergleich(Basiswert.GESCHWINDIGKEIT, NKartentyp.AKTION, Vergleich.G))
						.setWirkung(StatWirkung.angriff(2)).createZTE()));
	}
}