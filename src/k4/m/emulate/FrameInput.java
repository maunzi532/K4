package k4.m.emulate;

import java.awt.event.*;
import javax.swing.*;

public class FrameInput
{
	private static final int KL = 300;
	private static final int MP = 200;
	private static final int MRP = 10;
	private static final int ESCAPE = 49;

	private boolean[] keys;

	public void einbau(JFrame fenster)
	{
		keys = new boolean[KL];
		fenster.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent keyEvent)
			{
				x(false, keyEvent.getKeyChar());
			}
		});
		fenster.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mouseEvent)
			{
				x(true, mouseEvent.getButton());
			}
		});
		fenster.addMouseWheelListener(new MouseAdapter()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
			{
				x(true, mouseWheelEvent.getWheelRotation() > 0 ? MRP : MRP + 1);
			}
		});
	}

	private void x(boolean m, int n)
	{
		if(m)
			n += MP;
		if(n >= 0 && n < keys.length)
			keys[n] = true;
	}

	public void escape()
	{
		x(false, ESCAPE);
	}

	/*public void fix()
	{
		Arrays.fill(keys, false);
	}*/

	public int firstKey()
	{
		int k = 0;
		for(; k < keys.length; k++)
		{
			if(keys[k])
			{
				keys[k] = false;
				return k;
			}
		}
		return -1;
	}
}