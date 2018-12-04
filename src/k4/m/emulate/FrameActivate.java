package k4.m.emulate;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class FrameActivate
{
	private JFrame frame;
	private JImagePanel panel;
	private BufferedImage screen;
	private Graphics2D gd;

	public FrameActivate(int xs, int ys, FrameInput tasten, FrameFormatter format)
	{
		frame = new JFrame();
		screen = new BufferedImage(format.xchar * xs,
				format.ychar * ys, BufferedImage.TYPE_INT_RGB);
		gd = screen.createGraphics();
		frame.setSize(screen.getWidth(), screen.getHeight());
		tasten.einbau(frame);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent windowEvent)
			{
				tasten.escape();
			}
		});
		frame.setBackground(Color.BLACK);
		JOptionPane op = new JOptionPane();
		op.setBackground(Color.BLACK);
		frame.add(op);
		op.removeAll();
		panel = new JImagePanel(screen);
		panel.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
		op.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Graphics2D reset()
	{
		gd.setColor(Color.BLACK);
		gd.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		return gd;
	}

	public void imageToFrame()
	{
		frame.repaint();
		//frame.getGraphics().drawImage(screen, 0, 0, null);
	}

	public void imageToFrame(BufferedImage image)
	{
		gd.drawImage(image, 0, 0, null);
		frame.repaint();
		//frame.getGraphics().drawImage(image, 0, 0, null);
	}

	public void end()
	{
		frame.dispose();
	}
}