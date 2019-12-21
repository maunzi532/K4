package effektkarten.sets;

import effektkarten.effekte.bedingung.*;
import effektkarten.effekte.effekt.*;
import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.wirkung.*;
import effektkarten.karten.*;

public class SetV2Gegner extends Kartenset<Charakterkarte>
{
	public SetV2Gegner()
	{
		super();
		neueKarte(new Charakterkarte("Giftpilz", 9, 11, 7, 8, 12, 142,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(StatWirkung.verteidigung(-2)).setDauer(2).createZTE()));
		neueKarte(new Charakterkarte("Fortschreitender Gegner", 14, 14, 14, 14, 14, 860));
		neueKarte(new Charakterkarte("Gifto", 6, 7, 12, 12, 10, 112,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(new StatWirkung(0, -1, -1)).setDauer(2).createZTE()));
		neueKarte(new Charakterkarte("Viecho", 5, 5, 6, 5, 7, 8));
		neueKarte(new Charakterkarte("Steini", 7, 7, 5, 8, 6, 19));
		neueKarte(new Charakterkarte("Blocko", 7, 8, 3, 8, 10, 24,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH)
						.setStartTriggerSeite(StartTriggerSeite.GEGNER).setWirkung(StatWirkung.verteidigung(3)).createZTE()));
		neueKarte(new Charakterkarte("Angreifer", 13, 10, 8, 9, 10, 150));
		neueKarte(new Charakterkarte("Minimagier", 6, 8, 6, 5, 6, 16,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.ANFANG)
						.setWirkung(StatWirkung.magie(1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Charakterkarte("Lauchulm", 6, 7, 6, 5, 5, 10));
		neueKarte(new Charakterkarte("Gegner", 8, 6, 6, 6, 8, 22));
		neueKarte(new Charakterkarte("Minizombie", 6 /*8*/, 6, 5, 7, 5, 13));
		neueKarte(new Charakterkarte("Stoßer", 9, 8, 5, 8, 8, 35,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(StatWirkung.angriff(-2)).setDauer(2).createZTE()));
		neueKarte(new Charakterkarte("Fetti", 6, 6, 5, 9, 8, 21));
		neueKarte(new XGegnerKarte("Pflanzerich", 7, 6, 9, 7, 5,
				new int[]{21, 25, 30, 34, 38}).xLeben());
		neueKarte(new Charakterkarte("Luftfisch", 7, 9, 9, 6, 7, 38));
		neueKarte(new XGegnerKarte("Koba", 6, 8, 7, 5, 8,
				new int[]{22, 30, 40, 52, 65}).xAngriff().xVerteidigung());
		neueKarte(new Charakterkarte("Rächer", 7, 8, 7, 9, 7, 48,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_VOR).setBedingungen(new BLebenIst(
						Vergleich.KG, 10))
						.setWirkung(StatWirkung.angriff(3)).setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new Charakterkarte("Silbergolem", 8, 14, 5, 9, 6, 48));
		neueKarte(new Charakterkarte("Tern", 8, 7, 12, 6, 9, 58));
		neueKarte(new Charakterkarte("Zwergi", 6, 9, 6, 7, 7, 25));
		neueKarte(new Charakterkarte("Stummo", 10, 11, 7, 12, 9, 133));
		neueKarte(new Charakterkarte("Luftwand", 5, 9, 13, 12, 10, 112));
		neueKarte(new Charakterkarte("Mittelgegner", 10, 10, 10, 10, 10, 160));
		neueKarte(new Charakterkarte("Oktarko", 8, 9, 5, 8, 10, 46));
		neueKarte(new Charakterkarte("Vogelgegner", 6, 7, 9, 5, 7, 21));
		neueKarte(new XGegnerKarte("Kleinvogel", 7, 6, 7, 7, 8,
				new int[]{26, 30, 34, 38, 41}).xGeschwindigkeit());
		neueKarte(new Charakterkarte("Speergegner", 10, 9, 6, 7, 7, 42));
		neueKarte(new Charakterkarte("Maschino", 9, 7, 6, 9, 8, 44));
		neueKarte(new Charakterkarte("Rumm", 7, 7, 5, 9, 7, 25));
		neueKarte(new Charakterkarte("Sprinto", 8, 6, 10, 8, 8, 49));
		neueKarte(new Charakterkarte("Schleimo", 6, 8, 7, 7, 13, 49));
		neueKarte(new XGegnerKarte("Steinulm", 8, 8, 6, 7, 8,
				new int[]{34, 39, 44, 49, 54}).xVerteidigung());
		neueKarte(new Charakterkarte("Kobold", 6, 7, 5, 5, 6, 10));
		neueKarte(new XGegnerKarte("Lanzus", 8, 6, 6, 6, 7,
				new int[]{20, 26, 34, 44, 54}).xWaffenwert().xGeschwindigkeit());
		neueKarte(new Charakterkarte("Stammo", 8, 8, 7, 11, 9, 71));
		neueKarte(new Charakterkarte("Stung", 5, 6, 9, 5, 6, 13));
		neueKarte(new XGegnerKarte("Attacko", 5, 7, 8, 7, 8,
				new int[]{25, 30, 35, 40, 45}).xAngriff());
		neueKarte(new XGegnerKarte("Koblin", 9, 7, 8, 9, 10,
				new int[]{73, 83, 93, 104, 114}).xWaffenwert());
		neueKarte(new Charakterkarte("Standardgegner", 5, 5, 5, 5, 5, 5));
		neueKarte(new Charakterkarte("Brechling", 6, 8, 10, 8, 11, 79,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(StatWirkung.verteidigung(-2)).createZTE()));
		neueKarte(new Charakterkarte("Dringmagier", 7, 8, 9, 8, 12, 88,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.ANFANG)
						.setWirkung(StatWirkung.mindestschaden(2)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new Charakterkarte("Energi", 9, 12, 8, 6, 12, 177,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.ANFANG)
						.setWirkung(StatWirkung.magie(2)).setEndTrigger(EndTrigger.VERWENDET).createZTE(),
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_VOR)
						.setWirkung(new WAktuelleMagieWirkung(Wirkungswert.ANGRIFF, 1, 1, 5))
						.setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new Charakterkarte("Blob", 5, 6, 9, 9, 8, 31));
		neueKarte(new Charakterkarte("Obermittelgegner", 12, 12, 12, 12, 12, 398));
		neueKarte(new Charakterkarte("Schwacher Gegner", 7, 7, 7, 7, 7, 27));
		neueKarte(new Charakterkarte("Golo", 9, 9, 6, 10, 8, 62));
		neueKarte(new Charakterkarte("Lichti", 7, 8, 14, 7, 8, 70));
		neueKarte(new Charakterkarte("Minigolem", 7, 10, 5, 8, 7, 31));
		neueKarte(new Charakterkarte("Fortgeschrittener Gegner", 15, 15, 15, 15, 15, 1215));
		neueKarte(new Charakterkarte("Klingo", 8, 8, 9, 6, 6, 37,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new Charakterkarte("Ninja", 6, 8, 8, 5, 6, 22,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.EINMAL_VOR)
						.setBedingungen(new BGesVorteil()).setWirkung(StatWirkung.angriff(2)).createZTE()));
	}
}