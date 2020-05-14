package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.EffektZielCharakter;
import effektkarten.effekte.ziel.MitWaffe;

public interface Bedingung
{
	boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit);

	String text();
}