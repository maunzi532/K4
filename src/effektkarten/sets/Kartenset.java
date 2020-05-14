package effektkarten.sets;

import effektkarten.ansichtkarte.*;
import java.util.*;
import java.util.stream.*;
import stapelkarten.*;

public final class Kartenset<T extends EffektKarte>
{
	private final Map<String, T> karten;

	public Kartenset(Map<String, T> karten)
	{
		this.karten = karten;
	}

	public Kartenstapel<T> kartenstapel()
	{
		return new MischKartenstapel<>(karten.values());
	}

	public T gibKarte(String name)
	{
		return karten.get(name);
	}

	public List<T> alleKarten()
	{
		return new ArrayList<>(karten.values());
	}

	public List<T> findeKarten(String suche)
	{
		String sucheLow = suche.toLowerCase();
		return karten.keySet().stream().filter(e -> match(sucheLow, e)).map(karten::get).collect(Collectors.toList());
	}

	private boolean match(String sucheLow, String test)
	{
		return test.toLowerCase().startsWith(sucheLow);
	}
}