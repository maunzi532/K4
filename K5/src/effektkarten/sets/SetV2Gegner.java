package effektkarten.sets;

import effektkarten.effekte.bedingung.*;
import effektkarten.effekte.effekt.*;
import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.wirkung.*;
import effektkarten.karten.*;

public class SetV2Gegner extends KartensetBuilder<Gegner>
{
	public SetV2Gegner()
	{
		super();
		neueKarte(new StandardGegner("Giftpilz", 9, 11, 7, 8, 12, 142,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(StatWirkung.verteidigung(-2)).setDauer(2).createZTE()));
		neueKarte(new StandardGegner("Fortschreitender Gegner", 14, 14, 14, 14, 14, 860));
		neueKarte(new StandardGegner("Gifto", 6, 7, 12, 12, 10, 112,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(new StatWirkung(0, -1, -1)).setDauer(2).createZTE()));
		neueKarte(new StandardGegner("Viecho", 5, 5, 6, 5, 7, 8));
		neueKarte(new StandardGegner("Steini", 7, 7, 5, 8, 6, 19));
		neueKarte(new StandardGegner("Blocko", 7, 8, 3, 8, 10, 24,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH)
						.setStartTriggerSeite(StartTriggerSeite.GEGNER).setWirkung(StatWirkung.verteidigung(3)).createZTE()));
		neueKarte(new StandardGegner("Angreifer", 13, 10, 8, 9, 10, 150));
		neueKarte(new StandardGegner("Minimagier", 6, 8, 6, 5, 6, 16,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.ANFANG)
						.setWirkung(StatWirkung.magie(1)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new StandardGegner("Lauchulm", 6, 7, 6, 5, 5, 10));
		neueKarte(new StandardGegner("Gegner", 8, 6, 6, 6, 8, 22));
		neueKarte(new StandardGegner("Minizombie", 6 /*8*/, 6, 5, 7, 5, 13));
		neueKarte(new StandardGegner("Stoßer", 9, 8, 5, 8, 8, 35,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(StatWirkung.angriff(-2)).setDauer(2).createZTE()));
		neueKarte(new StandardGegner("Fetti", 6, 6, 5, 9, 8, 21));
		neueKarte(new XGegnerBuilder("Pflanzerich", 7, 6, 9, 7, 5)
				.xLeben().xExp(21, 25, 30, 34, 38));
		neueKarte(new StandardGegner("Luftfisch", 7, 9, 9, 6, 7, 38));
		neueKarte(new XGegnerBuilder("Koba", 6, 8, 7, 5, 8)
				.xAngriff().xVerteidigung().xExp(22, 30, 40, 52, 65));
		neueKarte(new StandardGegner("Rächer", 7, 8, 7, 9, 7, 48,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_VOR).setBedingungen(new BLebenIst(
						Vergleich.KG, 10))
						.setWirkung(StatWirkung.angriff(3)).setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new StandardGegner("Silbergolem", 8, 14, 5, 9, 6, 48));
		neueKarte(new StandardGegner("Tern", 8, 7, 12, 6, 9, 58));
		neueKarte(new StandardGegner("Zwergi", 6, 9, 6, 7, 7, 25));
		neueKarte(new StandardGegner("Stummo", 10, 11, 7, 12, 9, 133));
		neueKarte(new StandardGegner("Luftwand", 5, 9, 13, 12, 10, 112));
		neueKarte(new StandardGegner("Mittelgegner", 10, 10, 10, 10, 10, 160));
		neueKarte(new StandardGegner("Oktarko", 8, 9, 5, 8, 10, 46));
		neueKarte(new StandardGegner("Vogelgegner", 6, 7, 9, 5, 7, 21));
		neueKarte(new XGegnerBuilder("Kleinvogel", 7, 6, 7, 7, 8)
				.xGeschwindigkeit().xExp(26, 30, 34, 38, 41));
		neueKarte(new StandardGegner("Speergegner", 10, 9, 6, 7, 7, 42));
		neueKarte(new StandardGegner("Maschino", 9, 7, 6, 9, 8, 44));
		neueKarte(new StandardGegner("Rumm", 7, 7, 5, 9, 7, 25));
		neueKarte(new StandardGegner("Sprinto", 8, 6, 10, 8, 8, 49));
		neueKarte(new StandardGegner("Schleimo", 6, 8, 7, 7, 13, 49));
		neueKarte(new XGegnerBuilder("Steinulm", 8, 8, 6, 7, 8)
				.xVerteidigung().xExp(34, 39, 44, 49, 54));
		neueKarte(new StandardGegner("Kobold", 6, 7, 5, 5, 6, 10));
		neueKarte(new XGegnerBuilder("Lanzus", 8, 6, 6, 6, 7)
				.xWaffenwert().xGeschwindigkeit().xExp(20, 26, 34, 44, 54));
		neueKarte(new StandardGegner("Stammo", 8, 8, 7, 11, 9, 71));
		neueKarte(new StandardGegner("Stung", 5, 6, 9, 5, 6, 13));
		neueKarte(new XGegnerBuilder("Attacko", 5, 7, 8, 7, 8)
				.xAngriff().xExp(25, 30, 35, 40, 45));
		neueKarte(new XGegnerBuilder("Koblin", 9, 7, 8, 9, 10)
				.xWaffenwert().xExp(73, 83, 93, 104, 114));
		neueKarte(new StandardGegner("Standardgegner", 5, 5, 5, 5, 5, 5));
		neueKarte(new StandardGegner("Brechling", 6, 8, 10, 8, 11, 79,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_NACH).setBetrifftGegner(true)
						.setWirkung(StatWirkung.verteidigung(-2)).createZTE()));
		neueKarte(new StandardGegner("Dringmagier", 7, 8, 9, 8, 12, 88,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.ANFANG)
						.setWirkung(StatWirkung.mindestschaden(2)).setEndTrigger(EndTrigger.VERWENDET).createZTE()));
		neueKarte(new StandardGegner("Energi", 9, 12, 8, 6, 12, 177,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.ANFANG)
						.setWirkung(StatWirkung.magie(2)).setEndTrigger(EndTrigger.VERWENDET).createZTE(),
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.IMMER_VOR)
						.setWirkung(new WAktuelleMagieWirkung(Wirkungswert.ANGRIFF, 1, 1, 5))
						.setEndTrigger(EndTrigger.NACH_ANGRIFF).createZTE()));
		neueKarte(new StandardGegner("Blob", 5, 6, 9, 9, 8, 31));
		neueKarte(new StandardGegner("Obermittelgegner", 12, 12, 12, 12, 12, 398));
		neueKarte(new StandardGegner("Schwacher Gegner", 7, 7, 7, 7, 7, 27));
		neueKarte(new StandardGegner("Golo", 9, 9, 6, 10, 8, 62));
		neueKarte(new StandardGegner("Lichti", 7, 8, 14, 7, 8, 70));
		neueKarte(new StandardGegner("Minigolem", 7, 10, 5, 8, 7, 31));
		neueKarte(new StandardGegner("Fortgeschrittener Gegner", 15, 15, 15, 15, 15, 1215));
		neueKarte(new StandardGegner("Klingo", 8, 8, 9, 6, 6, 37,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.EINMAL_NACH)
						.setWirkung(StatWirkung.angriff(3)).createZTE()));
		neueKarte(new StandardGegner("Ninja", 6, 8, 8, 5, 6, 22,
				new ZTEBuilder(EffektZielKartentyp.CHARAKTER).setStartTrigger(StartTrigger.EINMAL_VOR)
						.setBedingungen(new BGesVorteil()).setWirkung(StatWirkung.angriff(2)).createZTE()));
	}
}