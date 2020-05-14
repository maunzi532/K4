package effektkarten.sets;

import effektkarten.ansichtkarte.*;
import java.util.*;

public abstract class KartensetBuilder<T extends EffektKarte>
{
	private final Map<String, T> karten;

	protected KartensetBuilder()
	{
		karten = new HashMap<>();
	}

	protected final void neueKarte(T neueKarte)
	{
		karten.put(neueKarte.name(), neueKarte);
	}

	public final Kartenset<T> fertig()
	{
		return new Kartenset<>(karten);
	}
}