package effekt.bedingung;

import effekt.*;
import kampf.*;

public interface Bedingung
{
	boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit);

	String text();
}