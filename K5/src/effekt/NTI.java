package effekt;

import effekt.kampf.*;
import java.util.*;

public interface NTI
{
	int getGeladeneMagie();

	int getGesAktion();

	List<? extends NTI> getAngegriffenVon();

	W getMit();

	NTI getZiel();

	boolean aktiv();

	int getMagie();

	void setMagie(int magie);

	int getLeben();

	NCharakter nCharakter();

	NWaffe nWaffe(W w);

	NAktion nAktion();

	boolean isGibtMagieAus();
}