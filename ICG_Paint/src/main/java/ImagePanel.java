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

	int DEFAULT_SQUARE_SIZE = 200;
	private SelectedSettings selectedSettings;

	public ImagePanel() {
		super();
	}

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
			if (selectedSettings.getCurrentWeight() == selectedSettings.LIGHT_LINE_WEIGHT) {
				Graphics g = getGraphics();
				myDrawLine((Graphics2D) g, x, y, ev.getX(), ev.getY(), 1);
				xd = -1;
				yd = -1;
			}
			else {
				Graphics2D g = (Graphics2D) (getGraphics());
				myDrawLine(g, x, y, ev.getX(), ev.getY(), selectedSettings.getCurrentWeight());
			}
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
		if (selectedSettings.getCurrentWeight() == selectedSettings.LIGHT_LINE_WEIGHT)
			myDrawLine((Graphics2D) g,
					currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
					currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3,
					1);
		else
			myDrawLine((Graphics2D) g,
					currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
					currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3,
					selectedSettings.getCurrentWeight());
	}

	private void drawTriangle(MouseEvent ev) {
		int currentX = ev.getX();
		int currentY = ev.getY();
		int currentWeight = selectedSettings.getCurrentWeight();

		Graphics g = getGraphics();
		myDrawLine((Graphics2D) g, currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3,
				currentX+DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentWeight);
		myDrawLine((Graphics2D) g, currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentX+DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentWeight);
		myDrawLine((Graphics2D) g, currentX-DEFAULT_TRIANGLE_SIZE*2/3, currentY+DEFAULT_TRIANGLE_SIZE/3,
				currentX, currentY-DEFAULT_TRIANGLE_SIZE*2/3,
				currentWeight);
	}


	private void drawSquare(MouseEvent ev) {
		int currentX = ev.getX();
		int currentY = ev.getY();
		int currentWeight = selectedSettings.getCurrentWeight();

		Graphics g = getGraphics();
		myDrawLine((Graphics2D) g, currentX-DEFAULT_SQUARE_SIZE/2, currentY-DEFAULT_SQUARE_SIZE/2,
				currentX-DEFAULT_SQUARE_SIZE/2, currentY+DEFAULT_SQUARE_SIZE/2, currentWeight);
		myDrawLine((Graphics2D) g, currentX-DEFAULT_SQUARE_SIZE/2, currentY+DEFAULT_SQUARE_SIZE/2,
				currentX+DEFAULT_SQUARE_SIZE/2, currentY+DEFAULT_SQUARE_SIZE/2, currentWeight);
		myDrawLine((Graphics2D) g, currentX+DEFAULT_SQUARE_SIZE/2, currentY+DEFAULT_SQUARE_SIZE/2,
				currentX+DEFAULT_SQUARE_SIZE/2, currentY-DEFAULT_SQUARE_SIZE/2, currentWeight);
		myDrawLine((Graphics2D) g, currentX-DEFAULT_SQUARE_SIZE/2, currentY-DEFAULT_SQUARE_SIZE/2,
				currentX+DEFAULT_SQUARE_SIZE/2, currentY-DEFAULT_SQUARE_SIZE/2, currentWeight);
	}

	private void myDrawLine(Graphics2D g, int x1, int y1, int x2, int y2, int weight){
		g.setColor(selectedSettings.getCurrentColor());
		g.setStroke(new BasicStroke(weight));
		g.draw(new Line2D.Float(x1, y1, x2, y2));
	}
}