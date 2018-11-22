package kartenset;

import java.util.*;

public class Kartenstapel<T extends AKarte>
{
	public List<T> karten;
	public LinkedList<T> deck;
	public List<T> umlauf;
	public Deque<T> ablage;

	public Kartenstapel(Kartenset set)
	{
		karten = set.alleKarten();
		deck = new LinkedList<>(karten);
		umlauf = new ArrayList<>();
		ablage = new ArrayDeque<>();
	}

	public Kartenstapel(List<T> karten)
	{
		this.karten = karten;
		deck = new LinkedList<>(karten);
		umlauf = new ArrayList<>();
		ablage = new ArrayDeque<>();
	}

	public T erhalteKarte()
	{
		if(deck.isEmpty())
		{
			ablageInsDeck();
		}
		T k = deck.removeFirst();
		umlauf.add(k);
		return k;
	}

	public void ablageInsDeck()
	{
		deck.addAll(ablage);
		Collections.shuffle(deck);
		ablage.clear();
	}

	public void ablage(T k)
	{
		assert umlauf.remove(k);
		ablage.addLast(k);
	}
}