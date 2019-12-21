package effektkarten.sets;

import effektkarten.ansichtkarte.*;
import java.util.*;
import java.util.stream.*;

public class Kartenset<T extends EffektKarte>
{
	private Map<String, T> karten;

	public Kartenset()
	{
		karten = new HashMap<>();
	}

	public void neueKarte(T neueKarte)
	{
		karten.put(neueKarte.name(), neueKarte);
	}

	public T gibKarte(String name)
	{
		return karten.get(name);
	}

	public List<T> alleKarten()
	{
		return new ArrayList<>(karten.values());
	}

	public <U extends T> Kartenset<U> teilset(Class<U> klasse)
	{
		Kartenset<U> teilset = new Kartenset<>();
		karten.values().stream().filter(e -> e.getClass().equals(klasse))
				.forEach(e -> teilset.neueKarte((U) e));
		return teilset;
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