package effektkarten.ansichtkarte;

import java.util.*;
import stapelkarten.*;

public interface EffektKarte extends StapelKarte, BildKarte
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