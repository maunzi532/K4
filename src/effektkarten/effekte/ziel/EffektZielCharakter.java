package effektkarten.effekte.ziel;

import java.util.*;

public interface EffektZielCharakter
{
	int getGeladeneMagie();

	int getGesAktion();

	List<? extends EffektZielCharakter> getAngegriffenVon();

	MitWaffe getMit();

	int getMagie();

	void setMagie(int magie);

	int getLeben();

	boolean isGibtMagieAus();

	EffektZielKarte effektZielKarte(EffektZielKartentyp kartentyp);

	EffektZielKarte effektZielKarte(EffektZielKartentyp kartentyp, MitWaffe zielWaffe);
}