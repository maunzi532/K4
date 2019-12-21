package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BBasisWertVergleich implements Bedingung
{
	public final Basiswert wert;
	public final EffektZielKartentyp von;
	public final Vergleich vergleich;

	public BBasisWertVergleich(Basiswert wert, EffektZielKartentyp von, Vergleich vergleich)
	{
		this.wert = wert;
		this.von = von;
		this.vergleich = vergleich;
	}

	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return vergleich.evaluiere(n.effektZielKarte(von, mit).basisWert(wert), ziel.effektZielKarte(von, ziel.getMit()).basisWert(wert));
	}

	@Override
	public String text()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(wert.kurz);
		if(von == EffektZielKartentyp.WAFFE)
			sb.append("_(Waffe)");
		else if(von == EffektZielKartentyp.AKTION)
			sb.append("_(Aktion)");
		sb.append(" ").append(vergleich.text).append(" ").append(wert.kurz).append("_(Ziel");
		if(von == EffektZielKartentyp.WAFFE)
			sb.append(",_Waffe");
		else if(von == EffektZielKartentyp.AKTION)
			sb.append(",_Aktion");
		sb.append(")");
		return sb.toString();
	}
}