package effektkarten.effekte.bedingung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public interface Bedingung
{
	boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit);

	String text();
}