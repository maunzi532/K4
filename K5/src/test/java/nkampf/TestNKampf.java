package nkampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.karten.*;
import java.util.*;
import kampf.*;
import main.*;
import stapelkarten.*;

public class TestNKampf
{
	private List<NTeilnehmer> spieler0;
	private List<NTeilnehmer> gegner0;
	private Einstellungen e;
	private Kartenstapel<Aktionskarte> aktionen;
	private NKampf nk;

	public TestNKampf(Kartenstapel<Aktionskarte> aktionen)
	{
		spieler0 = new ArrayList<>();
		gegner0 = new ArrayList<>();
		e = new Einstellungen();
		this.aktionen = aktionen;
	}

	public void spieler(Charakterkarte charakterkarte, Waffenkarte hw)
	{
		spieler0.add(new NTeilnehmer(e, charakterkarte, hw, null));
	}

	public void spieler(Charakterkarte charakterkarte, Waffenkarte hw, Waffenkarte nw)
	{
		spieler0.add(new NTeilnehmer(e, charakterkarte, hw, nw));
	}

	public void gegner(StandardGegner gegner, Waffenkarte hw)
	{
		gegner0.add(new NTeilnehmer(e, gegner.charakterkarte, hw, null));
	}

	public void xGegner(XGegner gegner, int x, Waffenkarte hw)
	{
		gegner0.add(new NTeilnehmer(e, gegner.erstelleCharakterkarte(x), hw, null));
	}

	public void start(int... tauschen)
	{
		nk = new NKampf(e, spieler0, gegner0, aktionen);
		nk.start();
		for(int i = 0; i < tauschen.length; i++)
		{
			spieler0.get(tauschen[i]).waffenTauschen();
		}
		nk.anfangstrigger();
	}

	public void beginneZug()
	{
		nk.beginneZug();
	}

	public boolean aktionskarte(int spielerNum, int aktionNum, MitWaffe mit, int zielNum)
	{
		return nk.aktionskarte(spieler0.get(spielerNum), nk.aktionenOptionen().get(aktionNum), mit, gegner0.get(zielNum));
	}

	public void gegnerAktionskarten(int... ziele)
	{
		nk.gegnerAktionskarten((n, a) -> ziele[n]);
	}

	public boolean magieEffekte(int... nehmen)
	{
		nk.magieZahlen();
		for(int i = 0; i < nehmen.length; i += 2)
		{
			spieler0.get(nehmen[i]).magieEffektOptionen.get(nehmen[i + 1]).benutzen = true;
		}
		return nk.magieEffektOptionenOK();
	}

	public void gesBerechnen()
	{
		nk.zugV();
	}

	public void angriffe()
	{
		nk.angriffe();
	}

	public int beendeZug()
	{
		return nk.beendeZug();
	}

	public void aktionskartenAblegen(int... ablegen)
	{
		Arrays.stream(ablegen).mapToObj(nk.aktionenOptionen()::get).forEach(nk::aktionskarteAblegen);
	}

	public NTeilnehmer getSpieler(int num)
	{
		return spieler0.get(num);
	}

	public NTeilnehmer getGegner(int num)
	{
		return gegner0.get(num);
	}
}