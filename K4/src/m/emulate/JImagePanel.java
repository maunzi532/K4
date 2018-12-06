package m.emulate;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class JImagePanel extends JPanel
{
	private BufferedImage image;

	public JImagePanel(BufferedImage image)
	{
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}