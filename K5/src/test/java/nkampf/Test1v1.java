package nkampf;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.karten.*;
import java.util.*;
import kampf.*;
import main.*;
import stapelkarten.*;

public class Test1v1
{
	private NTeilnehmer spieler;
	private NTeilnehmer gegner;
	private final Einstellungen e;
	private final Kartenstapel<Aktionskarte> aktionen;
	private NKampf nk;

	public Test1v1(Einstellungen e, Kartenstapel<Aktionskarte> aktionen)
	{
		this.e = e;
		this.aktionen = aktionen;
	}

	public void spieler(Charakterkarte charakterkarte, Waffenkarte hw)
	{
		spieler = new NTeilnehmer(e, charakterkarte, hw, null);
	}

	public void spieler(Charakterkarte charakterkarte, Waffenkarte hw, Waffenkarte nw)
	{
		spieler = new NTeilnehmer(e, charakterkarte, hw, nw);
	}

	public void gegner(StandardGegner gegner1, Waffenkarte hw)
	{
		gegner = new NTeilnehmer(e, gegner1.charakterkarte(), hw, null);
	}

	public void xGegner(XGegner gegner1, int x, Waffenkarte hw)
	{
		gegner = new NTeilnehmer(e, gegner1.erstelleCharakterkarte(x), hw, null);
	}

	public void start(boolean tauschen)
	{
		nk = new NKampf(e, List.of(spieler), List.of(gegner), aktionen);
		nk.start();
		if(tauschen)
			spieler.waffenTauschen();
		nk.anfangstrigger();
	}

	public void beginneZug()
	{
		nk.beginneZug();
	}

	public boolean aktionskarte(int aktionNum, MitWaffe mit)
	{
		return nk.aktionskarte(spieler, nk.aktionenOptionen().get(aktionNum), mit, gegner);
	}

	public void gegnerAktionskarten()
	{
		nk.gegnerAktionskarten((n, a) -> 0);
	}

	public boolean magieEffekte(int... nehmen)
	{
		nk.magieZahlen();
		for(int i = 0; i < nehmen.length; i++)
		{
			spieler.magieEffektOptionen.get(nehmen[i]).benutzen = true;
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

	public NTeilnehmer getSpieler()
	{
		return spieler;
	}

	public NTeilnehmer getGegner()
	{
		return gegner;
	}
}