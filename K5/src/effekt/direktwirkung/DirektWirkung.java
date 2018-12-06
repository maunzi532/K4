package effekt.direktwirkung;

import kampf.*;
import karten.*;

public interface DirektWirkung
{
	void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit);

	String text();
}