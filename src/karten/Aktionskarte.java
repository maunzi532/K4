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
	private final List<Effekt> effekte;

	public Aktionskarte(String name, int magieMod, int angriffMod, int geschwindigkeitMod, boolean hw, Effekt... effekte)
	{
		this.name = name;
		this.magieMod = magieMod;
		this.angriffMod = angriffMod;
		this.geschwindigkeitMod = geschwindigkeitMod;
		this.hw = hw;
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

	@Override
	public List<String> werte()
	{
		List<String> werte = new ArrayList<>();
		addWert(werte, "M", magieMod);
		addWert(werte, "A", angriffMod);
		addWert(werte, "G", geschwindigkeitMod);
		if(hw)
		{
			while(werte.size() < 8)
				werte.add("");
			werte.add("HW");
			werte.add("");
		}
		return werte;
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
	public List<Effekt> effekte()
	{
		return effekte;
	}
}