package stapelkarten;

import java.util.*;
import java.util.function.*;

public class MischKartenstapel<T extends StapelKarte> implements Kartenstapel<T>
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
		T k = deck.removeFirst();
		umlauf.add(k);
		return Optional.ofNullable(k);
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
		T k = deck.removeFirst();
		umlauf.add(k);
		return Optional.of(k);
	}

	@Override
	public T entnehmeKarte(Predicate<T> check)
	{
		T k1 = deck.stream().filter(check).findFirst().orElseThrow();
		deck.remove(k1);
		umlauf.add(k1);
		return k1;
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
	public void ablage(T k)
	{
		if(!umlauf.remove(k))
			throw new RuntimeException("Nicht vorhandene Karte abgelegt");
		ablage.addLast(k);
	}

	@Override
	public Optional<T> durchsucheAlle(Predicate<T> check)
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