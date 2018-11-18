package kartenset;

import java.util.*;

public class Kartenset<T extends Karte>
{
	private Map<String, T> karten;

	public Kartenset()
	{
		karten = new HashMap<>();
	}

	public void neueKarte(T neueKarte)
	{
		karten.put(neueKarte.getName(), neueKarte);
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
}