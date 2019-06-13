package logik;

import java.util.*;

public class TeamItems
{
	public List<ExpTrank> expTrankVorrat;
	public List<WaffeMap> waffenVorrat;

	public TeamItems()
	{
		expTrankVorrat = new ArrayList<>();
		waffenVorrat = new ArrayList<>();
	}

	public void erhalteTraenke(int anzahl, int exp)
	{
		for(int i = 0; i < anzahl; i++)
		{
			expTrankVorrat.add(new ExpTrank(exp));
		}
	}

	public void lagereWaffe(WaffeMap waffe)
	{
		waffenVorrat.add(waffe);
	}

	public WaffeMap entnehmeWaffe(int num)
	{
		if(num < 0 || num >= waffenVorrat.size())
			return null;
		return waffenVorrat.remove(num);
	}
}