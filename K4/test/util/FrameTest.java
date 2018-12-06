package util;

import javax.swing.*;
import m.emulate.*;
import org.junit.*;

public class FrameTest
{
	private JFrame frame;
	private JFrame dialog;

	@Test
	public void frameTest()
	{
		frame = new JFrame();
		//frame.setLayout(null);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.add(new JImagePanel(null));
		frame.setVisible(true);
		JOptionPane.showConfirmDialog(frame, "Wugu");
		try
		{
			Thread.sleep(100);
		}catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
		try
		{
			Thread.sleep(3000);
		}catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	public void dialogTest()
	{
		dialog = new JFrame();
		JOptionPane op = new JOptionPane();
		dialog.add(op);
		op.removeAll();
		op.add(new JImagePanel(null));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		try
		{
			Thread.sleep(100);
		}catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
		try
		{
			Thread.sleep(3000);
		}catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}