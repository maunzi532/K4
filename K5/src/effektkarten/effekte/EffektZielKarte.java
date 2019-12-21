package effektkarten.effekte;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.wirkung.*;

public interface EffektZielKarte
{
	int basisWert(Basiswert wert);

	void neuerEffekt(AnEffekt anEffekt, EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit);
}