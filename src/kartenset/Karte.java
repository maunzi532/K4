package kartenset;

import effekt.*;
import java.util.*;

public interface Karte
{
	int alleKlassen = 0b11111111;

	String getName();

	List<String> werte();

	default int klassencode()
	{
		return -1;
	}

	List<KartenEffekt> effekte();
}