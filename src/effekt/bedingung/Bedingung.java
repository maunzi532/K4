package effekt.bedingung;

import kampf.*;
import karten.*;

public interface Bedingung
{
	boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit);

	String text();
}