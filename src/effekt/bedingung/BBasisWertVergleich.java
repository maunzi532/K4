package effekt.bedingung;

import effekt.*;
import kampf.*;
import karten.*;

public class BBasisWertVergleich implements Bedingung
{
	private final Basiswert wert;
	private final NKartentyp von;
	private final Vergleich vergleich;

	public BBasisWertVergleich(Basiswert wert, NKartentyp von, Vergleich vergleich)
	{
		this.wert = wert;
		this.von = von;
		this.vergleich = vergleich;
	}

	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		int diff = switch(von)
		{
			case CHARAKTER -> n.nCharakter().basisWert(wert) - ziel.nCharakter().basisWert(wert);
			case WAFFE -> n.nWaffe(mit).basisWert(wert) - ziel.nWaffe(ziel.getMit()).basisWert(wert);
			case AKTION -> n.nAktion().basisWert(wert) - ziel.nAktion().basisWert(wert);
		};
		return switch(vergleich)
		{
			case K -> diff < 0;
			case KG -> diff <= 0;
			case G -> diff > 0;
			case GG -> diff >= 0;
		};
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(wert.kurz).append("_(Basis");
		if(von == NKartentyp.WAFFE)
			sb.append(",_Waffe");
		else if(von == NKartentyp.AKTION)
			sb.append(",_Aktion");
		sb.append(") ").append(vergleich.text).append(" ").append(wert.kurz).append("_(Ziel)");
		return sb.toString();
	}
}