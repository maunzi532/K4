package stapelkarten;

import java.util.*;
import java.util.function.*;

public final class MischKartenstapel<T extends StapelKarte> implements Kartenstapel<T>
{
	private final LinkedList<T> deck;
	private final List<T> umlauf;
	private final Deque<T> ablage;

	public MischKartenstapel(Collection<T> karten)
	{
		deck = new LinkedList<>(karten);
		Collections.shuffle(deck);
		umlauf = new ArrayList<>();
		ablage = new ArrayDeque<>();
	}

	@Override
	public Optional<T> erhalteKarte()
	{
		if(deck.isEmpty())
		{
			ablageInsDeck();
		}
		T karte = deck.removeFirst();
		umlauf.add(karte);
		return Optional.ofNullable(karte);
	}

	@Override
	public int effektiveDeckKartenAnzahl()
	{
		return deck.size() + ablage.size();
	}

	@Override
	public Optional<T> erhalteKarteNurDeck()
	{
		if(deck.isEmpty())
		{
			return Optional.empty();
		}
		T karte = deck.removeFirst();
		umlauf.add(karte);
		return Optional.of(karte);
	}

	@Override
	public T entnehmeKarte(Predicate<? super T> check)
	{
		T karte = deck.stream().filter(check).findFirst().orElseThrow();
		deck.remove(karte);
		umlauf.add(karte);
		return karte;
	}

	@Override
	public void ablageInsDeck()
	{
		if(!ablage.isEmpty())
		{
			deck.addAll(ablage);
			Collections.shuffle(deck);
			ablage.clear();
		}
	}

	@Override
	public void ablage(T karte)
	{
		if(!umlauf.remove(karte))
			throw new RuntimeException("Nicht vorhandene Karte abgelegt");
		ablage.addLast(karte);
	}

	@Override
	public Optional<T> durchsucheAlle(Predicate<? super T> check)
	{
		int anzahl = effektiveDeckKartenAnzahl();
		for(int i = 0; i < anzahl; i++)
		{
			T karte = erhalteKarte().orElseThrow();
			if(check.test(karte))
			{
				return Optional.of(karte);
			}
			else
			{
				ablage(karte);
			}
		}
		return Optional.empty();
	}
}