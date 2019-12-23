package dungeonmap;

import dungeonmap.karte.*;
import java.util.*;

public class KarteInMap
{
	private final MapKarte karte;
	private final boolean verkehrt;
	private final boolean[] verwendet;

	public KarteInMap(MapKarte karte, boolean verkehrt)
	{
		this.karte = karte;
		this.verkehrt = verkehrt;
		verwendet = new boolean[karte.getModLimit()];
	}

	private KarteInMap(MapKarte karte, boolean verkehrt, boolean[] verwendet)
	{
		this.karte = karte;
		this.verkehrt = verkehrt;
		this.verwendet = verwendet;
	}

	public MapTeil ort(int yf, int xf)
	{
		return karte.ort(yf, xf, verkehrt);
	}

	public int ortM(int yf, int xf)
	{
		return karte.ortM(yf, xf, verkehrt);
	}

	public boolean isVerwendet(int yf, int xf)
	{
		int mod = karte.ortM(yf, xf, verkehrt);
		if(mod >= 0)
			return verwendet[mod];
		else
			return false;
	}

	public KarteInMap verwende(int yf, int xf)
	{
		int mod = karte.ortM(yf, xf, verkehrt);
		if(mod >= 0)
			return verwende(mod);
		else
			return this;
	}

	public KarteInMap verwende(int num)
	{
		boolean[] verwendetNeu = Arrays.copyOf(verwendet, karte.getModLimit());
		verwendetNeu[num] = true;
		return new KarteInMap(karte, verkehrt, verwendetNeu);
	}

	public Begehbar begehbar(int yf, int xf)
	{
		if(isVerwendet(yf, xf))
			return ort(yf, xf).begehbar1;
		else
			return ort(yf, xf).begehbar0;
	}

	public int anschluss(MapRichtung seite)
	{
		return karte.anschluss(seite, verkehrt);
	}
}