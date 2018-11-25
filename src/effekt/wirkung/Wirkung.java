package effekt.wirkung;

import kampf.*;
import karten.*;

public interface Wirkung
{
	default void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit){}

	int angriff();

	int geschwindigkeit();

	int verteidigung();

	int mindestschaden();

	int mindestschutz();

	int magie();

	int extraangriffe();

	int setzeangriffe();

	String text();
}