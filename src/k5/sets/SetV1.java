package k5.sets;

public class SetV1
{
	/*neueKarte(new Waffenkarte("Eisenschwert", 8, 3, 5, 0b10001000));
	neueKarte(new Waffenkarte("Kleiner Stock", 2, 1, 8, 0b11111111));
	neueKarte(new Waffenkarte("Großer Stock", 4, 4, 3, 0b11011011));
	neueKarte(new Waffenkarte("Etwas Kleiner Stock", 3, 2, 7, 0b11111111));
	neueKarte(new Waffenkarte("Dolch", 12, 3, 6, 0b11101000));
	neueKarte(new Waffenkarte("Wasserorb", 9, 4, 7, 0b00100011,
			new XEffekt(">", "Bei Angriff: +1 M", StartTrigger.NACHANGRIFF, EndTrigger.ZUGANZAHL, 1, tre,
					new ExtramagieEffekt(false, 1)),
			new Effekt("*", "Wenn angegriffen, zahle 2 M (max 1x) für V +3 (Nur jetzt)"))); //TODO
	neueKarte(new Waffenkarte("Schild", 8, 2, 4, 0b11111001,
			new XEffekt("HW", "+3 V", StartTrigger.HAUPTWAFFE, EndTrigger.HAUPTWAFFE, 0, true,
					new CharakterWertEffekt(false, 0, 3, 0))));
	neueKarte(new Waffenkarte("Spiegelsplitter", 5, 1, 9, 0b00001100,
			new XEffekt("HW", "-3 V", StartTrigger.HAUPTWAFFE, EndTrigger.HAUPTWAFFE, 0, true,
					new CharakterWertEffekt(false, 0, -3, 0)), null)); //TODO
	neueKarte(new Waffenkarte("Pflanzenranke", 8, 2, 3, 0b00010001,
			new XEffekt(">", "Ziel: G -3 (2 Züge)", StartTrigger.NACHANGRIFF, EndTrigger.ZUGANZAHL, 2, true,
					new CharakterWertEffekt(true, 0, 0, -3))));
	neueKarte(new Waffenkarte("Kristall der Stärke", 12, 5, 1, 0b00100100,
			new XEffekt("A", "A +3, falls Anwender niedrigeren Angriff hat", StartTrigger.BEREIT, EndTrigger.ZUGANZAHL, 1, true,
					new WaffeWertEffekt(false, 3, 0, W.OK))));

	neueKarte(new Angriffsaktion("Hauptwaffe", 3,
			0, 0, new Effekt("HW")));
	neueKarte(new Angriffsaktion("Standardangriff", 2,
			0, 0));
	neueKarte(new Angriffsaktion("Brecher", -1, 2, -5,
			new Effekt("HW"), new XEffekt(">", "Ziel HW Angriff -3 (2 Züge)",
			StartTrigger.NACHANGRIFF, EndTrigger.ZUGANZAHL, 2, true,
			new WaffeWertEffekt(true, -3, 0, W.HW))));
	neueKarte(new Angriffsaktion("Disruptorangriff", -3, -2, 4,
			new Effekt("HW"), new XEffekt("*", "Ziel Angriff -2 (1 Zug)",
			StartTrigger.NACHANGRIFF, EndTrigger.ZUGANZAHL, 1, false,
			new CharakterWertEffekt(true, -2, 0, 0))));
	neueKarte(new Angriffsaktion("Risiko", 2, 3, 1,
			new Effekt("HW"), new XEffekt("A", "V -4 (1 Zug)",
			StartTrigger.BEREIT, EndTrigger.ZUGANZAHL, 1, true,
			new CharakterWertEffekt(false, 0, -4, 0))));
	neueKarte(new Angriffsaktion("Schutzangriff", 1, 1, -5,
			new Effekt("HW"), new XEffekt("A", "V +4 (1 Zug)",
			StartTrigger.BEREIT, EndTrigger.ZUGANZAHL, 1, true,
			new CharakterWertEffekt(false, 0, 4, 0))));
	neueKarte(new Angriffsaktion("Geladener Angriff", -5,
			0, 0, new Effekt("HW"),
			new XEffekt(null, "Angriff + Waffe Angriff",
			StartTrigger.ANFANG, EndTrigger.ENDE, 0, true, null))); //TODO
	neueKarte(new Angriffsaktion("Energieangriff", 3,
			0, -3));
	neueKarte(new Angriffsaktion("Wut", 1, 1, -2,
			new XEffekt(null, "A +4, falls Ziel höhere Leben hat",
			StartTrigger.BEREIT, EndTrigger.ZUGANZAHL, 1, true,
			new AktionWertEffekt(false, 3, 0)))); //TODO
	neueKarte(new Angriffsaktion("Powerangriff", 1,
			3, -3));
	neueKarte(new Angriffsaktion("Doppelschlag", -7,
			-2, 3, new Effekt("Greife genau 2x an"))); //TODO
	neueKarte(new Angriffsaktion("Zeitlupe", -6,
			0, 5));
	neueKarte(new Angriffsaktion("Magischer Angriff", -2,
			-2, 0, new Effekt("Verursacht immer min. 3 Schaden"))); //TODO
	neueKarte(new Angriffsaktion("Kombination", -3,
			2, -4, new XEffekt(">", "",
			StartTrigger.VORANGRIFF, EndTrigger.NACHANGRIFF, 0, true,
			new AktionWertEffekt(false, 4, 0)))); //TODO
	neueKarte(new Angriffsaktion("Powerstoß", -5, 1, 4,
			new XEffekt("*", "Ziel nach Angriff: V +5 (1 Zug)",
			StartTrigger.NACHANGRIFF, EndTrigger.ZUGANZAHL, 1, false,
			new CharakterWertEffekt(true, 0, 5, 0))));
	//TODO Waffentausch

	neueKarte(new Charakterkarte("Ich1", 7, 7, 7, 8, 10));
	neueKarte(new Charakterkarte("Gegner1", 8, 7, 6, 6, 10));
	neueKarte(new Charakterkarte("Blocko", 7, 7, 3, 8, 12,
			new XEffekt("*", "Wenn angegriffen, danach V +3 (1 Zug)", StartTrigger.NACHANGEGRIFFEN,
					EndTrigger.ZUGANZAHL, 1, false, new CharakterWertEffekt(false, 0, 3, 0))));
	neueKarte(new Charakterkarte("Schleimo", 6, 7, 7, 7, 15));*/
}