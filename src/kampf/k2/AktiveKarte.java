package kampf.k2;

import effektkarten.ansichtkarte.*;
import effektkarten.karten.*;

public class AktiveKarte
{
	private final EffektKarte karte;
	private final int basisAngriff;
	private final int basisWaffenwert;
	private final int basisGeschwindigkeit;
	private final int basisVerteidigung;
	private final int basisLeben;

	//Move basiswerte of (EffektKarte) to interface

	public AktiveKarte(EffektKarte karte)
	{
		this.karte = karte;
		if(karte instanceof Charakterkarte charakterkarte)
		{
			basisAngriff = charakterkarte.angriff();
			basisWaffenwert = charakterkarte.waffenwert();
			basisGeschwindigkeit = charakterkarte.geschwindigkeit();
			basisVerteidigung = charakterkarte.verteidigung();
			basisLeben = charakterkarte.leben();
		}
		else if(karte instanceof Waffenkarte waffenkarte)
		{
			basisAngriff = waffenkarte.angriff();
			basisWaffenwert = 0;
			basisGeschwindigkeit = waffenkarte.geschwindigkeit();
			basisVerteidigung = 0;
			basisLeben = 0;
		}
		else if(karte instanceof Aktionskarte aktionskarte)
		{
			basisAngriff = aktionskarte.angriffMod();
			basisWaffenwert = 0;
			basisGeschwindigkeit = aktionskarte.geschwindigkeitMod();
			basisVerteidigung = 0;
			basisLeben = 0;
		}
		else
		{
			throw new RuntimeException("Unexpected class: " + karte.getClass());
		}
	}
}