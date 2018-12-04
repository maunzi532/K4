package k5.charakter;

import k5.kampf.*;

public class Waffenwechsel
{
	public final int spielerNummer;
	public final TeamItems teamItems;
	public final WaffeMap hauptwaffeV;
	public final WaffeMap nebenwaffeV;
	public WaffeMap hauptwaffeN;
	public WaffeMap nebenwaffeN;

	public Waffenwechsel(int spielerNummer, TeamItems teamItems, WaffeMap hauptwaffeV, WaffeMap nebenwaffeV)
	{
		this.spielerNummer = spielerNummer;
		this.teamItems = teamItems;
		this.hauptwaffeV = hauptwaffeV;
		this.nebenwaffeV = nebenwaffeV;
		hauptwaffeN = hauptwaffeV;
		nebenwaffeN = nebenwaffeV;
	}

	public void tausche()
	{
		WaffeMap w = hauptwaffeN;
		hauptwaffeN = nebenwaffeN;
		nebenwaffeN = w;
	}

	public void alsHauptwaffe(int num)
	{
		lagereHauptwaffe();
		hauptwaffeN = teamItems.entnehmeWaffe(num);
	}

	public void lagereHauptwaffe()
	{
		if(hauptwaffeN != null)
		{
			teamItems.lagereWaffe(hauptwaffeN);
			hauptwaffeN = null;
		}
	}

	public void alsNebenwaffe(int num)
	{
		lagereNebenwaffe();
		nebenwaffeN = teamItems.entnehmeWaffe(num);
	}

	public void lagereNebenwaffe()
	{
		if(nebenwaffeN != null)
		{
			teamItems.lagereWaffe(nebenwaffeN);
			nebenwaffeN = null;
		}
	}

	public boolean anwenden(HeldMap spieler)
	{
		if(hauptwaffeN == hauptwaffeV && nebenwaffeN == nebenwaffeV)
		{
			return false;
		}
		spieler.hauptwaffe = hauptwaffeN;
		spieler.nebenwaffe = nebenwaffeN;
		return true;
	}

	public boolean anwenden(NTeilnehmer spieler)
	{
		if(hauptwaffeN == hauptwaffeV && nebenwaffeN == nebenwaffeV)
		{
			spieler.gesVorteil0 = 1;
			return false;
		}
		spieler.nHauptwaffe = NWaffe.von(hauptwaffeN.karte);
		spieler.nNebenwaffe = NWaffe.von(nebenwaffeN.karte);
		spieler.gesVorteil0 = -1;
		return true;
	}
}