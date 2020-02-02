package effektkarten.kartebild;

import effektkarten.ansichtkarte.*;
import java.util.*;
import java.util.stream.*;

public class KarteBild3 implements Zeichner3
{
	//"─━│┃┄┅┆┇┈┉┊┋┌┍┎┏┐┑┒┓└┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄╅╆╇╈╉╊╋╌╍╎╏═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬╭╮╯╰╱╲╳╴╵╶╷╸╹╺╻╼╽╾╿"
	//"▪▫▬▭▮▯▰▱▲△▴▵▶▷▸▹►▻▼▽▾▿◀◁◂◃◄◅◆◇◈◉◊○◌◍◎●◐◑◒◓◔◕◖◗◘◙◚◛◜◝◞◟◠◡◢◣◤◥◦◧◨◩◪◫◬◭◮◯◰◱◲◳◴◵◶◷◸◹◺◻◼◽◾◿☀☁☂☃☄★☆☇☈☉☊☋☌☍☎☏☐☑☒☓☔☕"

	private final Laengen3 laengen;
	private final Rahmen3 rahmen2;
	private final Rahmen3 rahmen1;
	private final Klassen3 klassen;
	private final Ersetzen3 ersetzen;

	public KarteBild3()
	{
		laengen = new Laengen3();
		rahmen2 = new Rahmen3(2);
		rahmen1 = new Rahmen3(1);
		klassen = new Klassen3();
		ersetzen = new Ersetzen3();
	}

	public KarteBild3(Laengen3 laengen, Rahmen3 rahmen2, Rahmen3 rahmen1, Klassen3 klassen, Ersetzen3 ersetzen)
	{
		this.laengen = laengen;
		this.rahmen2 = rahmen2;
		this.rahmen1 = rahmen1;
		this.klassen = klassen;
		this.ersetzen = ersetzen;
	}

	@Override
	public int yw()
	{
		return laengen.karteH;
	}

	@Override
	public int xw()
	{
		return laengen.karteW;
	}

	public char[][] karteBild(BildKarte karte)
	{
		return karteBild(karte.name(), karte.klassencode() == -1 ? null : karte.klassencode(),
				karte.werteLO(), karte.werteLU(), karte.werteR(), karte.text());
	}

	public char[][] karteBild(String name, Integer klassencode, List<String> werteLO, List<String> werteLU, List<String> werteR, List<Kartentext> effektText)
	{
		char[][] bild = erstellen();
		rahmen(bild, klassencode != null);
		kartenname(bild, name);
		werte(bild, werteLO, werteLU, werteR);
		klassen(bild, klassencode);
		effekte(bild, klassencode != null, effektText);
		return bild;
	}

	private void rahmen(char[][] bild, boolean klassencode)
	{
		if(klassencode)
			rahmen(bild, rahmen2, 2, 3 + laengen.werteH, 5 + laengen.werteH);
		else
			rahmen(bild, rahmen2, 2, 3 + laengen.werteH);
		linieInnen(bild, rahmen1, 2, 3 + laengen.werteH, 3 + laengen.werteW);
	}

	private void werte(char[][] bild, List<String> werteLO, List<String> werteLU, List<String> werteR)
	{
		int platz = werteR == null ? laengen.werteW : laengen.werte1;
		if(werteLO != null)
		{
			for(int i = 0; i < werteLO.size() / 2; i++)
			{
				wertL(bild[3 + i], werteLO.get(i * 2),
						werteLO.get(i * 2 + 1), platz, werteR != null && werteR.size() > 1);
			}
		}
		if(werteLU != null)
		{
			for(int i = 0; i < werteLU.size() / 2; i++)
			{
				wertL(bild[3 + laengen.werteH - werteLU.size() / 2 + i], werteLU.get(i * 2),
						werteLU.get(i * 2 + 1), platz, werteR != null && werteR.size() > 1);
			}
		}
		if(werteR != null)
		{
			int ys1 = 5 - (werteR.size() - 1) / 2;
			for(int i = 0; i < werteR.size(); i++)
			{
				String w2 = werteR.get(i);
				int xs = laengen.werteW + 2 - w2.length();
				int sh = xs < platz + 3 ? 1 : 0;
				for(int j = 0; j < w2.length(); j++)
				{
					if(xs + sh + j >= platz + 3)
						bild[ys1 + i][xs + sh + j] = w2.charAt(j);
				}
			}
		}
	}

