import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ToolBarMenu extends JToolBar {

    private int btnSize = 50;

    private ImagePanel imagePanel;
    private ColorHolder colorHolder;

    public ToolBarMenu(ImagePanel imagePanel, ColorHolder colorHolder) {
        this.imagePanel = imagePanel;
        this.colorHolder = colorHolder; // Инициализация colorHolder

        // Кнопка для прямой линии
        JButton lineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_icon.jpg"))));
        lineBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        lineBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        lineBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        this.add(lineBtn);

        // Кнопка для кривой/волнистой линии
        JButton wavedLineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("waved_line_icon.jpg"))));
        wavedLineBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        wavedLineBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        wavedLineBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        this.add(wavedLineBtn);

        // Кнопка для заливки
        JButton fillBtn = new JButton("filling");
        fillBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        fillBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        fillBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        this.add(fillBtn);

        // Кнопка для очистки
        JButton clearBtn = new JButton("clear");
        clearBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        clearBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        clearBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        clearBtn.addActionListener(ev -> this.imagePanel.clean());
        this.add(clearBtn);

        // Панель для цветовых кнопок
        JPanel colorsGrid = new JPanel();
        colorsGrid.setLayout(new BoxLayout(colorsGrid, BoxLayout.Y_AXIS));

        // Первый ряд цветов
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));

        // Черный цвет
        JButton blackBtn = createColorButton(new Color(0, 0, 0));
        row1.add(blackBtn);

        // Красный цвет
        JButton redBtn = createColorButton(new Color(255, 0, 0));
        row1.add(redBtn);

        // Желтый цвет
        JButton redGreenBtn = createColorButton(new Color(255, 255, 0));
        row1.add(redGreenBtn);

        // Зеленый цвет
        JButton greenBtn = createColorButton(new Color(0, 255, 0));
        row1.add(greenBtn);

        // Второй ряд цветов
        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));

        // Голубой цвет
        JButton greenBlueBtn = createColorButton(new Color(0, 255, 255));
        row2.add(greenBlueBtn);

        // Синий цвет
        JButton blueBtn = createColorButton(new Color(0, 0, 255));
        row2.add(blueBtn);

        // Пурпурный цвет
        JButton redBlueBtn = createColorButton(new Color(255, 0, 255));
        row2.add(redBlueBtn);

        // Белый цвет
        JButton whiteBtn = createColorButton(new Color(255, 255, 255));
        row2.add(whiteBtn);

        // Добавляем ряды в сетку
        colorsGrid.add(row1);
        colorsGrid.add(row2);
        this.add(colorsGrid);

        // Кнопка для выбора произвольного цвета
        JButton colorSelectBtn = new JButton("other");
        colorSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора цвета
                Color selectedColor = JColorChooser.showDialog(
                        ToolBarMenu.this,
                        "Choose a Color",
                        colorSelectBtn.getBackground()
                );

                // Если пользователь выбрал цвет (не нажал "Cancel")
                if (selectedColor != null) {
                    colorSelectBtn.setBackground(selectedColor);
                    colorHolder.setCurrentColor(selectedColor); // Обновляем цвет в ColorHolder
                }
            }
        });
        colorSelectBtn.setPreferredSize(new Dimension(2 * btnSize, btnSize));
        colorSelectBtn.setMinimumSize(new Dimension(2 * btnSize, btnSize));
        colorSelectBtn.setMaximumSize(new Dimension(2 * btnSize, btnSize));
        this.add(colorSelectBtn);

        // Настройки панели инструментов
        this.setFloatable(false);
        this.setRollover(false);
        this.add(Box.createGlue());
    }

    // Метод для создания цветной кнопки
    private JButton createColorButton(Color color) {
        JButton button = new JButton("");
        button.setPreferredSize(new Dimension(btnSize / 2, btnSize / 2));
        button.setMinimumSize(new Dimension(btnSize / 2, btnSize / 2));
        button.setMaximumSize(new Dimension(btnSize / 2, btnSize / 2));
        button.setBackground(color);

        // Устанавливаем обработчик для изменения цвета
        button.addActionListener(e -> colorHolder.setCurrentColor(color));
        return button;
    }

    private void toggleColorSelecter() {
        // Логика для выбора цвета (если требуется)
    }
}