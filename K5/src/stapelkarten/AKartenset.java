package stapelkarten;

import java.util.*;

public class AKartenset<T extends StapelKarte>
{
	public List<T> karten;

	public AKartenset()
	{
		karten = new ArrayList<>();
	}

	public void neueKarte(T karte)
	{
		karten.add(karte);
	}
}