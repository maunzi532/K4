package k5.effekt;

import k5.effekt.bedingung.*;
import java.util.*;
import k5.kampf.*;
import k5.karten.*;

public abstract class MagieEffekt extends KartenEffekt
{
	public final int magieKosten;
	public final List<Bedingung> bedingungen;

	public MagieEffekt(String typ, String text, int num, int magieKosten, List<Bedingung> bedingungen)
	{
		super(typ, text, num);
		this.magieKosten = magieKosten;
		this.bedingungen = bedingungen;
	}

	public boolean kannAktivieren(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		return n.getMagie() >= magieKosten && bedingungen.stream().allMatch(e -> e.ok(n, ziel, mit));
	}

	public void aktiviere(NTeilnehmer n, NTeilnehmer ziel, W mit)
	{
		n.setMagie(n.getMagie() - magieKosten);
	}
}