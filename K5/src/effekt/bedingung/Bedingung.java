package effekt.bedingung;

import effekt.*;

public interface Bedingung
{
	boolean ok(NTI n, NTI ziel, W mit);

	String text();
}