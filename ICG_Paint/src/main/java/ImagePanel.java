import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener {
	private boolean draw = true;
	private int x, y, xd = -1, yd = -1;
	private BufferedImage img;

	private ColorHolder colorHolder; // Объект для хранения текущего цвета

	public ImagePanel(ColorHolder colorHolder) {
		this.colorHolder = colorHolder; // Инициализация colorHolder
		addMouseListener(this);

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Произвольная линия (любой формы)
				if (xd >= 0) {
					Graphics g = getGraphics();
					g.setColor(colorHolder.getCurrentColor()); // Используем текущий цвет
					g.drawLine(xd, yd, e.getX(), e.getY());
				}

				xd = e.getX();
				yd = e.getY();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (draw) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}

	public void clean() {
		draw = !draw;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent ev) {}

	@Override
	public void mouseEntered(MouseEvent ev) {}

	@Override
	public void mouseExited(MouseEvent ev) {}

	@Override
	public void mousePressed(MouseEvent ev) {
		xd = x = ev.getX();
		yd = y = ev.getY();
	}

	@Override
	public void mouseReleased(MouseEvent ev) {
		Graphics g = getGraphics();
		g.setColor(colorHolder.getCurrentColor()); // Используем текущий цвет
		g.drawLine(x, y, ev.getX(), ev.getY());
		xd = -1;
		yd = -1;
	}
}