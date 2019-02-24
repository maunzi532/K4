package main.nk2;

import java.util.*;
import java.util.stream.*;
import kampf.*;
import karten.*;
import logik.*;
import main.*;
import sprites.*;

public class NKampf2 implements XFenster
{
	public List<HeldMap> spieler;
	public int gegnerExpMin, gegnerExpMax;
	public List<Charakterkarte> bossgegner;
	public List<Gegner> gegner;
	public List<Waffenkarte> gegnerWaffen;
	public NKampf nKampf;
	public List<Waffenwechsel> waffenwechsel;
	public List<Aktionskarte> aktionskarten;
	public List<AktionAuswahl> aktionAuswahl;
	public Hauptklasse hk;
	public SpriteList spriteList;
	public NKDarstellung nkd;

	public NKampf2(Hauptklasse hk, List<HeldMap> spieler, List<Charakterkarte> bossgegner)
	{
		this.hk = hk;
		this.spieler = spieler;
		this.bossgegner = bossgegner;
	}

	public NKampf2(Hauptklasse hk, HeldMap spieler0, int gegnerExpMin, int gegnerExpMax)
	{
		this.hk = hk;
		spieler = List.of(spieler0);
		this.gegnerExpMin = gegnerExpMin;
		this.gegnerExpMax = gegnerExpMax;
	}

	public void initSpriteList(SpriteList sl0)
	{
		spriteList = new SpriteList(sl0.planeFrame, 2);
		nkd = new NKDarstellung(spriteList, this);
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
			gegner = new ArrayList<>();
			Optional<Charakterkarte> k0 = hk.gegnerStapel.durchsucheAlle(e -> e.inBereich(gegnerExpMin, gegnerExpMax) >= 0);
			if(k0.isPresent())
			{
				Charakterkarte k1 = k0.get();
				if(k1 instanceof XGegnerKarte)
				{
					gegner.add(new Gegner((XGegnerKarte) k1, k1.inBereich(gegnerExpMin, gegnerExpMax)));
				}
				else
				{
					gegner.add(new Gegner(k1));
				}
			}
			else
			{
				throw new RuntimeException("Mehrere Gegner noch nicht implementiert");
			}
		}
		//ermittle Waffen für Gegner
		gegnerWaffen = new ArrayList<>();
		for(Gegner gegner1 : gegner)
		{
			int waffenwert = gegner1.charakterkarte().getWaffenwert();
			Optional<Waffenkarte> k0 = hk.waffenStapel.durchsucheAlle(e -> e.isGegnerOK() && (e.getKosten() == waffenwert || e.getKosten() == waffenwert - 1));
			if(k0.isPresent())
			{
				gegnerWaffen.add(k0.get());
			}
			else
			{
				throw new RuntimeException("Gegner hat keine Waffe");
			}
		}
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
		waffenwechsel = spieler.stream().map(e -> e.erstelleWaffenwechsel(hk.teamItems)).collect(Collectors.toList());
	}

	public void k1()
	{
		for(int i = 0; i < waffenwechsel.size(); i++)
		{
			waffenwechsel.get(i).anwenden(spieler.get(i));
			waffenwechsel.get(i).anwenden(nKampf.getAlleSpieler().get(i));
		}
		nKampf.anfangstrigger();
		aktionskarten = new ArrayList<>();
		z0();
	}

	public void z0()
	{
		nKampf.beginneZug();
		while(aktionskarten.size() < 3 + spieler.size())
		{
			Aktionskarte k = hk.aktionenStapel.erhalteKarte();
			if(k == null)
				break;
			aktionskarten.add(k);
		}
		//Erstelle Aktion Auswahl
	}

	public void z1()
	{

	}

	public void los()
	{
		/*Held klasseChar = new Held(Klasse.G, setV2Klassen, setV2Waffen);
		Waffenkarte heldWaffe = setV2Waffen.gibKarte("Spiegelsplitter");
		Charakterkarte gegner = setV2Gegner.gibKarte("TGegner");
		Waffenkarte gegnerWaffe = setV2Waffen.gibKarte("Großer Stock");

		NCharakter nc0 = new NCharakter(klasseChar.charakterkarte());
		NWaffe nw0 = new NWaffe(heldWaffe);
		NCharakter nc1 = new NCharakter(gegner);
		NWaffe nw1 = new NWaffe(gegnerWaffe);

		NTeilnehmer nt0 = new NTeilnehmer(0, null, nc0, nw0, null,
				List.of(heldWaffe), klasseChar.charakterkarte().getLeben() * 3);
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
		System.out.println(wugu.bilderReihe(List.of(klasseChar.charakterkarte(), heldWaffe, nt0.getAktionKarte(), gegner,
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
		System.out.println(wugu.bilderReihe(List.of(klasseChar.charakterkarte(), heldWaffe, nt0.getAktionKarte(), gegner,
				gegnerWaffe, nt1.getAktionKarte())));
		nKampf.magieZahlen();
		nKampf.zugV();
		nKampf.angriffe();
		nKampf.beendeZug();*/
	}

	@Override
	public void handleInput(int input)
	{

	}

	@Override
	public SpriteList getSpriteList()
	{
		return spriteList;
	}

	@Override
	public void update()
	{

	}
}