import javax.swing.*;

public class MainMenuPanel extends JMenu {

    // Конструктор для создания пункта меню с именем
    public MainMenuPanel(String pointName) {
        super(pointName); // Передаем имя пункта меню в конструктор JMenu
    }

    // Метод для добавления подпункта в меню
    public void addMenuItem(String itemName, Runnable action) {
        JMenuItem menuItem = new JMenuItem(itemName);
        menuItem.addActionListener(e -> action.run()); // Добавляем действие при выборе пункта
        this.add(menuItem); // Добавляем подпункт в меню
    }

}
