package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public interface DirektWirkung
{
	void triggere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit);

	String text();
}