package k5.kampf;

import k5.charakter.*;
import java.util.*;
import k5.karten.*;
import k5.kartenset.*;
import org.junit.*;
import k5.sets.*;

public class NKampfTest
{
	private SetV2Aktionen setV2Aktionen;
	private SetV2Waffen setV2Waffen;
	private SetV2Klassen setV2Klassen;
	private SetV2Gegner setV2Gegner;

	private KarteBild wugu;

	@Before
	public void init()
	{
		setV2Aktionen = new SetV2Aktionen();
		setV2Waffen = new SetV2Waffen();
		setV2Klassen = new SetV2Klassen();
		setV2Gegner = new SetV2Gegner();
		wugu = new KarteBild(17, 7, 5, 10);
	}

	@Test
	public void test()
	{
		KlasseMitLevels held = new KlasseMitLevels(Klasse.G, setV2Klassen);
		Waffenkarte heldWaffe = setV2Waffen.gibKarte("Spiegelsplitter");
		Charakterkarte gegner = new Charakterkarte("TGegner", 8, 7, 6, 6, 10);
		Waffenkarte gegnerWaffe = setV2Waffen.gibKarte("Großer Stock");

		NTeilnehmer nt0 = new NTeilnehmer(0, held.charakterkarte(), heldWaffe,
				null, held.charakterkarte().getLeben() * 3);
		NTeilnehmer nt1 = new NTeilnehmer(-1, gegner, gegnerWaffe,
				null, gegner.getLeben() * 3);

		NKampf nKampf = new NKampf(List.of(nt0), List.of(nt1));
		nKampf.start();
		nKampf.nachInitialWaffenwechsel();
		nKampf.anfangstrigger();
		nKampf.beginneZug();
		Assert.assertTrue(nKampf.aktionskarte(nt0, setV2Aktionen.gibKarte("Schnellangriff"), W.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt1, setV2Aktionen.gibKarte("Powerangriff"), W.HW, nt0));
		nKampf.gegnerAktionskarten();
		System.out.println(wugu.bilderReihe(List.of(held.charakterkarte(), heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte())));
		nKampf.magieZahlen();
		nKampf.zugV();
		Assert.assertEquals(24, nt0.getLeben());
		Assert.assertEquals(30, nt1.getLeben());
		nKampf.angriffe();
		nKampf.beendeZug();
		Assert.assertEquals(1, nt0.getMagie());
		Assert.assertEquals(1, nt1.getMagie());
		Assert.assertEquals(14, nt0.getLeben());
		Assert.assertEquals(27, nt1.getLeben());
		nKampf.beginneZug();
		Assert.assertFalse(nKampf.aktionskarte(nt0, setV2Aktionen.gibKarte("Disruptorangriff"), W.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt0, setV2Aktionen.gibKarte("Schutzangriff"), W.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt1, setV2Aktionen.gibKarte("Brecher"), W.HW, nt0));
		nKampf.gegnerAktionskarten();
		System.out.println(wugu.bilderReihe(List.of(held.charakterkarte(), heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte())));
		nKampf.magieZahlen();
		nKampf.zugV();
		nKampf.angriffe();
		nKampf.beendeZug();
		Assert.assertEquals(2, nt0.getMagie());
		Assert.assertEquals(0, nt1.getMagie());
		Assert.assertEquals(8, nt0.getLeben());
		Assert.assertEquals(21, nt1.getLeben());
	}
}