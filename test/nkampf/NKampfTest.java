package nkampf;

import effektkarten.effekte.ziel.MitWaffe;
import effektkarten.textbild.*;
import effektkarten.karten.*;
import effektkarten.sets.*;
import java.util.*;
import kampf.*;
import main.*;
import org.junit.jupiter.api.*;
import stapelkarten.*;

public final class NKampfTest
{
	private Kartenset<Aktionskarte> aktionen;
	private Kartenset<Waffenkarte> waffen;
	private Kartenset<Gegner> gegner;
	private Kartenset<Charakterkarte> klassen;
	private KarteBild3 karteBild3;

	@BeforeEach
	public void init()
	{
		aktionen = new SetV2Aktionen().fertig();
		waffen = new SetV2Waffen().fertig();
		gegner = new SetV2Gegner().fertig();
		klassen = new SetV2Klassen().fertig();
		karteBild3 = new KarteBild3();
	}

	@Test
	public void test()
	{
		Einstellungen e = Einstellungen.lies("Einstellungen", "1_Spieler");
		Charakterkarte held = klassen.gibKarte("Geist");
		Waffenkarte heldWaffe = waffen.gibKarte("Spiegelsplitter");
		Charakterkarte gegner1 = new Charakterkarte("TGegner", 8, 7, 6, 6, 10, 20);
		Waffenkarte gegnerWaffe = waffen.gibKarte("Großer Stock");

		NTeilnehmer nt0 = new NTeilnehmer(e, held, heldWaffe, null);
		NTeilnehmer nt1 = new NTeilnehmer(e, gegner1, gegnerWaffe, null);

		NKampf nKampf = new NKampf(e, List.of(nt0), List.of(nt1), new SortierterKartenstapel<>(aktionen,
				"Schutzangriff", "Geschwindigkeitsbelohnung", "Ausholen", "Powerstoß"));
		nKampf.start();
		nKampf.anfangstrigger();
		nKampf.beginneZug();
		Assertions.assertTrue(nKampf.aktionskarte(nt0, aktionen.gibKarte("Schnellangriff"), MitWaffe.HW, nt1));
		Assertions.assertTrue(nKampf.aktionskarte(nt1, aktionen.gibKarte("Powerangriff"), MitWaffe.HW, nt0));
		//nKampf.gegnerAktionskarten();
		System.out.println(karteBild3.inZeilen(List.of(held, heldWaffe, nt0.getAktionKarte(), gegner1,
				gegnerWaffe, nt1.getAktionKarte()), 7));
		nKampf.magieZahlen();
		Assertions.assertTrue(nKampf.magieEffektOptionenOK());
		nKampf.zugV();
		Assertions.assertEquals(24, nt0.getLeben());
		Assertions.assertEquals(30, nt1.getLeben());
		nKampf.angriffe();
		nKampf.beendeZug();
		Assertions.assertEquals(1, nt0.getMagie());
		Assertions.assertEquals(1, nt1.getMagie());
		Assertions.assertEquals(14, nt0.getLeben());
		Assertions.assertEquals(21, nt1.getLeben());
		nKampf.beginneZug();
		Assertions.assertFalse(nKampf.aktionskarte(nt0, aktionen.gibKarte("Disruptorangriff"), MitWaffe.HW, nt1));
		Assertions.assertTrue(nKampf.aktionskarte(nt0, aktionen.gibKarte("Schutzangriff"), MitWaffe.HW, nt1));
		Assertions.assertTrue(nKampf.aktionskarte(nt1, aktionen.gibKarte("Brecher"), MitWaffe.HW, nt0));
		//nKampf.gegnerAktionskarten();
		System.out.println(karteBild3.inZeilen(List.of(held, heldWaffe, nt0.getAktionKarte(), gegner1,
				gegnerWaffe, nt1.getAktionKarte()), 7));
		nKampf.magieZahlen();
		Assertions.assertTrue(nKampf.magieEffektOptionenOK());
		nKampf.zugV();
		nKampf.angriffe();
		nKampf.beendeZug();
		Assertions.assertEquals(2, nt0.getMagie());
		Assertions.assertEquals(0, nt1.getMagie());
		Assertions.assertEquals(8, nt0.getLeben());
		Assertions.assertEquals(13, nt1.getLeben());
	}

	@Test
	public void test2()
	{
		SortierterKartenstapel<Aktionskarte> kartenstapel = new SortierterKartenstapel<>(aktionen,
				"Schnellangriff", "Geschwindigkeitsbelohnung", "Ausholen", "Powerstoß",
				"Powerangriff", "Schutzangriff", "Disruptorangriff", "Brecher");
		Test1v1 tn = new Test1v1(Einstellungen.lies("Einstellungen", "1_Spieler"), kartenstapel);
		tn.spieler(klassen.gibKarte("Geist"), waffen.gibKarte("Spiegelsplitter"));
		StandardGegner gegner1 = new StandardGegner("TGegner", 8, 7, 6, 6, 10, 20);
		tn.gegner(gegner1, waffen.gibKarte("Großer Stock"));
		tn.start(false);
		tn.beginneZug();
		Assertions.assertTrue(tn.aktionskarte(0, MitWaffe.HW));
		tn.gegnerAktionskarten();
		Assertions.assertTrue(tn.magieEffekte());
		tn.gesBerechnen();
		NTeilnehmer nt0 = tn.getSpieler();
		NTeilnehmer nt1 = tn.getGegner();
		Assertions.assertEquals(24, nt0.getLeben());
		Assertions.assertEquals(30, nt1.getLeben());
		tn.angriffe();
		Assertions.assertEquals(0, tn.beendeZug());
		tn.aktionskartenAblegen();
		Assertions.assertEquals(1, nt0.getMagie());
		Assertions.assertEquals(1, nt1.getMagie());
		Assertions.assertEquals(14, nt0.getLeben());
		Assertions.assertEquals(21, nt1.getLeben());
		tn.beginneZug();
		Assertions.assertTrue(tn.aktionskarte(3, MitWaffe.HW));
		tn.gegnerAktionskarten();
		Assertions.assertTrue(tn.magieEffekte());
		tn.gesBerechnen();
		tn.angriffe();
		Assertions.assertEquals(0, tn.beendeZug());
		tn.aktionskartenAblegen();
		Assertions.assertEquals(2, nt0.getMagie());
		Assertions.assertEquals(0, nt1.getMagie());
		Assertions.assertEquals(8, nt0.getLeben());
		Assertions.assertEquals(13, nt1.getLeben());
	}
}