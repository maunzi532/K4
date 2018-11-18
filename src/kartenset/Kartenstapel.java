package kartenset;

import java.util.*;

public class Kartenstapel<T extends Karte>
{
	public List<T> karten;
	public LinkedList<T> deck;
	public List<T> umlauf;
	public Deque<T> ablage;

	public Kartenstapel(Kartenset<T> set)
	{
		karten = set.alleKarten();
		deck = new LinkedList<>(karten);
		umlauf = new ArrayList<>();
		ablage = new ArrayDeque<>();
	}

	public T erhalteKarte()
	{
		if(deck.isEmpty())
		{
			deck.addAll(ablage);
			Collections.shuffle(deck);
			ablage.clear();
		}
		T k = deck.removeFirst();
		umlauf.add(k);
		return k;
	}

	public void ablage(T k)
	{
		if(!umlauf.contains(k))
			throw new RuntimeException();
		umlauf.remove(k);
		ablage.addLast(k);
	}
}