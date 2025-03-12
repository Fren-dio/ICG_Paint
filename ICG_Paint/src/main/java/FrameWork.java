import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameWork extends JFrame
{

	private ImagePanel imagePanel;
	private SelectedSettings selectedSettings;

	public static void main(String[] args) {
		new FrameWork();
	}
	
	FrameWork()
	{
		super("ICG test");
		//set window's size
		int minWindowWidth = 640;
		int minWindowHeight = 480;
		int windowWidth = 640+300;
		int windowHeight = 480+200;
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		setMinimumSize(new Dimension(minWindowWidth, minWindowHeight));
		setResizable(true);
		//set window's position
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)((screenSize.getWidth()- windowWidth)/2), (int)((screenSize.getHeight()- windowHeight)/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//set image panel
		SelectedSettings defaultColor = new SelectedSettings(Color.lightGray);
		this.selectedSettings = defaultColor;
		ImagePanel p = new ImagePanel(defaultColor);
		this.imagePanel = p;
		add(p);

		//set inform menu
		addMainMenu();

		// set tool bar menu
		BoxLayoutUtils blUtils = new BoxLayoutUtils();
		JPanel utilsPanel = blUtils.createHorizontalPanel();
		ToolBarMenu toolBarMenu = new ToolBarMenu(p, defaultColor);
		utilsPanel.add(toolBarMenu);
		getContentPane().add(utilsPanel, "North");
		
		pack();
		setVisible(true);
	}

	private void showAboutDialog() {
		String aboutMessage =
				"Программа: Графический редактор\n" +
						"Версия: 1.0\n" +
						"Автор: Кулишова Анастасия\n\n" +
						"Основные особенности:\n" +
						"- Редактор позволяет рисовать линии, фигуры и закрашивать области.\n" +
						"- Редактор позволяет изменять цвет и толщину линий.\n" +
						"- Редактор позволяет сохранять и открывать изображений.\n\n" +
						"Подсказки:\n" +
						"- Сохраняйте свои работы и открывайте изображения с помощью меню 'File'\n" +
						"- Выбирайте режим рисования (линия/заливка/т.д.) с помощью 'Modes'\n" +
						"- Выбирайте цвет для рисования с помощью 'Colors'\n" +
						"- Выбирайте толщину линий с помощью 'Bolds'\n" +
						"- Выбирайте шаблон для рисования фигуры с помощью 'Patterns'\n" +
						"- Очищайте изображение с помощью 'Clean'.";

		JOptionPane.showMessageDialog(
				this, // Родительское окно
				aboutMessage, // Сообщение
				"О программе", // Заголовок окна
				JOptionPane.INFORMATION_MESSAGE // Тип сообщения
		);
	}

	public void addMainMenu() {
		// Создаем меню бар
		JMenuBar menuBar = new JMenuBar();

		MainMenuPanel aboutMenu = new MainMenuPanel("About");
		aboutMenu.addMenuItem("About", () -> showAboutDialog());

		MainMenuPanel fileMenu = new MainMenuPanel("File");
		fileMenu.addMenuItem("Open", () -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open File");
			fileChooser.setCurrentDirectory(new File("C:/Users/kulma/IdeaProjects/ICG_Paint/images"));

			// Добавляем фильтры для форматов файлов
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg", "bmp", "gif"));

			int userSelection = fileChooser.showOpenDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToOpen = fileChooser.getSelectedFile();
				imagePanel.openImage(fileToOpen.getAbsolutePath());

			}
		});

		fileMenu.setToolTipText("Actions and settings with file");
		fileMenu.addMenuItem("Save", () -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save As");
			fileChooser.setCurrentDirectory(new File("C:/Users/kulma/IdeaProjects/ICG_Paint/images"));

			// Добавляем фильтры для форматов файлов
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Images", "png"));

			int userSelection = fileChooser.showSaveDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				String filePath = fileToSave.getAbsolutePath();

				// Убедимся, что файл имеет правильное расширение
				if (!filePath.toLowerCase().endsWith(".png")) {
					filePath += ".png";
				}

				imagePanel.saveAsImage(filePath, "png");
			}
		});

		fileMenu.addSeparator(); // Добавляем разделитель
		fileMenu.addMenuItem("Exit", () -> System.exit(0)); // Выход из программы


		MainMenuPanel toolsMenu = new MainMenuPanel("Modes");
		toolsMenu.addMenuItem("Straight line", () -> selectedSettings.setStraightLineMode());
		toolsMenu.addMenuItem("Waved line", () -> selectedSettings.setWavedLineMode());
		toolsMenu.addMenuItem("Fun mode", () -> selectedSettings.setFunPatternMode());
		toolsMenu.addMenuItem("Fill mode", () -> selectedSettings.setFillMode());


		MainMenuPanel colorsMenu = new MainMenuPanel("Colors");
		colorsMenu.addMenuItem("Red", () -> selectedSettings.setCurrentColor(new Color(255, 0, 0)));
		colorsMenu.addMenuItem("Green", () -> selectedSettings.setCurrentColor(new Color(0, 255, 0)));
		colorsMenu.addMenuItem("Blue", () -> selectedSettings.setCurrentColor(new Color(0, 0, 255)));
		colorsMenu.addMenuItem("Yellow", () -> selectedSettings.setCurrentColor(new Color(255, 255, 0)));
		colorsMenu.addMenuItem("Purple", () -> selectedSettings.setCurrentColor(new Color(255, 0, 255)));
		colorsMenu.addMenuItem("Laureate", () -> selectedSettings.setCurrentColor(new Color(0, 255, 255)));
		colorsMenu.addMenuItem("Black", () -> selectedSettings.setCurrentColor(new Color(0, 0, 0)));
		colorsMenu.addMenuItem("White", () -> selectedSettings.setCurrentColor(new Color(255, 255, 255)));
		colorsMenu.addMenuItem("Other", () -> {
				Color selectedColor = JColorChooser.showDialog(
						this,
						"Choose a Color",
						null
				);

				// Если пользователь выбрал цвет (не нажал "Cancel")
				if (selectedColor != null) {
					selectedSettings.setCurrentColor(selectedColor); // Обновляем цвет в ColorHolder
				}
		});

		MainMenuPanel boldsMenu = new MainMenuPanel("Bolds");
		boldsMenu.addMenuItem("Light", () -> selectedSettings.setLightWeight());
		boldsMenu.addMenuItem("Medium", () -> selectedSettings.setMediumWeight());
		boldsMenu.addMenuItem("Bold", () -> selectedSettings.setBoldWeight());


		MainMenuPanel patternsMenu = new MainMenuPanel("Patterns");
		patternsMenu.addMenuItem("Triangle", () -> {
			int corners = 3;
			ToolBarMenu toolBarMenu = new ToolBarMenu(this.imagePanel, this.selectedSettings);
			toolBarMenu.createChosenWindow(corners, "Triangle Settings");
			selectedSettings.setFigurePatternMode(corners);
		});
		patternsMenu.addMenuItem("Square", () -> {
			int corners = 4;
			ToolBarMenu toolBarMenu = new ToolBarMenu(this.imagePanel, this.selectedSettings);
			toolBarMenu.createChosenWindow(corners, "Square Settings");
			selectedSettings.setFigurePatternMode(corners);
		});
		patternsMenu.addMenuItem("Five polygon", () -> {
			int corners = 5;
			ToolBarMenu toolBarMenu = new ToolBarMenu(this.imagePanel, this.selectedSettings);
			toolBarMenu.createChosenWindow(corners, "Five polygon Settings");
			selectedSettings.setFigurePatternMode(corners);
		});
		patternsMenu.addMenuItem("Six polygon", () -> {
			int corners = 6;
			ToolBarMenu toolBarMenu = new ToolBarMenu(this.imagePanel, this.selectedSettings);
			toolBarMenu.createChosenWindow(corners, "Six polygon Settings");
			selectedSettings.setFigurePatternMode(corners);
		});
		patternsMenu.addMenuItem("Other polygon", () -> {
			ToolBarMenu toolBarMenu = new ToolBarMenu(this.imagePanel, this.selectedSettings);
			int corners = toolBarMenu.showPolygonCornersDialog();
			if (corners != -1) {
				toolBarMenu.createChosenWindow(corners, "Polygon Settings");
				selectedSettings.setFigurePatternMode(corners);
			}
		});

		MainMenuPanel clearMenu = new MainMenuPanel("Clean");
		clearMenu.addMenuItem("Clean", () -> this.imagePanel.clear());

		// Добавляем пункты меню в меню бар
		menuBar.add(aboutMenu);
		menuBar.add(fileMenu);
		menuBar.add(toolsMenu);
		menuBar.add(colorsMenu);
		menuBar.add(boldsMenu);
		menuBar.add(patternsMenu);
		menuBar.add(clearMenu);

		// Устанавливаем меню бар в окно
		setJMenuBar(menuBar);
	}

}
