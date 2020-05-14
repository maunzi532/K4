package effektkarten.effekte.bedingung;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record BBasisWertIst(Basiswert wert, EffektZielKartentyp von, Vergleich vergleich, int zu) implements Bedingung
{
	@Override
	public boolean ok(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return vergleich.evaluiere(n.effektZielKarte(von).basisWert(wert), zu);
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