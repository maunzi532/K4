package effektkarten.sets;

import effektkarten.ansichtkarte.*;
import java.util.*;

public abstract class KartensetBuilder<T extends EffektKarte>
{
	private final Map<String, T> karten;

	public KartensetBuilder()
	{
		karten = new HashMap<>();
	}

	protected void neueKarte(T neueKarte)
	{
		karten.put(neueKarte.name(), neueKarte);
	}

	public Kartenset<T> fertig()
	{
		return new Kartenset<>(karten);
	}
}