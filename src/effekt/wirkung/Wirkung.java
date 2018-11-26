package effekt.wirkung;

import kampf.*;
import karten.*;

public interface Wirkung
{
	default void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit){}

	default int angriff()
	{
		return 0;
	}

	default int geschwindigkeit()
	{
		return 0;
	}

	default int verteidigung()
	{
		return 0;
	}

	default int mindestschaden()
	{
		return 0;
	}

	default int mindestschutz()
	{
		return 0;
	}

	default int magie()
	{
		return 0;
	}

	default int extraangriffe()
	{
		return 0;
	}

	default int setzeangriffe()
	{
		return -1;
	}

	String text();
}