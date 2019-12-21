package map2;

import effektkarten.ansichtkarte.*;
import java.util.*;
import java.util.function.*;
import effektkarten.sets.*;
import java.util.stream.*;
import stapelkarten.*;

public class Kartenstapel<T extends StapelKarte>
{
	public List<T> karten;
	public LinkedList<T> deck;
	public List<T> umlauf;
	public Deque<T> ablage;

	public Kartenstapel(Kartenset<?> set)
	{
		karten = set.alleKarten().stream().map(e -> (T) e).collect(Collectors.toList());
		deck = new LinkedList<>(karten);
		Collections.shuffle(deck);
		umlauf = new ArrayList<>();
		ablage = new ArrayDeque<>();
	}

	public Kartenstapel(List<T> karten)
	{
		this.karten = karten;
		deck = new LinkedList<>(karten);
		Collections.shuffle(deck);
		umlauf = new ArrayList<>();
		ablage = new ArrayDeque<>();
	}

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

	public int effektiveDeckKartenAnzahl()
	{
		return deck.size() + ablage.size();
	}

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

	public T entnehmeKarte(String name)
	{
		T k1 = deck.stream().filter(k -> k instanceof EffektKarte && ((EffektKarte) k).name().equals(name)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(name));
		deck.remove(k1);
		umlauf.add(k1);
		return k1;
	}

	public void ablageInsDeck()
	{
		if(!ablage.isEmpty())
		{
			deck.addAll(ablage);
			Collections.shuffle(deck);
			ablage.clear();
		}
	}

	public void ablage(T k)
	{
		/*if(!umlauf.remove(k))
			throw new RuntimeException();*/
		ablage.addLast(k);
	}

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