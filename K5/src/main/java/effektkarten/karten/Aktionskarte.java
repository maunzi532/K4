package effektkarten.karten;

import effektkarten.ansichtkarte.*;
import java.util.*;

public record Aktionskarte(String name, int magieMod, int angriffMod, int geschwindigkeitMod, boolean hw, boolean ladeMitMagie, List<KartenEffekt> effekte) implements EffektKarte
{
	public Aktionskarte(String name, int magieMod, int angriffMod, int geschwindigkeitMod, boolean hw, boolean ladeMitMagie, KartenEffekt... effekte)
	{
		this(name, magieMod, angriffMod, geschwindigkeitMod, hw, ladeMitMagie, Arrays.asList(effekte));
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return effekte;
	}

	@Override
	public List<String> werteLO()
	{
		List<String> werte = new ArrayList<>();
		if(ladeMitMagie)
		{
			werte.add("M");
			werte.add("-> 0");
		}
		else
		{
			addWert(werte, "M", magieMod);
		}
		addWert(werte, "A", angriffMod);
		addWert(werte, "G", geschwindigkeitMod);
		return werte;
	}

	private void addWert(List<? super String> werte, String wertName, int wert)
	{
		if(wert != 0)
		{
			werte.add(wertName);
			werte.add((wert > 0 ? "+" : "") + wert);
		}
		else
		{
			werte.add("");
			werte.add("");
		}
	}

	@Override
	public List<String> werteLU()
	{
		List<String> werte = new ArrayList<>();
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
}