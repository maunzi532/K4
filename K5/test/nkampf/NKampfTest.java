package nkampf;

import effekt.*;
import java.util.*;
import kampf.*;
import kartebild.*;
import karten.*;
import main.*;
import org.junit.*;
import sets.*;

public class NKampfTest
{
	private SetV2Aktionen setV2Aktionen;
	private SetV2Waffen setV2Waffen;
	private SetV2Klassen setV2Klassen;
	private SetV2Gegner setV2Gegner;
	private KarteBild3 karteBild3;

	@Before
	public void init()
	{
		setV2Aktionen = new SetV2Aktionen();
		setV2Waffen = new SetV2Waffen();
		setV2Klassen = new SetV2Klassen();
		setV2Gegner = new SetV2Gegner();
		karteBild3 = new KarteBild3();
	}

	@Test
	public void test()
	{
		Einstellungen e = new Einstellungen();
		Charakterkarte held = setV2Klassen.gibKarte("Geist");
		Waffenkarte heldWaffe = setV2Waffen.gibKarte("Spiegelsplitter");
		Charakterkarte gegner = new Charakterkarte("TGegner", 8, 7, 6, 6, 10);
		Waffenkarte gegnerWaffe = setV2Waffen.gibKarte("Großer Stock");

		NTeilnehmer nt0 = new NTeilnehmer(e, held, heldWaffe, null);
		NTeilnehmer nt1 = new NTeilnehmer(e, gegner, gegnerWaffe, null);

		NKampf nKampf = new NKampf(e, List.of(nt0), List.of(nt1));
		nKampf.start();
		nKampf.anfangstrigger();
		nKampf.beginneZug(null);
		Assert.assertTrue(nKampf.aktionskarte(nt0, setV2Aktionen.gibKarte("Schnellangriff"), W.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt1, setV2Aktionen.gibKarte("Powerangriff"), W.HW, nt0));
		//nKampf.gegnerAktionskarten();
		System.out.println(karteBild3.inZeilen(List.of(held, heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte()), 7));
		nKampf.magieZahlen();
		Assert.assertTrue(nKampf.magieEffektOptionenOK());
		nKampf.zugV();
		Assert.assertEquals(24, nt0.getLeben());
		Assert.assertEquals(30, nt1.getLeben());
		nKampf.angriffe();
		nKampf.beendeZug(null);
		Assert.assertEquals(1, nt0.getMagie());
		Assert.assertEquals(1, nt1.getMagie());
		Assert.assertEquals(14, nt0.getLeben());
		Assert.assertEquals(21, nt1.getLeben());
		nKampf.beginneZug(null);
		Assert.assertFalse(nKampf.aktionskarte(nt0, setV2Aktionen.gibKarte("Disruptorangriff"), W.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt0, setV2Aktionen.gibKarte("Schutzangriff"), W.HW, nt1));
		Assert.assertTrue(nKampf.aktionskarte(nt1, setV2Aktionen.gibKarte("Brecher"), W.HW, nt0));
		//nKampf.gegnerAktionskarten();
		System.out.println(karteBild3.inZeilen(List.of(held, heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte()), 7));
		nKampf.magieZahlen();
		Assert.assertTrue(nKampf.magieEffektOptionenOK());
		nKampf.zugV();
		nKampf.angriffe();
		nKampf.beendeZug(null);
		Assert.assertEquals(2, nt0.getMagie());
		Assert.assertEquals(0, nt1.getMagie());
		Assert.assertEquals(8, nt0.getLeben());
		Assert.assertEquals(13, nt1.getLeben());
	}
}