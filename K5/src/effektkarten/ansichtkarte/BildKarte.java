package effektkarten.ansichtkarte;

import java.util.*;

public interface BildKarte
{
	String name();

	List<String> werteLO();

	List<String> werteLU();

	List<String> werteR();

	default int klassencode()
	{
		return -1;
	}

	List<Kartentext> text();
}