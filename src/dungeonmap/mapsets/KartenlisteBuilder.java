package dungeonmap.mapsets;

import java.util.*;
import stapelkarten.*;

public abstract class KartenlisteBuilder<T extends StapelKarte>
{
	private final List<T> karten;

	protected KartenlisteBuilder()
	{
		karten = new ArrayList<>();
	}

	public final void neueKarte(T karte)
	{
		karten.add(karte);
	}

	public final List<T> fertig()
	{
		return karten;
	}
}