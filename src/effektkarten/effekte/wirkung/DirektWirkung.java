package effektkarten.effekte.wirkung;

import effektkarten.effekte.ziel.EffektZielCharakter;

public interface DirektWirkung
{
	void triggere(EffektZielCharakter sender, EffektZielCharakter ziel);

	String text();
}