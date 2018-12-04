package k4.game;

import java.util.*;

public class XInputBuffer
{
	private Deque<Integer> waguh;
	private int lag;
	private int maxSize;

	public XInputBuffer(int maxSize)
	{
		waguh = new ArrayDeque<>();
		this.maxSize = maxSize;
	}

	public void addInput(int input)
	{
		if(input >= 0 && waguh.size() < maxSize)
			waguh.addLast(input);
	}

	public int getInput()
	{
		lag--;
		if(lag <= 0)
		{
			if(waguh.isEmpty())
				return -2;
			return waguh.removeFirst();
		}
		return -1;
	}

	public void newLag(int nLag)
	{
		lag = Math.max(lag, nLag);
	}

	public void addLag(int aLag)
	{
		if(lag < 0)
			lag = 0;
		lag += aLag;
	}

	public boolean zeroLag()
	{
		return lag <= 0;
	}
}