package kampf;

import charakter.*;
import java.util.*;
import java.util.stream.*;
import karten.*;
import kartenset.*;

public class NKampf2
{
	public List<HeldMap> spieler;
	public int gegnerExpMin, gegnerExpMax;
	public List<Charakterkarte> bossgegner;
	public List<Gegner> gegner;
	public List<Waffenkarte> gegnerWaffen;
	public NKampf nKampf;
	public TeamItems teamItems;
	public Kartenstapel<Aktionskarte> aktionenStapel;
	public Kartenstapel<Charakterkarte> gegnerStapel;
	public Kartenstapel<Waffenkarte> waffenStapel;

	public NKampf2(List<HeldMap> spieler, List<Charakterkarte> bossgegner)
	{
		this.spieler = spieler;
		this.bossgegner = bossgegner;
	}

	public NKampf2(HeldMap spieler0, int gegnerExpMin, int gegnerExpMax)
	{
		spieler = List.of(spieler0);
		this.gegnerExpMin = gegnerExpMin;
		this.gegnerExpMax = gegnerExpMax;
	}

	public void ermittleGegner()
	{
		if(bossgegner != null)
		{
			gegner = bossgegner.stream().map(Gegner::new).collect(Collectors.toList());
		}
		else
		{
			//ermittle Gegner
		}
		//ermittle Waffen für Gegner
	}

	public void erstelle()
	{
		List<NTeilnehmer> nts = spieler.stream().map(HeldMap::erstelleNTeilnehmer).collect(Collectors.toList());
		List<NTeilnehmer> ntg = new ArrayList<>();
		for(int i = 0; i < gegner.size(); i++)
		{
			ntg.add(new NTeilnehmer(-1, gegner.get(i).charakterkarte(), gegnerWaffen.get(i), null));
		}
		nKampf = new NKampf(nts, ntg);
	}

	public void k0()
	{
		nKampf.start();
	}

	public void k1()
	{
		nKampf.anfangstrigger();
		z0();
	}

	public void z0()
	{
		nKampf.beginneZug();
	}

	public void los()
	{
		/*Held held = new Held(Klasse.G, setV2Klassen, setV2Waffen);
		Waffenkarte heldWaffe = setV2Waffen.gibKarte("Spiegelsplitter");
		Charakterkarte gegner = setV2Gegner.gibKarte("TGegner");
		Waffenkarte gegnerWaffe = setV2Waffen.gibKarte("Großer Stock");

		NCharakter nc0 = new NCharakter(held.charakterkarte());
		NWaffe nw0 = new NWaffe(heldWaffe);
		NCharakter nc1 = new NCharakter(gegner);
		NWaffe nw1 = new NWaffe(gegnerWaffe);

		NTeilnehmer nt0 = new NTeilnehmer(0, null, nc0, nw0, null,
				List.of(heldWaffe), held.charakterkarte().getLeben() * 3);
		NTeilnehmer nt1 = new NTeilnehmer(0, null, nc1, nw1, null,
				List.of(gegnerWaffe), gegner.getLeben() * 3);

		nKampf = new NKampf(List.of(nt0), List.of(nt1));
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
		nKampf.angriffe();
		nKampf.beendeZug();
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
		nKampf.beendeZug();*/
	}
}