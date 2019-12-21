package kampf;

import effektkarten.effekte.effekt.*;

public class MagieEffektOption
{
	public final NKarte nKarte;
	public final MagieEffekt magieEffekt;
	public boolean benutzen;

	public MagieEffektOption(NKarte nKarte, MagieEffekt magieEffekt)
	{
		this.nKarte = nKarte;
		this.magieEffekt = magieEffekt;
		benutzen = false;
	}
}