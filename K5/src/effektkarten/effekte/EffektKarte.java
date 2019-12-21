package effektkarten.effekte;

import effektkarten.ansichtkarte.*;
import effektkarten.effekte.effekt.*;
import java.util.*;

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