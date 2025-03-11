import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener {

	private boolean draw = true;
	private int x, y, xd = -1, yd = -1;
	private BufferedImage currentImage;

	int DEFAULT_TRIANGLE_SIZE = 200;
	int DEFAULT_SQUARE_SIZE = 200;
	private SelectedSettings selectedSettings;
	private int FIGURE_SIZE;
	private int FIGURE_ROTATE;
	private int FIGURE_COUNT_CORNER;
	public Boolean areSettingForm;

	private BufferedImage exampleImage; // Изображение для примера фигуры

	public ImagePanel() {
		currentImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		exampleImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		clearExampleImage();
	}

	public ImagePanel(SelectedSettings selectedSettings, int figureSize, int figureRotate, int figureCorners) {
		this();
		this.areSettingForm = false;
		this.selectedSettings = selectedSettings;
		this.FIGURE_SIZE = figureSize;
		this.FIGURE_ROTATE = figureRotate;
		this.FIGURE_COUNT_CORNER = figureCorners;
	}

	public ImagePanel(SelectedSettings selectedSettings) {
		this();
		this.areSettingForm = false;
		this.selectedSettings = selectedSettings;
		addMouseListener(this);

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (selectedSettings.getCurrentMode().equals("WAVED_LINE_MODE")) {
					if (xd >= 0) {
						Graphics2D g2d = currentImage.createGraphics();
						g2d.setColor(selectedSettings.getCurrentColor());
						g2d.setStroke(new BasicStroke(selectedSettings.getCurrentWeight()));
						if ((selectedSettings.getCurrentWeight()) > 1)
							g2d.drawLine(xd, yd, e.getX(), e.getY());
						else bresenhemDrawLine(xd, yd, e.getX(), e.getY());
						g2d.dispose();
						repaint();
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

	private void bresenhemDrawLine(int x0, int y0, int x1, int y1) {
		if (currentImage == null) return; // Проверка на null

		Graphics2D g2d = currentImage.createGraphics();
		g2d.setColor(selectedSettings.getCurrentColor());

		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);

		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;

		int err = dx - dy;

		while (true) {
			g2d.fillRect(x0, y0, 1, 1);
			if (x0 == x1 && y0 == y1) break;
			int e2 = 2 * err;
			if (e2 > -dy) {
				err -= dy;
				x0 += sx;
			}
			if (e2 < dx) {
				err += dx;
				y0 += sy;
			}
		}
		g2d.dispose();
		repaint();
	}

	public void setFigureParameters(int figureSize, int figureRotate, int figureCorners) {
		this.FIGURE_SIZE = figureSize;
		this.FIGURE_ROTATE = figureRotate;
		this.FIGURE_COUNT_CORNER = figureCorners;
	}



	private void clearExampleImage() {
		Graphics2D g2d = exampleImage.createGraphics();
		g2d.setColor(getBackground()); // Заливаем фоновым цветом
		g2d.fillRect(0, 0, exampleImage.getWidth(), exampleImage.getHeight());
		g2d.dispose();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Отрисовка основного изображения
		if (currentImage != null) {
			g.drawImage(currentImage, 0, 0, this);
		}

		// Отрисовка примера фигуры
		if (areSettingForm) {
			g.drawImage(exampleImage, 0, 0, this);
		}
	}


	public void clean() {
		draw = !draw;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		if (selectedSettings.getCurrentMode().equals("FILL_MODE")) {
			spanFill(ev.getX(), ev.getY());
		}
	}

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
		if (currentImage == null) return; // Проверка на null

		if (selectedSettings.getCurrentMode().equals("STRAIGHT_LINE_MODE")) {
			if (selectedSettings.getCurrentWeight() > 1) {
				bresenhemDrawLine(x, y, ev.getX(), ev.getY());
			} else {
				Graphics2D g2d = currentImage.createGraphics();
				myDrawLine(g2d, x, y, ev.getX(), ev.getY(), selectedSettings.getCurrentWeight());
				g2d.dispose();
				repaint();
			}
		}
		if (selectedSettings.getCurrentMode().equals("FUN_MODE")) {
			drawFunMode(ev);
		}

		if (selectedSettings.getCurrentMode().equals("FIGURE_MODE")) {
			drawFigure(ev);
		}
		if (selectedSettings.getCurrentMode().equals("FILL_MODE")) {
			spanFill(ev.getX(), ev.getY());
		}
	}

	private void spanFill(int x, int y) {
		if (currentImage == null) {
			System.out.println("BufferedImage is null");
			return;
		}

		// Получаем цвет заливки
		Color targetColor = this.selectedSettings.getCurrentColor();

		// Получаем цвет начальной точки
		Color startColor = new Color(currentImage.getRGB(x, y), true); // Учитываем прозрачность

		// Если цвет заливки совпадает с начальным цветом, заливка не требуется
		if (startColor.equals(targetColor)) {
			//System.out.println("Цвета совпадают, заливка не требуется");
			return;
		}

		// Стек для хранения точек, которые нужно обработать
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[]{x, y});

		// Основной цикл заливки
		while (!stack.isEmpty()) {
			int[] span = stack.pop();
			int startX = span[0];
			int currentY = span[1];

			// Находим начало и конец спана
			int leftX = startX;
			while (leftX >= 0 && isSameColor(leftX, currentY, startColor)) {
				leftX--;
			}
			leftX++;

			int rightX = startX;
			while (rightX < currentImage.getWidth() && isSameColor(rightX, currentY, startColor)) {
				rightX++;
			}
			rightX--;

			// Закрашиваем спан
			Graphics2D g2d = currentImage.createGraphics();
			g2d.setColor(targetColor);
			g2d.drawLine(leftX, currentY, rightX, currentY);
			g2d.dispose();

			// Проверяем строку выше
			if (currentY > 0) {
				checkSpan(leftX, rightX, currentY - 1, startColor, stack);
			}

			// Проверяем строку ниже
			if (currentY < currentImage.getHeight() - 1) {
				checkSpan(leftX, rightX, currentY + 1, startColor, stack);
			}
		}

		// Перерисовываем панель
		repaint();
	}

	// Проверяет, совпадает ли цвет пикселя с начальным цветом
	private boolean isSameColor(int x, int y, Color startColor) {
		if (x < 0 || y < 0 || x >= currentImage.getWidth() || y >= currentImage.getHeight()) {
			return false; // Выход за границы изображения
		}
		Color pixelColor = new Color(currentImage.getRGB(x, y), true); // Учитываем прозрачность
		return pixelColor.equals(startColor);
	}

	// Проверяет строку выше или ниже на наличие пикселей для заливки
	private void checkSpan(int leftX, int rightX, int y, Color startColor, Stack<int[]> stack) {
		for (int i = leftX; i <= rightX; i++) {
			if (isSameColor(i, y, startColor)) {
				// Начало нового спана
				int startSpanX = i;
				while (i <= rightX && isSameColor(i, y, startColor)) {
					i++;
				}
				stack.push(new int[]{startSpanX, y});
			}
		}
	}

	private void drawFunMode(MouseEvent ev) {
		if (currentImage == null) return; // Проверка на null

		int currentX = ev.getX();
		int currentY = ev.getY();

		Graphics2D g2d = currentImage.createGraphics();
		if (selectedSettings.getCurrentWeight() > 1)
			bresenhemDrawLine(currentX - DEFAULT_TRIANGLE_SIZE * 2 / 3, currentY + DEFAULT_TRIANGLE_SIZE / 3,
					currentX, currentY - DEFAULT_TRIANGLE_SIZE * 2 / 3);
		else
			myDrawLine(g2d,
					currentX - DEFAULT_TRIANGLE_SIZE * 2 / 3, currentY + DEFAULT_TRIANGLE_SIZE / 3,
					currentX, currentY - DEFAULT_TRIANGLE_SIZE * 2 / 3,
					selectedSettings.getCurrentWeight());
		g2d.dispose();
		repaint();
	}

	public void drawExampleFigure(){
		repaint();
		clearExampleImage(); // Очищаем изображение перед рисованием новой фигуры

		if (currentImage == null) return; // Проверка на null

		int currentX = getWidth() / 2;
		int currentY = getHeight() / 2;
		int corners = this.selectedSettings.getFigureCorners();

		int[] xPoints = new int[corners];
		int[] yPoints = new int[corners];

		double angleStep = 2 * Math.PI / corners;

		for (int i = 0; i < corners; i++) {
			double angle = i * angleStep;
			xPoints[i] = (int) (currentX + this.FIGURE_SIZE / 2 * Math.cos(angle));
			yPoints[i] = (int) (currentY + this.FIGURE_SIZE / 2 * Math.sin(angle));
		}

		int[] xPointsRotated = new int[xPoints.length];
		int[] yPointsRotated = new int[yPoints.length];

		double radians = Math.toRadians(this.FIGURE_ROTATE);
		for (int i = 0; i < xPoints.length; i++) {
			int x = xPoints[i] - currentX;
			int y = yPoints[i] - currentY;
			xPointsRotated[i] = (int) (x * Math.cos(radians) - y * Math.sin(radians) + currentX);
			yPointsRotated[i] = (int) (x * Math.sin(radians) + y * Math.cos(radians) + currentY);
		}

		Graphics2D g2d = currentImage.createGraphics();
		g2d.setColor(this.selectedSettings.getCurrentColor());
		g2d.setStroke(new BasicStroke(selectedSettings.getCurrentWeight()));
		g2d.drawPolygon(xPointsRotated, yPointsRotated, corners);
		g2d.dispose();
		repaint();
	}

	private void drawFigure(MouseEvent ev) {
		if (currentImage == null) {
			System.out.println("currentImage is null");
			return;
		}

		int currentX = ev.getX();
		int currentY = ev.getY();
		int corners = this.selectedSettings.getFigureCorners();

		int[] xPoints = new int[corners];
		int[] yPoints = new int[corners];

		double angleStep = 2 * Math.PI / corners;

		for (int i = 0; i < corners; i++) {
			double angle = i * angleStep;
			xPoints[i] = (int) (currentX + this.FIGURE_SIZE / 2 * Math.cos(angle));
			yPoints[i] = (int) (currentY + this.FIGURE_SIZE / 2 * Math.sin(angle));
		}

		int[] xPointsRotated = new int[xPoints.length];
		int[] yPointsRotated = new int[yPoints.length];

		double radians = Math.toRadians(this.FIGURE_ROTATE);
		for (int i = 0; i < xPoints.length; i++) {
			int x = xPoints[i] - currentX;
			int y = yPoints[i] - currentY;
			xPointsRotated[i] = (int) (x * Math.cos(radians) - y * Math.sin(radians) + currentX);
			yPointsRotated[i] = (int) (x * Math.sin(radians) + y * Math.cos(radians) + currentY);
		}

		Graphics2D g2d = currentImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.selectedSettings.getCurrentColor());
		g2d.setStroke(new BasicStroke(selectedSettings.getCurrentWeight()));
		g2d.drawPolygon(xPointsRotated, yPointsRotated, corners);
		g2d.dispose();

		repaint();
	}

	private void myDrawLine(Graphics2D g, int x1, int y1, int x2, int y2, int weight) {
		g.setColor(selectedSettings.getCurrentColor());
		g.setStroke(new BasicStroke(weight));
		g.draw(new Line2D.Float(x1, y1, x2, y2));
	}

	public void getMessage(String typeOfMsg, String text, String title) {
		if (typeOfMsg.equals("info")) {
			InformationMessages informationMessages = new InformationMessages();
			informationMessages.showInformationMessage(this, text, title);
		}
	}

	public void saveAsImage(String filePath, String format) {
		try {
			ImageIO.write(currentImage, format, new File(filePath));
			System.out.println("Изображение успешно сохранено в " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Ошибка при сохранении изображения: " + e.getMessage());
		}
	}

	public void openImage(String filePath) {
		try {
			currentImage = ImageIO.read(new File(filePath));
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Ошибка при открытии изображения: " + e.getMessage());
		}
	}
}