package effektkarten.ansichtkarte;

import java.util.*;
import stapelkarten.*;

public interface EffektKarte extends StapelKarte, BildKarte
{
	List<KartenEffekt> effekte();

	@Override
	default List<Kartentext> text()
	{
		return new ArrayList<>(effekte());
	}
}