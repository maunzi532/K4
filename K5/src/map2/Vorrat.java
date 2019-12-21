package map2;

import dungeonmap.*;
import effektkarten.effekte.eigenschaften.*;
import java.util.*;
import java.util.stream.*;
import kampf.*;
import effektkarten.karten.*;
import logik.*;
import main.*;

public class Vorrat
{
	public Einstellungen e;
	public DungeonMap map;
	public List<Spielfigur> spielfiguren;
	public List<Held2> helden;
	public List<WaffeMap> waffen;
	public Map<ExpTrank, Integer> traenke;
	public int unaufgeteilteExp;
	public int bossgegnerBesiegt;
	public int waendeBeworfen;
	public int anzahlHaendler;
	public boolean haendlerBereit;
	public List<WaffeMap> haendlerAngebot;
	public List<Integer> beiHaendler;

	public Vorrat(Einstellungen e)
	{
		this.e = e;
		map = new DungeonMap(e);
		spielfiguren = new ArrayList<>();
		helden = new ArrayList<>();
		waffen = new ArrayList<>();
		traenke = new HashMap<>();
		unaufgeteilteExp = 0;
		bossgegnerBesiegt = 0;
		waendeBeworfen = 0;
		anzahlHaendler = 0;
		haendlerBereit = true;
		beiHaendler = new ArrayList<>();
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			spielfiguren.add(map.erstelleSpielfigur());
			helden.add(null);
		}
	}

	public void erstelleHeld(Held2 h1, int num)
	{
		helden.set(num, h1);
		if(h1.hauptwaffe != null)
			waffen.add(h1.hauptwaffe);
		if(h1.nebenwaffe != null)
			waffen.add(h1.nebenwaffe);
		erhalteTraenke(e.traenkeProSpieler, e.trankExp.get(0));
	}

	public void erhalteTraenke(int anzahl, int exp)
	{
		ExpTrank expTrank = new ExpTrank(exp);
		if(traenke.containsKey(expTrank))
		{
			traenke.put(expTrank, traenke.get(expTrank) + anzahl);
		}
		else
		{
			traenke.put(expTrank, anzahl);
		}
	}

	public List<Charakterkarte> zieheGegner(int minExp, int maxExp, int startAnzahl, Kartenstapel<Gegner> gegnerKartenstapel, Random r)
	{
		for(int anzahl = startAnzahl; anzahl < startAnzahl + e.gegnerAnzahlZiehenVersuche; anzahl++)
		{
			List<Gegner> gegner1 = zieheGegnerAnzahl(minExp, maxExp, anzahl, gegnerKartenstapel);
			if(gegner1 != null)
				return xWerteBestimmen(gegner1, minExp, maxExp, r);
		}
		throw new RuntimeException();
	}

	public List<Gegner> zieheGegnerAnzahl(int minExp, int maxExp, int anzahl, Kartenstapel<Gegner> gegnerKartenstapel)
	{
		List<Gegner> gegner1 = new ArrayList<>();
		int minExpN = minExp;
		int maxExpN = maxExp;
		for(int noch = anzahl; noch > 0; noch--)
		{
			int minExp1 = minExpN / noch;
			int maxExp1 = -Math.floorDiv(-maxExpN, noch);
			Optional<Gegner> gegnerKarte = gegnerKartenstapel.durchsucheAlle(karte -> karte.maxExp() >= minExp1 && karte.minExp() <= maxExp1);
			if(gegnerKarte.isPresent())
			{
				Gegner karte1 = gegnerKarte.get();
				minExpN -= karte1.maxExp();
				maxExpN -= karte1.minExp();
				gegner1.add(karte1);
			}
			else
			{
				gegner1.forEach(gegnerKartenstapel::ablage);
				return null;
			}
		}
		return gegner1;
	}

	public List<Charakterkarte> xWerteBestimmen(List<Gegner> gegner1, int minExp, int maxExp, Random r)
	{
		List<Charakterkarte> gegner = new ArrayList<>();
		int minExpA = minExp;
		int maxExpA = maxExp;
		int minExpK = gegner1.stream().mapToInt(Gegner::minExp).sum();
		int maxExpK = gegner1.stream().mapToInt(Gegner::maxExp).sum();
		for(Gegner g : gegner1)
		{
			if(g instanceof XGegner)
			{
				XGegner gx = (XGegner) g;
				minExpK -= gx.minExp();
				maxExpK -= gx.maxExp();
				int minExpK1 = minExpK;
				int maxExpK1 = maxExpK;
				int minExpA1 = minExpA;
				int maxExpA1 = maxExpA;
				List<Integer> moeglich = IntStream.range(0, XGegner.X_ANZAHL)
						.filter(f -> maxExpK1 + gx.xExp[f] >= minExpA1 && minExpK1 + gx.xExp[f] <= maxExpA1).boxed().collect(Collectors.toList());
				int x = moeglich.get(moeglich.size() > 1 ? r.nextInt(moeglich.size()) : 0);
				minExpA -= gx.xExp[x];
				maxExpA -= gx.xExp[x];
				gegner.add(gx.erstelleCharakterkarte(x));
			}
			else if(g instanceof StandardGegner)
			{
				StandardGegner gs = (StandardGegner) g;
				minExpA -= gs.exp;
				maxExpA -= gs.exp;
				minExpK -= gs.exp;
				maxExpK -= gs.exp;
				gegner.add(gs.charakterkarte);
			}
		}
		return gegner;
	}

	public List<NTeilnehmer> waffenkartenZiehen(List<Charakterkarte> gegner, Kartenstapel<Waffenkarte> waffenKartenstapel)
	{
		List<NTeilnehmer> l = new ArrayList<>();
		for(Charakterkarte g : gegner)
		{
			int waffenwert = g.getWaffenwert();
			Waffenkarte w1 = waffenKartenstapel.durchsucheAlle(f -> gegnerOK(f, waffenwert)).orElseThrow();
			l.add(new NTeilnehmer(e, g, w1, null));
		}
		return l;
	}

	private boolean gegnerOK(Waffenkarte karte, int waffenwert)
	{
		return karte.getKosten() >= waffenwert + e.gegnerWaffenwertMin && karte.getKosten() <= waffenwert + e.gegnerWaffenwertMax;
	}

	public boolean haendlerBetreten(int num)
	{
		if(beiHaendler.contains(num))
			return false;
		if(!haendlerBereit && beiHaendler.isEmpty())
			return false;
		if(beiHaendler.isEmpty())
			haendlerBereit = false;
		beiHaendler.add(num);
		return true;
	}

	public void haendlerBeenden(int num, Kartenstapel<Waffenkarte> waffenKartenstapel)
	{
		beiHaendler.remove((Integer) num);
		if(beiHaendler.isEmpty())
		{
			haendlerAngebot.forEach(k -> waffenKartenstapel.ablage(k.karte));
			haendlerAngebot.clear();
		}
	}

	public void haendlerAngebot(Kartenstapel<Waffenkarte> waffenKartenstapel)
	{
		int min = beiHaendler.stream().mapToInt(f -> helden.get(f).upgradeHeld.charakterkarte().getWaffenwert()).min().orElseThrow() + e.gegnerWaffenwertMin;
		int max = beiHaendler.stream().mapToInt(f -> helden.get(f).upgradeHeld.charakterkarte().getWaffenwert()).max().orElseThrow() + e.gegnerWaffenwertMax;
		int anzahl = e.basisHaendlerAuswahl + e.extraHaendlerAuswahlProHaendler * anzahlHaendler;
		for(int i = 0; i < anzahl; i++)
		{
			waffenKartenstapel.durchsucheAlle(f -> f.getKosten() >= min && f.getKosten() <= max);
		}
	}

	public void waffeAblegen(int numH, MitWaffe mitWaffe)
	{
		if(mitWaffe == MitWaffe.HW)
			helden.get(numH).hauptwaffe = null;
		if(mitWaffe == MitWaffe.NW)
			helden.get(numH).nebenwaffe = null;
	}

	public void waffeAusruesten(int numH, MitWaffe mitWaffe, int numW)
	{
		if(mitWaffe == MitWaffe.HW)
			helden.get(numH).hauptwaffe = waffen.get(numW);
		if(mitWaffe == MitWaffe.NW)
			helden.get(numH).nebenwaffe = waffen.get(numW);
	}

	public void waffenTauschen(int numH)
	{
		helden.get(numH).tauscheWaffen();
	}

	public List<Integer> unverwendeteWaffen()
	{
		return IntStream.range(0, waffen.size()).filter(this::unverwendet).boxed().collect(Collectors.toList());
	}

	public boolean unverwendet(int numW)
	{
		WaffeMap w1 = waffen.get(numW);
		for(Held2 h : helden)
		{
			if(h.hauptwaffe == w1 || h.nebenwaffe == w1)
				return false;
		}
		return true;
	}
}