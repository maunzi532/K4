package k5.charakter;

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