	private void wertL(char[] bildTeil, String w0, String w1, int platz, boolean kl)
	{
		if(kl && !w1.endsWith("+X"))
			platz -= 2;
		for(int j = 0; j < w0.length() && j < platz; j++)
		{
			bildTeil[2 + j] = w0.charAt(j);
		}
		int xs = 2 + platz - w1.length();
		for(int j = 0; j < w1.length(); j++)
		{
			if(xs + j >= 2)
				bildTeil[xs + j] = w1.charAt(j);
		}
	}

	private void kartenname(char[][] bild, String name)
	{
		if(name.length() > laengen.karteW - 2)
			name = ersetzen.ersetze(name);
		int start = name.length() >= laengen.karteW - 2 ? 1 : 2;
		for(int i = 0; i < name.length(); i++)
		{
			if(start + i < laengen.karteW - 1)
				bild[1][start + i] = name.charAt(i);
		}
	}

	private void klassen(char[][] bild, Integer klassencode)
	{
		if(klassencode != null)
		{
			if(klassencode == klassen.alleKlassenCode)
			{
				System.arraycopy(laengen.alleKlassen, 0, bild[laengen.werteH + 4],
						laengen.klassen2, laengen.alleKlassen.length);
			}
			else
			{
				int anz = klassen.anzahlKlassen;
				int halb = (laengen.karteW - 5) / 2;
				int abstand = halb / (anz / 2 - 1);
				for(int i = 0; i < anz; i++)
				{
					if(((klassencode >> (anz - 1 - i)) & 1) > 0)
						bild[laengen.werteH + 4][laengen.klassen1 + abstand * i] = klassen.klassenNamen[i].charAt(0);
				}
			}
		}
	}

	private void effekte(char[][] bild, boolean klassencode, List<Kartentext> effektText)
	{
		if(effektText != null)
		{
			int zeile = klassencode ? laengen.werteH + 6 : laengen.werteH + 4;
			for(Kartentext text : effektText)
			{
				String typ = text.typ();
				int start = 2 + typ.length();
				int platz = laengen.karteW - start - 2;
				String textAktuell = text.text();
				List<String> zeilen = new ArrayList<>();
				while(textAktuell.length() > platz)
				{
					int teilen = textAktuell.substring(0, platz + 1).lastIndexOf(" ");
					if(teilen >= 0)
						zeilen.add(textAktuell.substring(0, teilen));
					else
						throw new RuntimeException("Text zu lang: " + textAktuell);
					textAktuell = textAktuell.substring(teilen + 1);
				}
				zeilen.add(textAktuell);
				System.arraycopy(typ.toCharArray(), 0, bild[zeile], 2, typ.length());
				for(String teil : zeilen)
				{
					if(zeile < laengen.karteH - 1)
						System.arraycopy(teil.replace('_', ' ').toCharArray(), 0,
								bild[zeile], start, teil.length());
					zeile++;
				}
			}
		}
	}

	public char[][] zeile(List<char[][]> list)
	{
		char[][] re = new char[laengen.karteH][list.size() * laengen.karteW];
		for(int iy = 0; iy < laengen.karteH; iy++)
		{
			for(int ix = 0; ix < list.size(); ix++)
			{
				System.arraycopy(list.get(ix)[iy], 0, re[iy], ix * laengen.karteW, laengen.karteW);
			}
		}
		return re;
	}

	public String inZeilen(List<? extends BildKarte> karten, int zeilenlaenge)
	{
		Collection<List<Integer>> a = IntStream
				.range(0, karten.size()).boxed().collect(Collectors.groupingBy(e -> e / zeilenlaenge)).values();
		return a.stream().map(e -> e.stream().map(k -> karteBild(karten.get(k))).collect(Collectors.toList()))
				.map(this::zeile).map(e -> Arrays.stream(e).map(String::new).reduce((s, s2) -> s + '\n' + s2).orElseThrow())
				.reduce((s, s2) -> s + "\n\n" + s2).orElse("");
	}
}