package charakter;

import kampf.*;

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
		spieler.nHauptwaffe = new NWaffe(hauptwaffeN.karte);
		spieler.nNebenwaffe = new NWaffe(nebenwaffeN.karte);
		spieler.gesVorteil0 = -1;
		return true;
	}
}