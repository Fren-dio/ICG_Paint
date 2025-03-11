import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class ToolBarMenu extends JToolBar {

    private final int btnSize = 50;

    private final ImagePanel imagePanel;
    private final SelectedSettings selectedSettings;

    public ToolBarMenu(ImagePanel imagePanel, SelectedSettings selectedSettings) {
        this.imagePanel = imagePanel;
        this.selectedSettings = selectedSettings;

        // Кнопка для прямой линии
        JButton lineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_icon.jpg"))));
        setSizeSquareBtn(lineBtn, btnSize);
        lineBtn.setToolTipText("Draw straight line.");
        lineBtn.addActionListener(e -> selectedSettings.setStraightLineMode());
        this.add(lineBtn);

        // Кнопка для кривой/волнистой линии
        JButton wavedLineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("waved_line_icon.jpg"))));
        setSizeSquareBtn(wavedLineBtn, btnSize);
        wavedLineBtn.setToolTipText("Draw waved line, with any rotate and length.");
        wavedLineBtn.addActionListener(e -> selectedSettings.setWavedLineMode());
        this.add(wavedLineBtn);

        this.addSeparator();

        // Кнопка для заливки
        JButton fillBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("filling_icon.jpg"))));
        setSizeSquareBtn(fillBtn, btnSize);
        fillBtn.setToolTipText("Filling area by chosen color");
        fillBtn.addActionListener(ev -> selectedSettings.setFillMode());
        this.add(fillBtn);

        // Кнопка для очистки
        JButton clearBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("clear_icon.jpg"))));
        setSizeSquareBtn(clearBtn, btnSize);
        clearBtn.setToolTipText("Clear all area.");
        clearBtn.addActionListener(ev -> this.imagePanel.clean());
        this.add(clearBtn);


        JButton funBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("fun_icon.jpg"))));
        setSizeSquareBtn(funBtn, btnSize);
        funBtn.setToolTipText("Just press left mouse button and move your mouse.");
        funBtn.addActionListener(ev -> selectedSettings.setFunPatternMode());
        this.add(funBtn);


        this.addSeparator();

        // настройки для штампов
        JPanel patternsGrid = new JPanel();
        patternsGrid.setLayout(new BoxLayout(patternsGrid, BoxLayout.X_AXIS));
        createPatternsGrid(patternsGrid);
        this.add(patternsGrid);

        this.addSeparator();

        // panel with weight of line settings
        JPanel weightsGrid = new JPanel();
        weightsGrid.setLayout(new BoxLayout(weightsGrid, BoxLayout.Y_AXIS));
        JButton lightWeightLineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_weight_light.jpg"))));
        lightWeightLineBtn.setBackground(new Color(255, 255, 255));
        lightWeightLineBtn.setToolTipText("Line weight = 1. 'Light' weight.");
        Dimension weightBtnSize = new Dimension(btnSize*2, btnSize/3);
        setDimensionBtn(lightWeightLineBtn, weightBtnSize);
        lightWeightLineBtn.addActionListener(e -> selectedSettings.setLightWeight());
        weightsGrid.add(lightWeightLineBtn);

        JButton mediumWeightLineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_weight_medium.jpg"))));
        mediumWeightLineBtn.setBackground(new Color(255, 255, 255));
        mediumWeightLineBtn.setToolTipText("Line weight = 3. 'Medium' weight.");
        setDimensionBtn(mediumWeightLineBtn, weightBtnSize);
        mediumWeightLineBtn.addActionListener(e -> selectedSettings.setMediumWeight());
        weightsGrid.add(mediumWeightLineBtn);

        JButton boldWeightLineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_weight_bold.jpg"))));
        boldWeightLineBtn.setBackground(new Color(255, 255, 255));
        boldWeightLineBtn.setToolTipText("Line weight = 6. 'Bold' weight.");
        setDimensionBtn(boldWeightLineBtn, weightBtnSize);
        boldWeightLineBtn.addActionListener(e -> selectedSettings.setBoldWeight());
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
        blackBtn.setToolTipText("Black color. RGB=(0,0,0)");
        row1.add(blackBtn);

        // Красный цвет
        JButton redBtn = createColorButton(new Color(255, 0, 0));
        blackBtn.setToolTipText("Red color. RGB=(255,0,0)");
        row1.add(redBtn);

        // Желтый цвет
        JButton redGreenBtn = createColorButton(new Color(255, 255, 0));
        blackBtn.setToolTipText("Yellow color. RGB=(255,255,0)");
        row1.add(redGreenBtn);

        // Зеленый цвет
        JButton greenBtn = createColorButton(new Color(0, 255, 0));
        blackBtn.setToolTipText("Green color. RGB=(0,255,0)");
        row1.add(greenBtn);

        // Второй ряд цветов
        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));

        // Голубой цвет
        JButton greenBlueBtn = createColorButton(new Color(0, 255, 255));
        blackBtn.setToolTipText("Azure color. RGB=(0,255,255)");
        row2.add(greenBlueBtn);

        // Синий цвет
        JButton blueBtn = createColorButton(new Color(0, 0, 255));
        blackBtn.setToolTipText("Blue color. RGB=(0,0,255)");
        row2.add(blueBtn);

        // Пурпурный цвет
        JButton redBlueBtn = createColorButton(new Color(255, 0, 255));
        blackBtn.setToolTipText("Purple color. RGB=(255,0,255)");
        row2.add(redBlueBtn);

        // Белый цвет
        JButton whiteBtn = createColorButton(new Color(255, 255, 255));
        blackBtn.setToolTipText("White color. RGB=(255,255,255)");
        row2.add(whiteBtn);

        // Добавляем ряды в сетку
        colorsGrid.add(row1);
        colorsGrid.add(row2);
        this.add(colorsGrid);

        // Кнопка для выбора произвольного цвета
        JButton colorSelectBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("palitra_icon.jpg"))));
        colorSelectBtn.setToolTipText("Select color, what you want.");
        setDimensionBtn(colorSelectBtn, new Dimension(btnSize-20, btnSize-20));
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


        // Настройки панели инструментов
        this.setFloatable(false);
        this.setRollover(false);
        this.add(Box.createGlue());
    }

    private void createPatternsGrid(JPanel patternsGrid) {
        JButton triangleBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("triangle_icon.jpg"))));
        triangleBtn.setToolTipText("Draw triangle by chosen color and weight.");
        setSizeSquareBtn(triangleBtn, (int)(btnSize*0.85));
        triangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                int corners = 3;
                createChosenWindow(corners, "Triangle Settings");
                selectedSettings.setFigurePatternMode(corners);

            }
        });
        patternsGrid.add(triangleBtn);

        JButton squareBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("square_icon.jpg"))));
        squareBtn.setToolTipText("Draw square by chosen color and weight.");
        setSizeSquareBtn(squareBtn, (int)(btnSize*0.85));
        squareBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                int corners = 4;
                JFrame squareFrame = new JFrame("Choose square settings");
                createChosenWindow(corners, "Five corners Settings");
                selectedSettings.setFigurePatternMode(corners);

            }
        });
        patternsGrid.add(squareBtn);


        JButton fiveBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("five_icon.jpg"))));
        fiveBtn.setToolTipText("Draw square by chosen color and weight.");
        setSizeSquareBtn(fiveBtn, (int)(btnSize*0.85));
        fiveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                int corners = 5;
                JFrame squareFrame = new JFrame("Choose square settings");
                createChosenWindow(corners, "Six corners Settings");
                selectedSettings.setFigurePatternMode(corners);

            }
        });
        patternsGrid.add(fiveBtn);

        JButton sixBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("six_icon.jpg"))));
        sixBtn.setToolTipText("Draw square by chosen color and weight.");
        setSizeSquareBtn(sixBtn, (int)(btnSize*0.85));
        sixBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                int corners = 6;
                JFrame squareFrame = new JFrame("Choose square settings");
                createChosenWindow(corners, "Square Settings");
                selectedSettings.setFigurePatternMode(corners);

            }
        });
        patternsGrid.add(sixBtn);
    }

    // Метод для создания цветной кнопки
    private JButton createColorButton(Color color) {
        JButton button = new JButton("");
        setDimensionBtn(button, new Dimension(btnSize / 2, btnSize / 2));
        button.setBackground(color);

        // Устанавливаем обработчик для изменения цвета
        button.addActionListener(e -> selectedSettings.setCurrentColor(color));
        return button;
    }

    private void setSizeSquareBtn(JButton btn, int size) {
        btn.setPreferredSize(new Dimension(size, size));
        btn.setMinimumSize(new Dimension(size, size));
        btn.setMaximumSize(new Dimension(size, size ));

    }

    private JButton setDimensionBtn(JButton btn, Dimension dim) {
        btn.setPreferredSize(dim);
        btn.setMinimumSize(dim);
        btn.setMaximumSize(dim);

        return btn;
    }

    private JFrame setPatternsSettingsFrame(JFrame frame) {
        int minSelecterWidth = 300;
        int minSelecterHeight = 400;
        frame.setPreferredSize(new Dimension(minSelecterWidth, minSelecterHeight));
        frame.setMinimumSize(new Dimension(minSelecterWidth, minSelecterHeight));
        frame.setResizable(false);

        //set window's position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)((screenSize.getWidth()- minSelecterWidth)/2),
                (int)((screenSize.getHeight()- minSelecterHeight)/2));

        return frame;
    }

    private void createChosenWindow(int corners, String formName) {
        int formSizeH = 300;
        int formSizeW = 500;
        JFrame figureSetSettingsFrame = new JFrame(formName);

        figureSetSettingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        figureSetSettingsFrame.setSize(formSizeW, formSizeH);

        // Установим позицию окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        figureSetSettingsFrame.setLocation(
                (int)((screenSize.getWidth() - formSizeW) / 2),
                (int)((screenSize.getHeight() - formSizeH) / 2)
        );

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Панель для отображения фигуры
        ImagePanel figurePanel = new ImagePanel(selectedSettings, 100, 0, corners); // Инициализация с начальными параметрами
        figurePanel.setPreferredSize(new Dimension(formSizeW - 200, formSizeH)); // Задаем размер панели
        mainPanel.add(figurePanel, BorderLayout.CENTER);

        // Панель настроек
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JLabel labelSize = new JLabel("Choose size:");
        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 5, 250, 125); // Уменьшили диапазон слайдера
        sizeSlider.setMajorTickSpacing(50);
        sizeSlider.setMinorTickSpacing(25);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        JLabel labelRotation = new JLabel("Choose rotation angle:");
        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 720, 0); // Уменьшили диапазон слайдера
        rotationSlider.setMajorTickSpacing(90);
        rotationSlider.setMinorTickSpacing(30);
        rotationSlider.setPaintTicks(true);
        rotationSlider.setPaintLabels(true);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> {
            figurePanel.setFigureParameters(sizeSlider.getValue(), rotationSlider.getValue(), corners);
            selectedSettings.setFigureSettings(sizeSlider.getValue(), rotationSlider.getValue());
            selectedSettings.setFigurePatternMode(corners);
            selectedSettings.setFigureMode();
            figurePanel.repaint(); // Перерисовываем панель
            JOptionPane.showMessageDialog(figureSetSettingsFrame, "Settings applied!");
        });

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            figurePanel.setFigureParameters(sizeSlider.getValue(), rotationSlider.getValue(), corners);
            selectedSettings.setFigureSettings(sizeSlider.getValue(), rotationSlider.getValue());
            selectedSettings.setFigurePatternMode(corners);
            figureSetSettingsFrame.dispose();
            selectedSettings.setFigureMode();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            figureSetSettingsFrame.dispose();
        });

        settingsPanel.add(labelSize);
        settingsPanel.add(sizeSlider);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Уменьшили отступы
        settingsPanel.add(labelRotation);
        settingsPanel.add(rotationSlider);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Уменьшили отступы
        settingsPanel.add(applyButton);
        settingsPanel.add(okButton);
        settingsPanel.add(cancelButton);

        mainPanel.add(settingsPanel, BorderLayout.EAST);
        figureSetSettingsFrame.add(mainPanel);

        // Обработчик изменений слайдеров
        ChangeListener updateListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int size = sizeSlider.getValue();
                int rotation = rotationSlider.getValue();
                figurePanel.setFigureParameters(size, rotation, corners);
                figurePanel.areSettingForm = true;
                figurePanel.repaint(); // Перерисовываем панель при изменении параметров
            }
        };
        sizeSlider.addChangeListener(updateListener);
        rotationSlider.addChangeListener(updateListener);

        // Отображаем окно
        figureSetSettingsFrame.setVisible(true);
        figureSetSettingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                figureSetSettingsFrame.dispose();
            }
        });

    }

}