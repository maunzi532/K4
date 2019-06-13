package basiskarte;

import ansichtkarte.*;
import java.util.*;

public interface Karte extends AKarte, BildKarte
{
	@Override
	String name();

	List<KartenEffekt> effekte();

	@Override
	default List<Kartentext> text()
	{
		return new ArrayList<>(effekte());
	}
}