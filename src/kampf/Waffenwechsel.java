package kampf;

import effekt.*;
import java.util.*;
import karten.*;

public class Waffenwechsel
{
	public final NKampf hier;
	public final int spielerId;
	public final List<Waffenkarte> verwendbarW;
	public final Waffenkarte hauptwaffeV;
	public final Waffenkarte nebenwaffeV;
	public Waffenkarte hauptwaffeN;
	public Waffenkarte nebenwaffeN;

	public Waffenwechsel(NKampf hier, int spielerId, List<Waffenkarte> verwendbarW,
			Waffenkarte hauptwaffeV, Waffenkarte nebenwaffeV)
	{
		this.hier = hier;
		this.spielerId = spielerId;
		this.verwendbarW = verwendbarW;
		this.hauptwaffeV = hauptwaffeV;
		this.nebenwaffeV = nebenwaffeV;
		hauptwaffeN = hauptwaffeV;
		nebenwaffeN = nebenwaffeV;
	}

	public boolean anwenden(NTeilnehmer spieler, boolean nachAnfang)
	{
		/*if(hauptwaffeV != hauptwaffeN && hauptwaffeV != nebenwaffeN)
		{
			spieler.setnHauptwaffe(null);
		}
		if(nebenwaffeV != nebenwaffeN && nebenwaffeV != hauptwaffeN)
		{
			spieler.setnNebenwaffe(null);
		}*/
		if(hauptwaffeN == hauptwaffeV && nebenwaffeN == nebenwaffeV)
		{
			return false;
		}
		if(nachAnfang)
		{
			if(hauptwaffeV != hauptwaffeN)
			{
				//Check Null
				//Effekt Ende, Trigger: Verwendet
				//spieler.getnHauptwaffe()
			}
			if(nebenwaffeV != nebenwaffeN)
			{
				//Check Null
				//Effekt Ende, Trigger: Verwendet
				//spieler.getnNebenwaffe()
			}
		}

		if(hauptwaffeN == nebenwaffeV && nebenwaffeN == hauptwaffeV)
		{
			//Tausch
		}

		if(nachAnfang)
		{
			if(hauptwaffeN != hauptwaffeV)
			{
				//Effekt Start, Trigger: Verwendet
				if(spieler.nHauptwaffe != null)
					spieler.triggereEffekte1va(spieler.getWaffeKarte(W.HW).effekte(), StartTrigger2.VERWENDET, W.HW);
			}
			if(nebenwaffeN != nebenwaffeV)
			{
				//Effekt Start, Trigger: Verwendet
				if(spieler.nNebenwaffe != null)
					spieler.triggereEffekte1va(spieler.getWaffeKarte(W.NW).effekte(), StartTrigger2.VERWENDET, W.NW);
			}
		}
		return true;
	}
}