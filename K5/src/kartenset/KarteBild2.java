package kartenset;

import effekt.*;
import java.util.*;
import java.util.stream.*;
import karten.*;

public class KarteBild2
{
	private int breiteInnen;
	private int breiteWerte;
	private int hoeheWerte;
	private int hoeheEffekte;

	public KarteBild2()
	{
		breiteInnen = 23;
		breiteWerte = 10;
		hoeheWerte = 5;
		hoeheEffekte = 12;
	}

	public int hoehe()
	{
		return hoeheWerte + hoeheEffekte + 5;
	}

	public int breite()
	{
		return breiteInnen + 4;
	}

	public String karteBild(Karte karte)
	{
		StringBuilder sb = new StringBuilder();
		leereZeile(sb, '╔', '═', '╗');
		//"─━│┃┄┅┆┇┈┉┊┋┌┍┎┏┐┑┒┓└┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄╅╆╇╈╉╊╋╌╍╎╏═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬╭╮╯╰╱╲╳╴╵╶╷╸╹╺╻╼╽╾╿"
		//"▪▫▬▭▮▯▰▱▲△▴▵▶▷▸▹►▻▼▽▾▿◀◁◂◃◄◅◆◇◈◉◊○◌◍◎●◐◑◒◓◔◕◖◗◘◙◚◛◜◝◞◟◠◡◢◣◤◥◦◧◨◩◪◫◬◭◮◯◰◱◲◳◴◵◶◷◸◹◺◻◼◽◾◿☀☁☂☃☄★☆☇☈☉☊☋☌☍☎☏☐☑☒☓☔☕"
		textZeile(sb, karte.getName());
		leereZeileM(sb, '╟', '─', '┬', '╢');
		List<String> werte = karte.werte();
		for(int i = 0; i * 2 + 1 < werte.size(); i++)
			geteilteZeile(sb, werte.get(i * 2), werte.get(i * 2 + 1));
		for(int i = werte.size() / 2; i < hoeheWerte; i++)
			geteilteZeile(sb, "", "");
		leereZeileM(sb, '╟', '─', '┴', '╢');
		int klassencode = karte.klassencode();
		if(klassencode >= 0)
		{
			klassenZeile(sb, klassencode);
			leereZeile(sb, '╟', '─', '╢');
		}
		List<KartenEffekt> effekte = karte.effekte();
		int effektZeilen = 0;
		for(int i = 0; i < effekte.size(); i++)
			effektZeilen += effektTeilen(sb, effekte.get(i));
		for(int i = effektZeilen + (klassencode >= 0 ? 2 : 0); i < hoeheEffekte; i++)
			leereZeile(sb, '║', ' ', '║');
		leereZeile(sb, '╚', '═', '╝');
		return sb.toString();
	}

	private void leereZeile(StringBuilder sb, char links, char innen, char rechts)
	{
		sb.append(links);
		for(int i = 0; i < breiteInnen + 2; i++)
			sb.append(innen);
		sb.append(rechts).append('\n');
	}

	private void leereZeileM(StringBuilder sb, char links, char innen, char mitte, char rechts)
	{
		sb.append(links);
		for(int i = 0; i < breiteWerte + 2; i++)
			sb.append(innen);
		sb.append(mitte);
		for(int i = 0; i < breiteInnen - breiteWerte - 1; i++)
			sb.append(innen);
		sb.append(rechts).append('\n');
	}

	private void textZeile(StringBuilder sb, String text)
	{
		if(text.length() > breiteInnen + 2)
			text = text.replace("Fortgeschrittener", "Fortges.");
		if(text.length() > breiteInnen + 2)
			throw new RuntimeException("Name zu lang: " + text);
		sb.append('║');
		if(text.length() <= breiteInnen + 1)
			sb.append(' ');
		sb.append(text);
		for(int i = text.length(); i < breiteInnen + 1; i++)
			sb.append(' ');
		sb.append("║\n");
	}

	private void geteilteZeile(StringBuilder sb, String t0, String t1)
	{
		sb.append("║ ");
		sb.append(t0);
		for(int i = t0.length() + t1.length(); i < breiteWerte; i++)
			sb.append(' ');
		sb.append(t1);
		sb.append(" │ ");
		for(int i = breiteWerte + 3; i < breiteInnen; i++)
			sb.append(' ');
		sb.append(" ║\n");
	}

