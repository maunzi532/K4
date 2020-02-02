package logik;

import effektkarten.ansichtkarte.*;
import effektkarten.karten.*;
import java.util.*;

public class WaffeMap implements EffektKarte
{
	private static final int ALLE_KLASSEN = 0b11111111;

	public final Waffenkarte karte;
	public final int maxBenutzungen;
	public int benutzungen;

	public WaffeMap(Waffenkarte karte, boolean initial)
	{
		this.karte = karte;
		if(initial)
			maxBenutzungen = 4;
		else if(karte.klassencode() == ALLE_KLASSEN)
			maxBenutzungen = 3;
		else
			maxBenutzungen = 2;
		benutzungen = maxBenutzungen;
	}

	@Override
	public String name()
	{
		return karte.name();
	}

	@Override
	public List<String> werteLO()
	{
		return karte.werteLO();
	}

	@Override
	public List<String> werteLU()
	{
		return List.of("H", benutzungen + "/" + maxBenutzungen);
	}

	@Override
	public List<String> werteR()
	{
		return karte.werteR();
	}

	@Override
	public List<KartenEffekt> effekte()
	{
		return karte.effekte();
	}
}