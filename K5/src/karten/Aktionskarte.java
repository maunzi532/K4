package karten;

import effekt.*;
import java.util.*;
import kartenset.*;

public class Aktionskarte implements Karte
{
	private final String name;
	private final int magieMod;
	private final int angriffMod;
	private final int geschwindigkeitMod;
	private final boolean hw;
	private final boolean ladeMitMagie;
	private final List<KartenEffekt> effekte;

	public Aktionskarte(String name, int magieMod, int angriffMod, int geschwindigkeitMod, boolean hw, KartenEffekt... effekte)
	{
		this.name = name;
		this.magieMod = magieMod;
		this.angriffMod = angriffMod;
		this.geschwindigkeitMod = geschwindigkeitMod;
		this.hw = hw;
		ladeMitMagie = false;
		this.effekte = Arrays.asList(effekte);
	}

	public Aktionskarte(String name, int magieMod, int angriffMod, int geschwindigkeitMod, boolean hw, boolean ladeMitMagie, KartenEffekt... effekte)
	{
		this.name = name;
		this.magieMod = magieMod;
		this.angriffMod = angriffMod;
		this.geschwindigkeitMod = geschwindigkeitMod;
		this.hw = hw;
		this.ladeMitMagie = ladeMitMagie;
		this.effekte = Arrays.asList(effekte);
	}

	@Override
	public String getName()
	{
		return name;
	}

	public int getMagieMod()
	{
		return magieMod;
	}

	public int getAngriffMod()
	{
		return angriffMod;
	}

	public int getGeschwindigkeitMod()
	{
		return geschwindigkeitMod;
	}

	public boolean isHw()
	{
		return hw;
	}

	public boolean isLadeMitMagie()
	{
		return ladeMitMagie;
	}

	@Override
	public List<String> werteLO()
	{
		List<String> werte = new ArrayList<>();
		addWert(werte, "M", magieMod);
		addWert(werte, "A", angriffMod);
		addWert(werte, "G", geschwindigkeitMod);
		return werte;
	}

	@Override
	public List<String> werteLU()
	{
		List<String> werte = new ArrayList<>();
		if(ladeMitMagie)
		{
			werte.add("LM");
			werte.add("");
		}
		if(hw)
		{
			werte.add("HW");
			werte.add("");
		}
		return werte;
	}

	@Override
	public List<String> werteR()
	{
		return null;
	}

	private static void addWert(List<String> werte, String name, int wert)
	{
		if(wert != 0)
		{
			werte.add(name);
			werte.add((wert > 0 ? "+" : "") + wert);
		}
		else
		{
			werte.add("");
			werte.add("");
		}
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}
}