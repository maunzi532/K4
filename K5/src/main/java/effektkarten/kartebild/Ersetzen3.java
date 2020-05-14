package effektkarten.kartebild;

import java.util.*;

public final class Ersetzen3
{
	private final Map<String, String> ersetzen = new HashMap<>();

	public Ersetzen3()
	{
		ersetzen.put("Fortgeschrittener", "Fortges.");
	}

	public String ersetze(String von)
	{
		for(Map.Entry<String, String> en : ersetzen.entrySet())
		{
			von = von.replace(en.getKey(), en.getValue());
		}
		return von;
	}
}