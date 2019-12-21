package effektkarten.effekte.ziel;

import effektkarten.effekte.eigenschaften.*;
import java.util.*;

public interface EffektZielCharakter
{
	int getGeladeneMagie();

	int getGesAktion();

	List<? extends EffektZielCharakter> getAngegriffenVon();

	MitWaffe getMit();

	EffektZielCharakter getZiel();

	boolean aktiv();

	int getMagie();

	void setMagie(int magie);

	int getLeben();

	boolean isGibtMagieAus();

	EffektZielKarte effektZielKarte(EffektZielKartentyp kartentyp);

	EffektZielKarte effektZielKarte(EffektZielKartentyp kartentyp, MitWaffe zielWaffe);
}