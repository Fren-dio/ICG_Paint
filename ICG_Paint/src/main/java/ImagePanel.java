import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener {
	private boolean draw = true;
	private int x, y, xd = -1, yd = -1;
	private BufferedImage img;

	int DEFAULT_TRIANGLE_SIZE = 200;

	private final SelectedSettings selectedSettings;

	public ImagePanel(SelectedSettings selectedSettings) {
		this.selectedSettings = selectedSettings;
		addMouseListener(this);

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (selectedSettings.getCurrentMode().equals("WAVED_LINE_MODE")) {
					if (xd >= 0) {
						Graphics g = getGraphics();
						g.setColor(selectedSettings.getCurrentColor()); // Используем текущий цвет
						g.drawLine(xd, yd, e.getX(), e.getY());
					}

					xd = e.getX();
					yd = e.getY();
				}
				if (selectedSettings.getCurrentMode().equals("FUN_MODE")) {
					drawFunMode(e);
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
		if (selectedSettings.getCurrentMode().equals("STRAIGHT_LINE_MODE")) {
			Graphics g = getGraphics();
			g.setColor(selectedSettings.getCurrentColor()); // Используем текущий цвет
			g.drawLine(x, y, ev.getX(), ev.getY());
			xd = -1;
			yd = -1;
		}
		if (selectedSettings.getCurrentMode().equals("FUN_MODE")) {
			drawFunMode(ev);
		}
		if (selectedSettings.getCurrentMode().equals("TRIANGLE_PATTERN_MODE"))
			drawTriangle(ev);
	}

	private void drawFunMode(MouseEvent ev) {
		int currentX = ev.getX();
		int currentY = ev.getY();

		Graphics g = getGraphics();
		g.setColor(selectedSettings.getCurrentColor());
		g.drawLine(currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3);
	}

	private void drawTriangle(MouseEvent ev) {
		int currentX = ev.getX();
		int currentY = ev.getY();

		Graphics g = getGraphics();
		g.setColor(selectedSettings.getCurrentColor());
		g.drawLine(currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3,
				currentX+DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3);
		g.drawLine(currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentX+DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3);
		g.drawLine(currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3);

	}
}