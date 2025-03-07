import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ToolBarMenu extends JToolBar {

    private final int btnSize = 50;
    private final int minSelecterHeight = 400;
    private final int minSelecterWidth = 300;

    private final ImagePanel imagePanel;
    private final SelectedSettings selectedSettings;

    public ToolBarMenu(ImagePanel imagePanel, SelectedSettings selectedSettings) {
        this.imagePanel = imagePanel;
        this.selectedSettings = selectedSettings;

        // Кнопка для прямой линии
        JButton lineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_icon.jpg"))));
        lineBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        lineBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        lineBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        lineBtn.addActionListener(e -> selectedSettings.setStraightLineMode());
        this.add(lineBtn);

        // Кнопка для кривой/волнистой линии
        JButton wavedLineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("waved_line_icon.jpg"))));
        wavedLineBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        wavedLineBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        wavedLineBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        wavedLineBtn.addActionListener(e -> selectedSettings.setWavedLineMode());
        this.add(wavedLineBtn);

        this.addSeparator();

        // Кнопка для заливки
        JButton fillBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("filling_icon.jpg"))));
        fillBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        fillBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        fillBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        this.add(fillBtn);

        // Кнопка для очистки
        JButton clearBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("clear_icon.jpg"))));
        clearBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        clearBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        clearBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        clearBtn.addActionListener(ev -> this.imagePanel.clean());
        this.add(clearBtn);


        JButton funBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("fun_icon.jpg"))));
        funBtn.setPreferredSize(new Dimension(btnSize, btnSize));
        funBtn.setMinimumSize(new Dimension(btnSize, btnSize));
        funBtn.setMaximumSize(new Dimension(btnSize, btnSize));
        funBtn.addActionListener(ev -> selectedSettings.setFunPatternMode());
        this.add(funBtn);


        this.addSeparator();

        // настройки для штампов
        JPanel patternsGrid = new JPanel();
        patternsGrid.setLayout(new BoxLayout(patternsGrid, BoxLayout.X_AXIS));
        createPatternsGrid(patternsGrid);
        this.add(patternsGrid);

        this.addSeparator();

        JPanel weightsGrid = new JPanel();
        weightsGrid.setLayout(new BoxLayout(weightsGrid, BoxLayout.Y_AXIS));
        JButton lightWeightLineBtn = new JButton("light");
        Dimension weightBtnSize = new Dimension(btnSize*2, btnSize/3);
        lightWeightLineBtn.setPreferredSize(weightBtnSize);
        lightWeightLineBtn.setMinimumSize(weightBtnSize);
        lightWeightLineBtn.setMaximumSize(weightBtnSize);
        weightsGrid.add(lightWeightLineBtn);
        JButton mediumWeightLineBtn = new JButton("medium");
        mediumWeightLineBtn.setPreferredSize(weightBtnSize);
        mediumWeightLineBtn.setMinimumSize(weightBtnSize);
        mediumWeightLineBtn.setMaximumSize(weightBtnSize);
        weightsGrid.add(mediumWeightLineBtn);
        JButton boldWeightLineBtn = new JButton("bold");
        boldWeightLineBtn.setPreferredSize(weightBtnSize);
        boldWeightLineBtn.setMinimumSize(weightBtnSize);
        boldWeightLineBtn.setMaximumSize(weightBtnSize);
        weightsGrid.add(boldWeightLineBtn);

        this.add(weightsGrid);
        this.addSeparator();

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
        JButton colorSelectBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("palitra_icon.jpg"))));
        colorSelectBtn.setPreferredSize(new Dimension(btnSize-20, btnSize-20));
        colorSelectBtn.setMinimumSize(new Dimension(btnSize-20, btnSize-20));
        colorSelectBtn.setMaximumSize(new Dimension(btnSize-20, btnSize-20));
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
                    selectedSettings.setCurrentColor(selectedColor); // Обновляем цвет в ColorHolder
                }
            }
        });
        this.add(colorSelectBtn);


        JPanel widthsGrid = new JPanel();
        widthsGrid.setLayout(new BoxLayout(colorsGrid, BoxLayout.Y_AXIS));
        //JButton width
        //this.add(widthsGrid);

        // Настройки панели инструментов
        this.setFloatable(false);
        this.setRollover(false);
        this.add(Box.createGlue());
    }

    private void createPatternsGrid(JPanel patternsGrid) {
        JButton triangleBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("triangle_icon.jpg"))));
        setSizeBtn(triangleBtn);
        triangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                JFrame triangleFrame = new JFrame("Choose triangle settings");
                setPatternsSettingsFrame(triangleFrame);

                JLabel labelSize = new JLabel("Choose size:");
                JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
                triangleFrame.add(labelSize);
                triangleFrame.add(slider);
                //triangleFrame.add();

                triangleFrame.pack();
                triangleFrame.setVisible(true);
                selectedSettings.setTrianglePatternMode(); // Обновляем цвет в ColorHolder

            }
        });
        patternsGrid.add(triangleBtn);

        JButton squareBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("square_icon.jpg"))));
        setSizeBtn(squareBtn);
        squareBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                JFrame squareFrame = new JFrame("Choose square settings");
                setPatternsSettingsFrame(squareFrame);

                squareFrame.pack();
                squareFrame.setVisible(true);
                selectedSettings.setSquarePatternMode(); // Обновляем цвет в ColorHolder

            }
        });
        patternsGrid.add(squareBtn);
    }

    // Метод для создания цветной кнопки
    private JButton createColorButton(Color color) {
        JButton button = new JButton("");
        button.setPreferredSize(new Dimension(btnSize / 2, btnSize / 2));
        button.setMinimumSize(new Dimension(btnSize / 2, btnSize / 2));
        button.setMaximumSize(new Dimension(btnSize / 2, btnSize / 2));
        button.setBackground(color);

        // Устанавливаем обработчик для изменения цвета
        button.addActionListener(e -> selectedSettings.setCurrentColor(color));
        return button;
    }

    private JButton setSizeBtn(JButton btn) {
        btn.setPreferredSize(new Dimension(btnSize, btnSize));
        btn.setMinimumSize(new Dimension(btnSize, btnSize));
        btn.setMaximumSize(new Dimension(btnSize, btnSize ));

        return btn;
    }

    private JFrame setPatternsSettingsFrame(JFrame frame) {
        frame.setPreferredSize(new Dimension(minSelecterWidth, minSelecterHeight));
        frame.setMinimumSize(new Dimension(minSelecterWidth, minSelecterHeight));
        frame.setResizable(false);

        //set window's position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)((screenSize.getWidth()-minSelecterWidth)/2),
                (int)((screenSize.getHeight()-minSelecterHeight)/2));

        return frame;
    }
}