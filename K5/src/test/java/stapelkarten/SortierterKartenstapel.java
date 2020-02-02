package stapelkarten;

import effektkarten.ansichtkarte.*;
import effektkarten.sets.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class SortierterKartenstapel<T extends EffektKarte> implements Kartenstapel<T>
{
	private final LinkedList<T> deck;
	private final List<T> umlauf;
	private final Deque<T> ablage;

	public SortierterKartenstapel(Kartenset<T> kartenset, String... kartennamen)
	{
		deck = Arrays.stream(kartennamen).map(kartenset::gibKarte).collect(Collectors.toCollection(LinkedList::new));
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
		if(deck.isEmpty())
			throw new RuntimeException("Keine Karten in Deck oder Ablage");
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
			ablage.clear();
		}
	}

	@Override
	public void ablage(T k)
	{
		umlauf.remove(k);
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