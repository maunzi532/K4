package effekt.direktwirkung;

import effekt.*;
import kampf.*;

public interface DirektWirkung
{
	void triggere(NTeilnehmer n, NTeilnehmer ziel, W mit);

	String text();
}