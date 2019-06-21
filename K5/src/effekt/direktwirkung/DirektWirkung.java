package effekt.direktwirkung;

import effekt.*;

public interface DirektWirkung
{
	void triggere(NTI n, NTI ziel, W mit);

	String text();
}