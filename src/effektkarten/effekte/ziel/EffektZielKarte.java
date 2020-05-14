package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;

public interface EffektZielKarte
{
	int basisWert(Basiswert wert);

	void neuerEffekt(AnEffekt anEffekt, EffektZielCharakter n, EffektZielCharakter ziel);
}