package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;

public interface Bedingung
{
	boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe nichtMit);

	String text();
}