package effektkarten.sets;

import effektkarten.effekte.bedingung.*;
import effektkarten.effekte.wirkung.MagieWirkung;
import effektkarten.effekte.effekt.*;
import effektkarten.effekte.wirkung.*;
import effektkarten.effekte.ziel.*;
import effektkarten.karten.*;

@SuppressWarnings("MagicNumber")
public final class SetV2Aktionen extends KartensetBuilder<Aktionskarte>
{
	public SetV2Aktionen()
	{
		neueKarte(new Aktionskarte("Zeitlupe", -4, 0, 6, false, false));
		neueKarte(new Aktionskarte("Disruptorangriff", -3, -2, 4, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifft(ZeitEffektBetrifft.ZIEL)
						.setAn(EffektZielKartentyp.CHARAKTER).setWirkung(StatWirkung.angriff(-2)).createZTE()));
		neueKarte(new Aktionskarte("Powerangriff", 1, 3, -3, false, false));
		neueKarte(new Aktionskarte("Ausholen", -3, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.ZUGENDE).setAn(EffektZielKartentyp.WAFFE)
						.setZielWaffe(MitWaffe.HW).setWirkung(StatWirkung.angriff(6)).setDauer(2).createZTE()));
		neueKarte(new Aktionskarte("Adrenalin", 0, 1, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BLebenIst(Vergleich.KG, 10)).setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Aktionskarte("Magiehaltiger Angriff", 0, 0, -1, false, true,
				new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WMagiehaltig(Wirkungswert.ANGRIFF, 8)).createZTE()));
		neueKarte(new Aktionskarte("Brecher", -1, 2, -3, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setDauer(2).setBetrifft(ZeitEffektBetrifft.ZIEL).setAn(EffektZielKartentyp.WAFFE).setZielWaffe(MitWaffe.HW)
						.setWirkung(StatWirkung.angriff(-3)).createZTE()));
		neueKarte(new Aktionskarte("Geladener Angriff", -5, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WWaffeAngriff(1)).createZTE()));
		neueKarte(new Aktionskarte("Verstärkender Angriff", -2, 1, 1, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(1)).createZTE()));
		neueKarte(new Aktionskarte("Ladeangriff", 4, -3, 0, false, false));
		neueKarte(new Aktionskarte("Magisches Stößchen", 2, -2, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.mindestschaden(1)).createZTE()));
		neueKarte(new Aktionskarte("Rüstungspolitur", 4, 0, -5, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new StatWirkung(0, 0, 1, 0, 1)).createZTE()));
		neueKarte(new Aktionskarte("Extraschlag", -5, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.extraangriffe(1)).createZTE()));
		neueKarte(new Aktionskarte("Schnellangriff", 1, -3, 3, false, false));
		neueKarte(new Aktionskarte("Risiko", 2, 3, 1, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(-3)).createZTE()));
		neueKarte(new Aktionskarte("Hauptwaffe", 3, 0, 0, true, false));
		neueKarte(new Aktionskarte("Konter", 1, -2, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_VOR).setBedingungen(new BWurdeAngegriffen())
						.setWirkung(StatWirkung.angriff(4)).setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new Aktionskarte("Dunkler Angriff", 3, -1, 0, true,
				false, new DTEBuilder().setStartTrigger(StartTrigger.EINMAL_NACH)
						.setDirektWirkung(new MagieWirkung(-2, true)).createDTE()));
		neueKarte(new Aktionskarte("Meisterhafte Verteidigung", -3, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(5)).createZTE()));
		neueKarte(new Aktionskarte("Magischer Angriff", 0, -2, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.mindestschaden(2)).createZTE()));
		neueKarte(new Aktionskarte("Schutzangriff", 1, 1, -5, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).createZTE()));
		neueKarte(new Aktionskarte("Powerstoß", -2, 1, 6, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifft(ZeitEffektBetrifft.ZIEL)
						.setAn(EffektZielKartentyp.CHARAKTER).setWirkung(StatWirkung.verteidigung(5)).createZTE()));
		neueKarte(new Aktionskarte("Doppelschlag", -1, -2, 3, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.setzeangriffe(2)).createZTE()));
		neueKarte(new Aktionskarte("Energieangriff", 4, 0, 3, false, false));
		neueKarte(new Aktionskarte("Zertrümmerer", 1, 4, -4, true, false));
		neueKarte(new Aktionskarte("Hieb", 1, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BGesVorteil()).setWirkung(StatWirkung.angriff(2)).createZTE()));
		neueKarte(new Aktionskarte("Auslassschlag", -8, 4, 4, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(4)).createZTE()));
		neueKarte(new Aktionskarte("Wut", 1, 1, -2, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_VOR)
						.setBedingungen(new BLebenVergleich(Vergleich.K)).setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Aktionskarte("Blockschlag", 2, 0, -3, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).createZTE()));
		neueKarte(new Aktionskarte("Rüstungsangriff", 1, 1, -1, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.verteidigung(2)).createZTE()));
		neueKarte(new Aktionskarte("Standardangriff", 2, 0, 0, false, false));
		neueKarte(new Aktionskarte("Kombination", 2, 2, -4, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_VOR).setBedingungen(new BZielVonTeamAngegriffen())
						.setWirkung(StatWirkung.angriff(4)).setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new Aktionskarte("Eisschlag", -1, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.EINMAL_NACH).setBetrifft(ZeitEffektBetrifft.ZIEL)
						.setAn(EffektZielKartentyp.CHARAKTER).setWirkung(StatWirkung.geschwindigkeit(-2)).setDauer(3).createZTE()));
		neueKarte(new Aktionskarte("Schnellschlag", -3, -2, 3, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BGesVorteil()).setWirkung(StatWirkung.extraangriffe(1)).createZTE()));
		neueKarte(new Aktionskarte("Magieklatsch", 0, -2, 0, false,
				false, new DTEBuilder().setStartTrigger(StartTrigger.IMMER_NACH)
						.setDirektWirkung(new MagieWirkung(2, false)).createDTE()));
		neueKarte(new Aktionskarte("Schnellschwung", 1, 0, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertIst(Basiswert.GESCHWINDIGKEIT, EffektZielKartentyp.WAFFE, Vergleich.GG, 5))
						.setWirkung(StatWirkung.angriff(2)).createZTE()));
		neueKarte(new Aktionskarte("Tritt", 3, 4, -2, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new WWaffeAngriff(-1)).createZTE()));
		neueKarte(new Aktionskarte("Aufladung", 1, -8, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH).setAn(
						EffektZielKartentyp.CHARAKTER)
						.setWirkung(StatWirkung.angriff(3)).setDauer(3).createZTE()));
		neueKarte(new Aktionskarte("Illusion", 0, -4, 1, true,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(StatWirkung.verteidigung(3)).setEndTrigger(EndTrigger.NACH_ANGEGRIFFEN).createZTE()));
		neueKarte(new Aktionskarte("Hinterhalt", 0, 4, -2, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.IMMER_NACH)
						.setStartTriggerSeite(StartTriggerSeite.GEGNER).setWirkung(StatWirkung.angriff(-2)).createZTE()));
		neueKarte(new Aktionskarte("Ninjaschlag", 1, -2, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new DatenWirkung(DatenWirkungTyp.GES_VORTEIL, Wirkungswert.ANGRIFF, 1, 1, 5)).createZTE()));
		neueKarte(new Aktionskarte("Schneller Laser", 1, -2, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setWirkung(new DatenWirkung(DatenWirkungTyp.GES_VORTEIL, Wirkungswert.MINDESTSCHADEN, 1, 3, 3)).createZTE()));
		neueKarte(new Aktionskarte("Geschwindigkeitsbelohnung", 2, -2, 0, false,
				false, new ZTEBuilder(EffektZielKartentyp.AKTION).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertVergleich(Basiswert.GESCHWINDIGKEIT, EffektZielKartentyp.CHARAKTER, Vergleich.G))
						.setWirkung(StatWirkung.angriff(2)).createZTE(),
				new ZTEBuilder(EffektZielKartentyp.AKTION).setNum(1).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertVergleich(Basiswert.GESCHWINDIGKEIT, EffektZielKartentyp.WAFFE, Vergleich.G))
						.setWirkung(StatWirkung.angriff(2)).createZTE(),
				new ZTEBuilder(EffektZielKartentyp.AKTION).setNum(2).setStartTrigger(StartTrigger.GES_NACH)
						.setBedingungen(new BBasisWertVergleich(Basiswert.GESCHWINDIGKEIT, EffektZielKartentyp.AKTION, Vergleich.G))
						.setWirkung(StatWirkung.angriff(2)).createZTE()));
	}
}