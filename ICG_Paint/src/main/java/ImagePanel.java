package ru.nsu.fit.g18200.rylov.testproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener
{
	private boolean draw = true;
	private int x, y, xd=-1, yd=-1;
	private BufferedImage img;
	
	public ImagePanel()
	{
		addMouseListener(this);
		
		addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				if (xd >= 0)
				{
					Graphics g = getGraphics();
					g.setColor(Color.blue);
					g.drawLine(xd, yd, e.getX(), e.getY());
				}
				
				xd = e.getX();
				yd = e.getY();
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if (draw)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(0, 0, getWidth()-1, getHeight()-1);
			
			
			// Работа с изображением (BufferedImage)
			/*
			 * Получить цвет пикселя:
			 * int color = img.getRGB(x, y);
			 * Задать цвет пикселя:
			 * img.setRGB(x, y, Color.GREEN.getRGB());
			 * Подготовка изображения должна проводиться в другом методе, который будет вызывать repaint();
			 * 
			 * В paintComponent() в итоге просто отрисовывается обработанное изображение:
			 * g2.drawImage(img, x, y, getWidth(), getHeight(), null);
			*/
		}
	}
	
	public void clean()
	{
		draw = !draw;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent ev) { }

	@Override
	public void mouseEntered(MouseEvent ev) { }

	@Override
	public void mouseExited(MouseEvent ev) { }

	@Override
	public void mousePressed(MouseEvent ev) {
		xd = x = ev.getX();
		yd = y = ev.getY();
	}

	@Override
	public void mouseReleased(MouseEvent ev) {
		getGraphics().drawLine(x, y, ev.getX(), ev.getY());
		xd = -1;
		yd = -1;
	}
}
