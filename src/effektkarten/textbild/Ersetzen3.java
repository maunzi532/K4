package effektkarten.textbild;

import java.util.HashMap;
import java.util.Map;

public final class Ersetzen3
{
	private final Map<String, String> ersetzen = new HashMap<>();

	public Ersetzen3()
	{
		ersetzen.put("Fortgeschrittener", "Fortges.");
	}

	public String ersetze(String von)
	{
		String ersetzt = von;
		for(Map.Entry<String, String> en : ersetzen.entrySet())
		{
			ersetzt = ersetzt.replace(en.getKey(), en.getValue());
		}
		return ersetzt;
	}
}