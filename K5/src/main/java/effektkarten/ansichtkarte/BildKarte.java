package effektkarten.ansichtkarte;

import java.util.*;

public interface BildKarte
{
	String name();

	default int klassencode()
	{
		return -1;
	}

	List<Kartentext> text();

	List<String> werteLO();

	List<String> werteLU();

	List<String> werteR();
}