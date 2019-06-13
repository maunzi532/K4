package effekt.wirkung;

import effekt.*;
import kampf.*;

public interface Wirkung
{
	default int triggere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return 0;
	}

	default int getWert(Wirkungswert wert, int daten)
	{
		return switch(wert)
		{
			case ANGRIFF -> getAngriff(daten);
			case GESCHWINDIGKEIT -> getGeschwindigkeit(daten);
			case VERTEIDIGUNG -> getVerteidigung(daten);
			case MINDESTSCHADEN -> getMindestschaden(daten);
			case MINDESTSCHUTZ -> getMindestschutz(daten);
			case EXTRAANGRIFFE -> getExtraangriffe(daten);
			case MAGIE -> getMagie(daten);
		};
	}

	default int getAngriff(int daten)
	{
		return 0;
	}

	default int getGeschwindigkeit(int daten)
	{
		return 0;
	}

	default int getVerteidigung(int daten)
	{
		return 0;
	}

	default int getMindestschaden(int daten)
	{
		return 0;
	}

	default int getMindestschutz(int daten)
	{
		return 0;
	}

	default int getMagie(int daten)
	{
		return 0;
	}

	default int getExtraangriffe(int daten)
	{
		return 0;
	}

	default int getSetzeangriffe(int daten)
	{
		return -1;
	}

	String text();
}