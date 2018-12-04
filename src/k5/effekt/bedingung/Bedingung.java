package k5.effekt.bedingung;

import k5.kampf.*;
import k5.karten.*;

public interface Bedingung
{
	boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit);

	String text();
}