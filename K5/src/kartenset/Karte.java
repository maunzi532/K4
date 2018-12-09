package kartenset;

import effekt.*;
import java.util.*;

public interface Karte extends AKarte
{
	int alleKlassen = 0b11111111;

	String getName();

	List<String> werteLO();

	List<String> werteLU();

	List<String> werteR();

	default int klassencode()
	{
		return -1;
	}

	List<KartenEffekt> effekte();
}