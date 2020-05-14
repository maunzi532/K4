package effektkarten.effekte.bedingung;

import effektkarten.effekte.ziel.*;

public record BBasisWertVergleich(Basiswert wert, EffektZielKartentyp von, Vergleich vergleich) implements Bedingung
{
	@Override
	public boolean erfuellt(EffektZielCharakter sender, EffektZielCharakter ziel, MitWaffe nichtMit)
	{
		return vergleich.evaluiere(sender.effektZielKarte(von).basisWert(wert), ziel.effektZielKarte(von).basisWert(wert));
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
		sb.append(' ').append(vergleich.text).append(' ').append(wert.kurz).append("_(Ziel");
		if(von == EffektZielKartentyp.WAFFE)
			sb.append(",_Waffe");
		else if(von == EffektZielKartentyp.AKTION)
			sb.append(",_Aktion");
		sb.append(')');
		return sb.toString();
	}
}