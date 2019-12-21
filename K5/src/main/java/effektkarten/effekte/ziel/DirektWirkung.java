package effektkarten.effekte.ziel;

public interface DirektWirkung
{
	void triggere(EffektZielCharakter n, EffektZielCharakter ziel);

	String text();
}