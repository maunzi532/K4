package effekt.bedingung;

import effekt.*;

public class BBasisWertVergleich implements Bedingung
{
	private final Basiswert wert;
	private final Kartentyp von;
	private final Vergleich vergleich;

	public BBasisWertVergleich(Basiswert wert, Kartentyp von, Vergleich vergleich)
	{
		this.wert = wert;
		this.von = von;
		this.vergleich = vergleich;
	}

	@Override
	public boolean ok(NTI n, NTI ziel, W mit)
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
		sb.append(wert.kurz);
		if(von == Kartentyp.WAFFE)
			sb.append("_(Waffe)");
		else if(von == Kartentyp.AKTION)
			sb.append("_(Aktion)");
		sb.append(" ").append(vergleich.text).append(" ").append(wert.kurz).append("_(Ziel");
		if(von == Kartentyp.WAFFE)
			sb.append(",_Waffe");
		else if(von == Kartentyp.AKTION)
			sb.append(",_Aktion");
		sb.append(")");
		return sb.toString();
	}
}