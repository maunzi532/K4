package effekt.bedingung;

import effekt.*;
import kampf.*;
import karten.*;

public class BBasisWertIst implements Bedingung
{
	private final Basiswert wert;
	private final NKartentyp von;
	private final Vergleich vergleich;
	private final int zu;

	public BBasisWertIst(Basiswert wert, NKartentyp von, Vergleich vergleich, int zu)
	{
		this.wert = wert;
		this.von = von;
		this.vergleich = vergleich;
		this.zu = zu;
	}

	@Override
	public boolean ok(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		int ist = switch(von)
		{
			case CHARAKTER -> n.nCharakter().basisWert(wert);
			case WAFFE -> n.nWaffe(mit).basisWert(wert);
			case AKTION -> n.nAktion().basisWert(wert);
		};
		return switch(vergleich)
		{
			case K -> ist < zu;
			case KG -> ist <= zu;
			case G -> ist > zu;
			case GG -> ist >= zu;
		};
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(wert.kurz).append(" (Basis");
		if(von == NKartentyp.WAFFE)
			sb.append(", Waffe");
		else if(von == NKartentyp.AKTION)
			sb.append(", Aktion");
		sb.append(") ");
		switch(vergleich)
		{
			case K -> sb.append("<");
			case KG -> sb.append("<=");
			case G -> sb.append(">");
			case GG -> sb.append(">=");
		}
		sb.append(" ").append(zu);
		return sb.toString();
	}
}