package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public interface Bedingung
{
	boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit);

	String text();
}