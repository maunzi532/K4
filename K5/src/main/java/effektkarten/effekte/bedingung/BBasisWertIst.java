package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public class BBasisWertIst implements Bedingung
{
	private final Basiswert wert;
	private final EffektZielKartentyp von;
	private final Vergleich vergleich;
	private final int zu;

	public BBasisWertIst(Basiswert wert, EffektZielKartentyp von, Vergleich vergleich, int zu)
	{
		this.wert = wert;
		this.von = von;
		this.vergleich = vergleich;
		this.zu = zu;
	}

	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return vergleich.evaluiere(n.effektZielKarte(von, mit).basisWert(wert), zu);
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
		sb.append(" ").append(vergleich.text).append(" ").append(zu);
		return sb.toString();
	}
}