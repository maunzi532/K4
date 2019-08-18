package map2;

import dungeonmap.*;
import java.util.*;
import java.util.stream.*;
import kampf.*;
import karten.*;
import kartenset.*;
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
		for(int i = 0; i < e.anzahlSpieler; i++)
		{
			spielfiguren.add(map.erstelleSpielfigur());
		}
	}

	public void erstelleHeld(Held2 h1)
	{
		helden.add(h1);
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

	public List<Gegner> zieheGegner(int minExp, int maxExp, int startAnzahl, Kartenstapel<Charakterkarte> gegnerKartenstapel, Random r)
	{
		for(int anzahl = startAnzahl; anzahl < startAnzahl + e.gegnerAnzahlVersuche; anzahl++)
		{
			List<Charakterkarte> gegner1 = zieheGegnerAnzahl(minExp, maxExp, anzahl, gegnerKartenstapel);
			if(gegner1 != null)
				return xWerteBestimmen(gegner1, minExp, maxExp, r);
		}
		throw new RuntimeException();
	}

	public List<Charakterkarte> zieheGegnerAnzahl(int minExp, int maxExp, int anzahl, Kartenstapel<Charakterkarte> gegnerKartenstapel)
	{
		List<Charakterkarte> gegner1 = new ArrayList<>();
		int minExpN = minExp;
		int maxExpN = maxExp;
		for(int noch = anzahl; noch > 0; noch--)
		{
			int minExp1 = minExpN / noch;
			int maxExp1 = -Math.floorDiv(-maxExpN, noch);
			Optional<Charakterkarte> gegnerKarte = gegnerKartenstapel.durchsucheAlle(karte -> karte.maxExp() >= minExp1 && karte.minExp() <= maxExp1);
			if(gegnerKarte.isPresent())
			{
				Charakterkarte karte1 = gegnerKarte.get();
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

	public List<Gegner> xWerteBestimmen(List<Charakterkarte> gegner1, int minExp, int maxExp, Random r)
	{
		List<Gegner> gegner = new ArrayList<>();
		int minExpA = minExp;
		int maxExpA = maxExp;
		int minExpK = gegner1.stream().mapToInt(Charakterkarte::minExp).sum();
		int maxExpK = gegner1.stream().mapToInt(Charakterkarte::maxExp).sum();
		for(Charakterkarte g : gegner1)
		{
			if(g instanceof XGegnerKarte)
			{
				XGegnerKarte gx = (XGegnerKarte) g;
				minExpK -= gx.minExp();
				maxExpK -= gx.maxExp();
				int minExpK1 = minExpK;
				int maxExpK1 = maxExpK;
				int minExpA1 = minExpA;
				int maxExpA1 = maxExpA;
				List<Integer> moeglich = IntStream.range(0, gx.xExp.length)
						.filter(f -> maxExpK1 + gx.xExp[f] >= minExpA1 && minExpK1 + gx.xExp[f] <= maxExpA1).boxed().collect(Collectors.toList());
				int x;
				if(moeglich.size() > 1)
					x = moeglich.get(r.nextInt(moeglich.size()));
				else
					x = moeglich.get(0);
				minExpA -= g.getExp(x);
				maxExpA -= g.getExp(x);
				gegner.add(new Gegner(gx, x));
			}
			else
			{
				minExpA -= g.minExp();
				maxExpA -= g.maxExp();
				minExpK -= g.minExp();
				maxExpK -= g.maxExp();
				gegner.add(new Gegner(g));
			}
		}
		return gegner;
	}

	public List<NTeilnehmer> waffenkartenZiehen(List<Gegner> gegner, Kartenstapel<Waffenkarte> waffenKartenstapel)
	{
		List<NTeilnehmer> l = new ArrayList<>();
		for(Gegner g : gegner)
		{
			Charakterkarte gegnerkarte = g.charakterkarte();
			int waffenwert = gegnerkarte.getWaffenwert();
			Waffenkarte w1 = waffenKartenstapel.durchsucheAlle(f -> gegnerOK(f, waffenwert)).orElseThrow();
			l.add(new NTeilnehmer(e, gegnerkarte, w1, null));
		}
		return l;
	}

	private boolean gegnerOK(Waffenkarte karte, int waffenwert)
	{
		return karte.getKosten() >= waffenwert + e.gegnerWaffenwertMin && karte.getKosten() <= waffenwert + e.gegnerWaffenwertMax;
	}
}