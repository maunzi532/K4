package nkampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.kartebild.*;
import effektkarten.karten.*;
import effektkarten.sets.*;
import java.util.*;
import kampf.*;
import main.*;
import org.junit.*;
import stapelkarten.*;

public class NKampfTest
{
	private Kartenset<Aktionskarte> aktionen;
	private Kartenset<Waffenkarte> waffen;
	private Kartenset<Gegner> gegner;
	private Kartenset<Charakterkarte> klassen;
	private KarteBild3 karteBild3;

	@Before
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
		Einstellungen e = new Einstellungen();
		Charakterkarte held = klassen.gibKarte("Geist");
		Waffenkarte heldWaffe = waffen.gibKarte("Spiegelsplitter");
		Charakterkarte gegner = new Charakterkarte("TGegner", 8, 7, 6, 6, 10, 20);
		Waffenkarte gegnerWaffe = waffen.gibKarte("Großer Stock");

		NTeilnehmer nt0 = new NTeilnehmer(e, held, heldWaffe, null);
		NTeilnehmer nt1 = new NTeilnehmer(e, gegner, gegnerWaffe, null);

		NKampf nKampf = new NKampf(e, List.of(nt0), List.of(nt1), aktionen.kartenstapel());
		nKampf.start();
		nKampf.anfangstrigger();
		nKampf.beginneZug();
		Assert.assertTrue(nKampf.aktionskarte(nt0, aktionen.gibKarte("Schnellangriff"), MitWaffe.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt1, aktionen.gibKarte("Powerangriff"), MitWaffe.HW, nt0));
		//nKampf.gegnerAktionskarten();
		System.out.println(karteBild3.inZeilen(List.of(held, heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte()), 7));
		nKampf.magieZahlen();
		Assert.assertTrue(nKampf.magieEffektOptionenOK());
		nKampf.zugV();
		Assert.assertEquals(24, nt0.getLeben());
		Assert.assertEquals(30, nt1.getLeben());
		nKampf.angriffe();
		nKampf.beendeZug();
		Assert.assertEquals(1, nt0.getMagie());
		Assert.assertEquals(1, nt1.getMagie());
		Assert.assertEquals(14, nt0.getLeben());
		Assert.assertEquals(21, nt1.getLeben());
		nKampf.beginneZug();
		Assert.assertFalse(nKampf.aktionskarte(nt0, aktionen.gibKarte("Disruptorangriff"), MitWaffe.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt0, aktionen.gibKarte("Schutzangriff"), MitWaffe.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt1, aktionen.gibKarte("Brecher"), MitWaffe.HW, nt0));
		//nKampf.gegnerAktionskarten();
		System.out.println(karteBild3.inZeilen(List.of(held, heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte()), 7));
		nKampf.magieZahlen();
		Assert.assertTrue(nKampf.magieEffektOptionenOK());
		nKampf.zugV();
		nKampf.angriffe();
		nKampf.beendeZug();
		Assert.assertEquals(2, nt0.getMagie());
		Assert.assertEquals(0, nt1.getMagie());
		Assert.assertEquals(8, nt0.getLeben());
		Assert.assertEquals(13, nt1.getLeben());
	}

	@Test
	public void test2()
	{
		SortierterKartenstapel<Aktionskarte> kartenstapel = new SortierterKartenstapel<>(aktionen,
				"Schnellangriff", "Geschwindigkeitsbelohnung", "Ausholen", "Powerstoß",
				"Powerangriff", "Schutzangriff", "Disruptorangriff", "Brecher");
		Test1v1 tn = new Test1v1(kartenstapel);
		tn.spieler(klassen.gibKarte("Geist"), waffen.gibKarte("Spiegelsplitter"));
		StandardGegner gegner = new StandardGegner("TGegner", 8, 7, 6, 6, 10, 20);
		tn.gegner(gegner, waffen.gibKarte("Großer Stock"));
		tn.start(false);
		tn.beginneZug();
		Assert.assertTrue(tn.aktionskarte(0, MitWaffe.HW));
		tn.gegnerAktionskarten();
		Assert.assertTrue(tn.magieEffekte());
		tn.gesBerechnen();
		NTeilnehmer nt0 = tn.getSpieler();
		NTeilnehmer nt1 = tn.getGegner();
		Assert.assertEquals(24, nt0.getLeben());
		Assert.assertEquals(30, nt1.getLeben());
		tn.angriffe();
		Assert.assertEquals(0, tn.beendeZug());
		tn.aktionskartenAblegen();
		Assert.assertEquals(1, nt0.getMagie());
		Assert.assertEquals(1, nt1.getMagie());
		Assert.assertEquals(14, nt0.getLeben());
		Assert.assertEquals(21, nt1.getLeben());
		tn.beginneZug();
		Assert.assertTrue(tn.aktionskarte(3, MitWaffe.HW));
		tn.gegnerAktionskarten();
		Assert.assertTrue(tn.magieEffekte());
		tn.gesBerechnen();
		tn.angriffe();
		Assert.assertEquals(0, tn.beendeZug());
		tn.aktionskartenAblegen();
		Assert.assertEquals(2, nt0.getMagie());
		Assert.assertEquals(0, nt1.getMagie());
		Assert.assertEquals(8, nt0.getLeben());
		Assert.assertEquals(13, nt1.getLeben());
	}
}