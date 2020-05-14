package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;

public interface Wirkung
{
	default int triggere(EffektZielCharakter n, EffektZielCharakter ziel)
	{
		return 0;
	}

	default int wert(Wirkungswert wert, int daten)
	{
		return 0;
	}

	default int setzeangriffeWert(int daten)
	{
		return -1;
	}

	String text();
}