	private void klassenZeile(StringBuilder sb, int klassencode)
	{
		Klasse[] klasse = Klasse.values();
		int l0 = klasse.length * 2 - 1;
		int l1 = (breiteInnen - l0) / 2;
		sb.append('║');
		for(int i = 0; i < l1; i++)
			sb.append(' ');
		if(klassencode == Karte.alleKlassen)
		{
			sb.append("Alle");
			for(int i = l1 + 3; i < breiteInnen; i++)
				sb.append(' ');
		}
		else
		{
			for(int i = 0; i < klasse.length; i++)
				sb.append(' ').append(((klassencode >> (klasse.length - 1 - i)) & 1) > 0 ? klasse[i] : " ");
			for(int i = l1 + l0; i < breiteInnen; i++)
				sb.append(' ');
		}
		sb.append(" ║\n");
	}

	private int effektTeilen(StringBuilder sb, KartenEffekt kartenEffekt)
	{
		if(kartenEffekt == null)
			return 0;
		int zeilen = 1;
		int l0 = kartenEffekt.getTyp() == null ? 0 : kartenEffekt.getTyp().length();
		int lt = breiteInnen - l0;
		String text = kartenEffekt.getText();
		while(text.length() > lt)
		{
			int l1 = text.substring(0, lt + 1).lastIndexOf(" ");
			sb.append("║ ");
			if(zeilen <= 1)
			{
				if(kartenEffekt.getTyp() != null)
					sb.append(kartenEffekt.getTyp());
			}
			else
			{
				for(int i = 0; i < l0; i++)
					sb.append(' ');
			}
			if(l1 >= 0)
				entEffektText(sb, text.substring(0, l1));
			else
				throw new RuntimeException("Text zu lang: " + text);
			for(int i = l1; i < lt; i++)
				sb.append(' ');
			sb.append(" ║\n");
			text = text.substring(l1 + 1);
			zeilen++;
		}
		sb.append("║ ");
		if(zeilen <= 1)
		{
			if(kartenEffekt.getTyp() != null)
				sb.append(kartenEffekt.getTyp());
		}
		else
		{
			for(int i = 0; i < l0; i++)
				sb.append(' ');
		}
		entEffektText(sb, text);
		for(int i = text.length(); i < lt; i++)
			sb.append(' ');
		sb.append(" ║\n");
		return zeilen;
	}

	private void entEffektText(StringBuilder sb, String text)
	{
		sb.append(text.replace('_', ' '));
	}

	public String[] karteBildA(Karte karte)
	{
		return karteBild(karte).split("\n");
	}

	public String bilderReihe(List<Karte> karten)
	{
		return felderReihe(karten.stream().map(this::karteBild).collect(Collectors.toList()));
	}

	public String bilderReihe2(List<Karte> karten, int max)
	{
		Collection<List<Integer>> a = IntStream.range(0, karten.size()).boxed().collect(Collectors.groupingBy(e -> e / max)).values();
		return a.stream().map(e -> e.stream().map(k -> karteBild(karten.get(k))).collect(Collectors.toList()))
				.map(this::felderReihe).reduce((s, s2) -> s + "\n\n" + s2).orElse("");
	}

	public String felderReihe(List<String> felder)
	{
		List<String[]> zeilen0 = felder.stream().map(e -> e.replace("\t", "    ").split("\n")).collect(Collectors.toList());
		int maxH = zeilen0.stream().mapToInt(e -> e.length).max().orElse(0);
		int[] kartenW = new int[zeilen0.size()];
		for(int i = 0; i < zeilen0.size(); i++)
		{
			kartenW[i] = Arrays.stream(zeilen0.get(i)).mapToInt(String::length).max().orElse(0) + 1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < maxH; i++)
		{
			for(int j = 0; j < zeilen0.size(); j++)
			{
				int w0 = 0;
				if(i < zeilen0.get(j).length)
				{
					String a0 = zeilen0.get(j)[i];
					w0 = a0.length();
					sb.append(a0);
				}
				for(int k = w0; k < kartenW[j]; k++)
				{
					sb.append(' ');
				}
			}
			sb.append('\n');
		}
		sb.append('\n');
		return sb.toString();
	}
}