import java.awt.*;

import javax.swing.*;

public class FrameWork extends JFrame
{

	private int minWindowWidth = 640;
	private int minWindowHeight = 480;

	public static void main(String[] args) {
		new FrameWork();
	}
	
	FrameWork()
	{
		super("ICG test");
		//set window's size
		setPreferredSize(new Dimension(minWindowWidth, minWindowHeight));
		setMinimumSize(new Dimension(minWindowWidth, minWindowHeight));
		setResizable(true);
		//set window's position
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)((screenSize.getWidth()-minWindowWidth)/2), (int)((screenSize.getHeight()-minWindowHeight)/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//set image panel
		SelectedSettings defaultColor = new SelectedSettings(new Color(0, 0, 0));
		ImagePanel p = new ImagePanel(defaultColor);
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
		fileMenu.addMenuItem("Open", () -> System.out.println("Открыть файл"));
		fileMenu.setToolTipText("Actions and settings with file");
		fileMenu.addMenuItem("Save", () -> System.out.println("Сохранить файл"));
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
