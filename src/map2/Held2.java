package map2;

import kampf.*;
import effektkarten.karten.*;
import logik.*;
import main.*;
import effektkarten.sets.*;
import stapelkarten.*;

public final class Held2
{
	public final Einstellungen e;
	public final Klasse klasse;
	public final Charakterkarte charakterkarte;
	public final UpgradeHeld upgradeHeld;
	public WaffeMap hauptwaffe;
	public WaffeMap nebenwaffe;
	public final int[] upgrades;
	public int exp;
	public int leben;
	public TrankStatus trankStatus;
	public final int gegnerExpLevel;

	public Held2(Klasse klasse, WaffeMap hauptwaffe, WaffeMap nebenwaffe, int[] upgrades, int exp,
			Kartenset<Charakterkarte> charakterkarten, Einstellungen e)
	{
		this.e = e;
		this.klasse = klasse;
		charakterkarte = charakterkarten.gibKarte(klasse.klassenName());
		upgradeHeld = new UpgradeHeld(charakterkarte, upgrades);
		this.hauptwaffe = hauptwaffe;
		this.nebenwaffe = nebenwaffe;
		this.upgrades = upgrades;
		this.exp = exp;
		leben = maxLeben();
		trankStatus = TrankStatus.MAXIMAL;
		gegnerExpLevel = 0;
	}

	public static Held2 lesen(String input, Kartenset<Charakterkarte> charakterkarten, Einstellungen e)
	{
		return null; //TODO
	}

	public static Held2 initial(Klasse klasse, Kartenset<Charakterkarte> charakterkarten, Kartenstapel<Waffenkarte> waffenkartenstapel, Einstellungen e)
	{
		return new Held2(klasse, new WaffeMap(waffenkartenstapel.entnehmeKarte(karte -> karte.name().equals(klasse.startwaffe())), true), null,
				new int[5], 0, charakterkarten, e);
	}

	public int maxLeben()
	{
		return (charakterkarte.leben() + upgrades[4]) * e.lebenMultiplikator;
	}

	public boolean kampfbereit()
	{
		if(trankStatus == TrankStatus.BESIEGT)
			return false;
		//Waffenklassen überprüfen
		if(hauptwaffe == null || hauptwaffe.benutzungen <= 0)
			return false;
		if(nebenwaffe == null)
		{
			return hauptwaffe.karte.gegnerOK();
		}
		else
		{
			return nebenwaffe.benutzungen > 0;
		}
	}

	public NTeilnehmer kampfversion()
	{
		return new NTeilnehmer(e, upgradeHeld.charakterkarte(), hauptwaffe.karte, nebenwaffe.karte);
	}

	public void tauscheWaffen()
	{
		WaffeMap z = hauptwaffe;
		hauptwaffe = nebenwaffe;
		nebenwaffe = z;
	}

	public void nachKampf(int lebenNeu)
	{
		leben = lebenNeu;
		if(leben <= 0)
		{
			trankStatus = TrankStatus.BESIEGT;
		}
		else if(leben >= maxLeben())
		{
			trankStatus = TrankStatus.MAXIMAL;
		}
		else
		{
			trankStatus = TrankStatus.NORMAL;
		}
		if(hauptwaffe != null)
		{
			hauptwaffe.benutzungen--;
		}
		if(nebenwaffe != null)
		{
			nebenwaffe.benutzungen--;
		}
	}

	public void verwendeTrank(int trankExp)
	{
		exp += trankExp;
		switch(trankStatus)
		{
			case BESIEGT, NORMAL ->
					{
						leben += maxLeben() / 2;
						if(leben >= maxLeben())
						{
							leben = maxLeben();
							trankStatus = TrankStatus.MAXIMAL;
						}
						else
							trankStatus = TrankStatus.TRANK;
					}
			case TRANK ->
					{
						leben = maxLeben();
						trankStatus = TrankStatus.MAXIMAL;
					}
		}
	}
}