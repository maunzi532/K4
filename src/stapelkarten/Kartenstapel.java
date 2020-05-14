package stapelkarten;

import java.util.*;
import java.util.function.*;

public interface Kartenstapel<T extends StapelKarte>
{
	Optional<T> erhalteKarte();

	int effektiveDeckKartenAnzahl();

	Optional<T> erhalteKarteNurDeck();

	T entnehmeKarte(Predicate<T> check);

	void ablageInsDeck();

	void ablage(T karte);

	Optional<T> durchsucheAlle(Predicate<T> check);
}