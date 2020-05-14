package effektkarten.effekte.ziel;

public interface EffektZielKarte
{
	int basisWert(Basiswert wert);

	void neuerEffekt(AnEffekt anEffekt, EffektZielCharakter sender, EffektZielCharakter ziel);
}