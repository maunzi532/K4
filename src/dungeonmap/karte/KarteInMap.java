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

	public MapTeil ort(FeldPosition fp)
	{
		return karte.ort(fp, verkehrt);
	}

	public int ortM(FeldPosition fp)
	{
		return karte.ortM(fp, verkehrt);
	}

	public boolean isVerwendet(FeldPosition fp)
	{
		int mod = karte.ortM(fp, verkehrt);
		if(mod >= 0)
			return verwendet[mod];
		else
			return false;
	}

	public KarteInMap verwende(FeldPosition fp)
	{
		int mod = karte.ortM(fp, verkehrt);
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

	public Begehbar begehbar(FeldPosition fp)
	{
		if(isVerwendet(fp))
			return ort(fp).begehbar1;
		else
			return ort(fp).begehbar0;
	}

	public int anschluss(MapRichtung seite)
	{
		return karte.anschluss(seite, verkehrt);
	}
}