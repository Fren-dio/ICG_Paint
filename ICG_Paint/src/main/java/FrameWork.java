import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameWork extends JFrame
{

	private ImagePanel imagePanel;

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
		SelectedSettings defaultColor = new SelectedSettings(new Color(0, 0, 0));
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

	public void addMainMenu() {
		// Создаем меню бар
		JMenuBar menuBar = new JMenuBar();

		MainMenuPanel aboutMenu = new MainMenuPanel("About");
		aboutMenu.addMenuItem("About", () -> System.out.println("get about"));

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

		// Создаем пункт меню "Настройки"
		MainMenuPanel settingsMenu = new MainMenuPanel("Settings");
		settingsMenu.addMenuItem("Settings", () -> System.out.println("Открыть настройки"));

		// Добавляем пункты меню в меню бар
		menuBar.add(aboutMenu);
		menuBar.add(fileMenu);
		menuBar.add(settingsMenu);

		// Устанавливаем меню бар в окно
		setJMenuBar(menuBar);
	}

}
