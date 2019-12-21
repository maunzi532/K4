package effektkarten.effekte.wirkung;

import effektkarten.effekte.*;
import effektkarten.effekte.eigenschaften.*;

public interface DirektWirkung
{
	void triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit);

	String text();
}