package effektkarten.effekte.wirkung;

import effektkarten.effekte.ziel.EffektZielCharakter;

public interface Wirkung
{
	default int triggere(EffektZielCharakter sender, EffektZielCharakter ziel)
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