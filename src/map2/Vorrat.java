package map2;

import dungeonmap.karte.*;
import dungeonmap.map.*;
import effektkarten.effekte.ziel.MitWaffe;
import effektkarten.karten.*;
import java.util.*;
import java.util.stream.*;
import kampf.*;
import logik.*;
import main.*;
import stapelkarten.*;

public final class Vorrat
{
	public final Einstellungen e;
	public final KartenMap map;
	public final List<Spieler> spielerListe;
	public final List<WaffeMap> waffen;
	public final Map<ExpTrank, Integer> traenke;
	public int unaufgeteilteExp;
	public int bossgegnerBesiegt;
	public int waendeBeworfen;
	public int anzahlHaendler;
	public boolean haendlerBereit;
	public List<WaffeMap> haendlerAngebot;
	public final List<Integer> beiHaendler;

	public Vorrat(Einstellungen e)
	{
		this.e = e;
		map = new KartenMap(e);
		spielerListe = new ArrayList<>();
		waffen = new ArrayList<>();
		traenke = new HashMap<>();
		unaufgeteilteExp = 0;
		bossgegnerBesiegt = 0;
		waendeBeworfen = 0;
		anzahlHaendler = 0;
		haendlerBereit = true;
		beiHaendler = new ArrayList<>();
	}

	public void erstelleSpielfiguren()
	{
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			Spieler spieler = new Spieler(i);
			spieler.spielfigur = new Spielfigur(map, map.startPosition());
			spielerListe.add(spieler);
		}
	}

	public void erstelleHeld(Held2 h1, int num)
	{
		spielerListe.get(num).held = h1;
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

	public List<AktiverGegner> zieheGegner(int minExp, int maxExp, int startAnzahl, Kartenstapel<Gegner> gegnerKartenstapel, Random r)
	{
		for(int anzahl = startAnzahl; anzahl < startAnzahl + e.gegnerAnzahlZiehenVersuche; anzahl++)
		{
			List<Gegner> gegner1 = zieheGegnerAnzahl(minExp, maxExp, anzahl, gegnerKartenstapel);
			if(gegner1 != null)
				return xWerteBestimmen(gegner1, minExp, maxExp, r);
		}
		throw new RuntimeException("Gegner ziehen unmöglich (Exp: " + minExp + '-' + maxExp
		                           + " Anzahl: " + startAnzahl + '-' + (startAnzahl + e.gegnerAnzahlZiehenVersuche - 1) + ')');
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

	public List<AktiverGegner> xWerteBestimmen(List<? extends Gegner> gegner1, int minExp, int maxExp, Random r)
	{
		List<AktiverGegner> gegner = new ArrayList<>();
		int minExpA = minExp;
		int maxExpA = maxExp;
		int minExpK = gegner1.stream().mapToInt(Gegner::minExp).sum();
		int maxExpK = gegner1.stream().mapToInt(Gegner::maxExp).sum();
		for(Gegner g : gegner1)
		{
			if(g instanceof XGegner gx)
			{
				minExpK -= gx.minExp();
				maxExpK -= gx.maxExp();
				int minExpK1 = minExpK;
				int maxExpK1 = maxExpK;
				int minExpA1 = minExpA;
				int maxExpA1 = maxExpA;
				List<Integer> moeglich = IntStream.range(0, XGegner.X_ANZAHL)
						.filter(f -> maxExpK1 + gx.xExp()[f] >= minExpA1 && minExpK1 + gx.xExp()[f] <= maxExpA1).boxed().collect(Collectors.toList());
				int x = moeglich.get(moeglich.size() > 1 ? r.nextInt(moeglich.size()) : 0);
				minExpA -= gx.xExp()[x];
				maxExpA -= gx.xExp()[x];
				gegner.add(new AktiverGegner(gx, gx.erstelleCharakterkarte(x)));
			}
			else if(g instanceof StandardGegner gs)
			{
				Charakterkarte ch = gs.charakterkarte();
				minExpA -= ch.exp();
				maxExpA -= ch.exp();
				minExpK -= ch.exp();
				maxExpK -= ch.exp();
				gegner.add(new AktiverGegner(gs, ch));
			}
		}
		return gegner;
	}

	public List<NTeilnehmer> waffenkartenZiehen(List<Charakterkarte> gegner, Kartenstapel<Waffenkarte> waffenKartenstapel)
	{
		List<NTeilnehmer> l = new ArrayList<>();
		for(Charakterkarte g : gegner)
		{
			int waffenwert = g.waffenwert();
			Waffenkarte w1 = waffenKartenstapel.durchsucheAlle(f -> gegnerOK(f, waffenwert)).orElseThrow();
			l.add(new NTeilnehmer(e, g, w1, null));
		}
		return l;
	}

	private boolean gegnerOK(Waffenkarte karte, int waffenwert)
	{
		return karte.kosten() >= waffenwert + e.gegnerWaffenwertMin && karte.kosten() <= waffenwert + e.gegnerWaffenwertMax;
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

	public void haendlerBeenden(int num, Kartenstapel<? super Waffenkarte> waffenKartenstapel)
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
		int min = beiHaendler.stream().mapToInt(f -> spielerListe.get(f).held().upgradeHeld.charakterkarte().waffenwert()).min().orElseThrow() + e.gegnerWaffenwertMin;
		int max = beiHaendler.stream().mapToInt(f -> spielerListe.get(f).held().upgradeHeld.charakterkarte().waffenwert()).max().orElseThrow() + e.gegnerWaffenwertMax;
		int anzahl = e.basisHaendlerAuswahl + e.extraHaendlerAuswahlProHaendler * anzahlHaendler;
		for(int i = 0; i < anzahl; i++)
		{
			waffenKartenstapel.durchsucheAlle(f -> f.kosten() >= min && f.kosten() <= max);
		}
	}

	public void waffeAblegen(int numH, MitWaffe mitWaffe)
	{
		if(mitWaffe == MitWaffe.HW)
			spielerListe.get(numH).held().hauptwaffe = null;
		if(mitWaffe == MitWaffe.NW)
			spielerListe.get(numH).held().nebenwaffe = null;
	}

	public void waffeAusruesten(int numH, MitWaffe mitWaffe, int numW)
	{
		if(mitWaffe == MitWaffe.HW)
			spielerListe.get(numH).held().hauptwaffe = waffen.get(numW);
		if(mitWaffe == MitWaffe.NW)
			spielerListe.get(numH).held().nebenwaffe = waffen.get(numW);
	}

	public void waffenTauschen(int numH)
	{
		spielerListe.get(numH).held().tauscheWaffen();
	}

	public List<Integer> unverwendeteWaffen()
	{
		return IntStream.range(0, waffen.size()).filter(this::unverwendet).boxed().collect(Collectors.toList());
	}

	public boolean unverwendet(int numW)
	{
		WaffeMap w1 = waffen.get(numW);
		return spielerListe.stream().noneMatch(s -> s.held().hauptwaffe == w1 || s.held().nebenwaffe == w1);
	}

	public void forsche(MapKartenPosition mkp, Kartenstapel<MapKarte> mapStapel)
	{
		map.forsche(mkp, mapStapel);
		spielerListe.forEach(s -> s.spielfigur().erstelleBewegungsgraph());
	}
}