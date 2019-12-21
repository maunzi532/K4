package effektkarten.effekte.effekt;

import effektkarten.ansichtkarte.*;
import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;
import java.util.*;

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

	public boolean kannAktivieren(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		return n.getMagie() >= magieKosten && bedingungen.stream().allMatch(e -> e.ok(n, ziel, mit));
	}

	public void aktiviere(EffektZielCharakter n, EffektZielCharakter ziel, MitWaffe mit)
	{
		n.setMagie(n.getMagie() - magieKosten);
	}
}