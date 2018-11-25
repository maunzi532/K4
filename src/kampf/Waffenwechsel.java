package kampf;

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

	public boolean anwenden(NTeilnehmer spieler)
	{
		if(hauptwaffeN == hauptwaffeV && nebenwaffeN == nebenwaffeV)
		{
			return false;
		}
		spieler.nHauptwaffe = new NWaffe(hauptwaffeN);
		spieler.nNebenwaffe = new NWaffe(nebenwaffeN);
		return true;
	}
}