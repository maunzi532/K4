package k5.effekt.direktwirkung;

import k5.kampf.*;
import k5.karten.*;

public interface DirektWirkung
{
	void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit);

	String text();
}