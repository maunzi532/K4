package effektkarten.effekte.effekt;

import effektkarten.ansichtkarte.*;
import effektkarten.effekte.bedingung.Bedingung;
import effektkarten.effekte.ziel.*;
import java.util.*;

public abstract class MagieEffekt extends KartenEffekt
{
	public final int magieKosten;
	public final List<Bedingung> bedingungen;

	protected MagieEffekt(String text, int num, int magieKosten, List<Bedingung> bedingungen)
	{
		super("", text, num);
		this.magieKosten = magieKosten;
		this.bedingungen = bedingungen;
	}

	public final boolean kannAktivieren(EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		return sender.getMagie() >= magieKosten && bedingungen.stream().allMatch(e -> e.erfuellt(sender, ziel, null));
	}

	public void aktiviere(EffektZielCharakter sender, EffektZielCharakter ziel)
	{
		sender.setMagie(sender.getMagie() - magieKosten);
	}
}