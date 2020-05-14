package dungeonmap.karte;

import java.util.*;

public final class KarteInMap
{
	private final MapKarte karte;
	private final boolean verkehrt;
	private final boolean[] verwendet;

	public KarteInMap(MapKarte karte, boolean verkehrt)
	{
		this.karte = karte;
		this.verkehrt = verkehrt;
		verwendet = new boolean[karte.modLimit()];
	}

	private KarteInMap(MapKarte karte, boolean verkehrt, boolean[] verwendet)
	{
		this.karte = karte;
		this.verkehrt = verkehrt;
		this.verwendet = verwendet;
	}

	public MapTeil ort(FeldKoordinaten f)
	{
		return karte.ort(f, verkehrt);
	}

	public int ortM(FeldKoordinaten f)
	{
		return karte.ortM(f, verkehrt);
	}

	public boolean isVerwendet(FeldKoordinaten f)
	{
		int mod = karte.ortM(f, verkehrt);
		if(mod >= 0)
			return verwendet[mod];
		else
			return false;
	}

	public KarteInMap verwende(FeldKoordinaten f)
	{
		int mod = karte.ortM(f, verkehrt);
		if(mod >= 0)
			return verwende(mod);
		else
			return this;
	}

	public KarteInMap verwende(int num)
	{
		boolean[] verwendetNeu = Arrays.copyOf(verwendet, karte.modLimit());
		verwendetNeu[num] = true;
		return new KarteInMap(karte, verkehrt, verwendetNeu);
	}

	public Begehbar begehbar(FeldKoordinaten f)
	{
		if(isVerwendet(f))
			return ort(f).begehbar1;
		else
			return ort(f).begehbar0;
	}

	public int anschluss(MapRichtung seite)
	{
		return karte.anschluss(seite, verkehrt);
	}
}