package mapsets;

import java.util.*;
import stapelkarten.*;

public class KartenlisteBuilder<T extends StapelKarte>
{
	private final List<T> karten;

	public KartenlisteBuilder()
	{
		karten = new ArrayList<>();
	}

	public void neueKarte(T karte)
	{
		karten.add(karte);
	}

	public List<T> fertig()
	{
		return karten;
	}
}