import javax.swing.*;
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
        clearBtn.addActionListener(ev -> this.imagePanel.clear());
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
        redBtn.setToolTipText("Red color. RGB=(255,0,0)");
        row1.add(redBtn);

        // Желтый цвет
        JButton redGreenBtn = createColorButton(new Color(255, 255, 0));
        redGreenBtn.setToolTipText("Yellow color. RGB=(255,255,0)");
        row1.add(redGreenBtn);

        // Зеленый цвет
        JButton greenBtn = createColorButton(new Color(0, 255, 0));
        greenBtn.setToolTipText("Green color. RGB=(0,255,0)");
        row1.add(greenBtn);

        // Второй ряд цветов
        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));

        // Голубой цвет
        JButton greenBlueBtn = createColorButton(new Color(0, 255, 255));
        greenBlueBtn.setToolTipText("Azure color. RGB=(0,255,255)");
        row2.add(greenBlueBtn);

        // Синий цвет
        JButton blueBtn = createColorButton(new Color(0, 0, 255));
        blueBtn.setToolTipText("Blue color. RGB=(0,0,255)");
        row2.add(blueBtn);

        // Пурпурный цвет
        JButton redBlueBtn = createColorButton(new Color(255, 0, 255));
        redBlueBtn.setToolTipText("Purple color. RGB=(255,0,255)");
        row2.add(redBlueBtn);

        // Белый цвет
        JButton whiteBtn = createColorButton(new Color(255, 255, 255));
        whiteBtn.setToolTipText("White color. RGB=(255,255,255)");
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
                int corners = 4;
                createChosenWindow(corners, "Four corners Settings");
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
                createChosenWindow(corners, "Five corners Settings");
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
                createChosenWindow(corners, "Six corners Settings");
                selectedSettings.setFigurePatternMode(corners);

            }
        });
        patternsGrid.add(sixBtn);

        JButton starBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("star_icon.jpg"))));
        starBtn.setToolTipText("Draw square by chosen color and weight.");
        setSizeSquareBtn(starBtn, (int)(btnSize*0.85));
        starBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем диалог выбора
                int corners = 6;
                createChosenWindowForStar(corners, "Star corners Settings");
                selectedSettings.setFigurePatternMode(corners);

            }
        });
        patternsGrid.add(starBtn);

        JButton polygonBtn = new JButton("Polygon");
        polygonBtn.setToolTipText("Draw a polygon with chosen number of corners.");
        setSizeSquareBtn(polygonBtn, (int)(btnSize * 0.85));
        polygonBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.starExampleMode = true;
                int corners = showPolygonCornersDialog();
                if (corners >= 3) {
                    createChosenWindow(corners, "Polygon Settings");
                    selectedSettings.setFigurePatternMode(corners);
                }
                imagePanel.starExampleMode = false;
            }
        });
        patternsGrid.add(polygonBtn);
    }

    private void createChosenWindowForStar(int corners, String formName) {
        int formSizeH = 380;
        int formSizeW = 500;
        JFrame figureSetSettingsFrame = new JFrame(formName);

        figureSetSettingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        figureSetSettingsFrame.setSize(formSizeW, formSizeH);

        // Установим позицию окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        figureSetSettingsFrame.setLocation(
                (int) ((screenSize.getWidth() - formSizeW) / 2),
                (int) ((screenSize.getHeight() - formSizeH) / 2)
        );

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Панель для отображения фигуры
        ImagePanel figurePanel = new ImagePanel(selectedSettings, 100, 0, corners);
        figurePanel.setPreferredSize(new Dimension(formSizeW - 200, formSizeH));
        mainPanel.add(figurePanel, BorderLayout.CENTER);
        figurePanel.setStarExampleValue(true);

        // Панель настроек
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 5, 200, 100);
        JLabel labelSize = new JLabel("Choose size: " + sizeSlider.getValue());
        sizeSlider.setMajorTickSpacing(50);
        sizeSlider.setMinorTickSpacing(25);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 5, 250, 125);
        JLabel labelRotation = new JLabel("Choose rotation:" + rotationSlider.getValue());
        rotationSlider.setMajorTickSpacing(50);
        rotationSlider.setMinorTickSpacing(25);
        rotationSlider.setPaintTicks(true);
        rotationSlider.setPaintLabels(true);

        JSlider cornersSlider = new JSlider(JSlider.HORIZONTAL, 3, 50, 5);
        JLabel cornersSize = new JLabel("Choose amount of corners:" + cornersSlider.getValue());
        cornersSlider.setMajorTickSpacing(50);
        cornersSlider.setMinorTickSpacing(25);
        cornersSlider.setPaintTicks(true);
        cornersSlider.setPaintLabels(true);

        JSlider radiusSlider = new JSlider(JSlider.HORIZONTAL, 0, 220, 0);
        JLabel radiusRotation = new JLabel("Choose radius:" + radiusSlider.getValue());
        radiusSlider.setMajorTickSpacing(90);
        radiusSlider.setMinorTickSpacing(30);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> {
            figurePanel.repaint();
        });

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            selectedSettings.setStarMode();
            figureSetSettingsFrame.dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            figureSetSettingsFrame.dispose();
        });

        settingsPanel.add(labelSize);
        settingsPanel.add(sizeSlider);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingsPanel.add(labelRotation);
        settingsPanel.add(rotationSlider);
        settingsPanel.add(cornersSize);
        settingsPanel.add(cornersSlider);
        settingsPanel.add(radiusRotation);
        settingsPanel.add(radiusSlider);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPanel.add(applyButton);
        settingsPanel.add(okButton);
        settingsPanel.add(cancelButton);

        sizeSlider.addChangeListener(e -> {
            labelSize.setText("Choose size: " + sizeSlider.getValue());
            figurePanel.setStarParamsSize(sizeSlider.getValue());
            figurePanel.repaint();
        });

        rotationSlider.addChangeListener(e -> {
            labelRotation.setText("Choose rotation:" + rotationSlider.getValue());
            figurePanel.setStarParamsRotate(rotationSlider.getValue());
            figurePanel.repaint();
        });

        cornersSlider.addChangeListener(e -> {
            cornersSize.setText("Choose amount of corners:" + cornersSlider.getValue());
            figurePanel.setStarParamsCorners(cornersSlider.getValue());
            figurePanel.repaint();
        });

        radiusSlider.addChangeListener(e -> {
            radiusRotation.setText("Choose radius:" + radiusSlider.getValue());
            figurePanel.setStarParamsRadius(radiusSlider.getValue());
            figurePanel.repaint();
        });


        mainPanel.add(settingsPanel, BorderLayout.EAST);
        figureSetSettingsFrame.add(mainPanel);
        figureSetSettingsFrame.setVisible(true);


    }

    int showPolygonCornersDialog() {
        // Создаем массив возможных значений углов
        Integer[] cornersOptions = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        // Создаем диалог выбора
        Integer selectedCorners = (Integer) JOptionPane.showInputDialog(
                this,
                "Choose the number of corners:",
                "Polygon Corners",
                JOptionPane.QUESTION_MESSAGE,
                null,
                cornersOptions,
                cornersOptions[0]
        );

        return (selectedCorners != null) ? selectedCorners : -1;
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

    void createChosenWindow(int corners, String formName) {
        int formSizeH = 300;
        int formSizeW = 500;
        JFrame figureSetSettingsFrame = new JFrame(formName);

        figureSetSettingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        figureSetSettingsFrame.setSize(formSizeW, formSizeH);

        // Установим позицию окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        figureSetSettingsFrame.setLocation(
                (int) ((screenSize.getWidth() - formSizeW) / 2),
                (int) ((screenSize.getHeight() - formSizeH) / 2)
        );

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Панель для отображения фигуры
        ImagePanel figurePanel = new ImagePanel(selectedSettings, 100, 0, corners);
        figurePanel.setPreferredSize(new Dimension(formSizeW - 200, formSizeH));
        mainPanel.add(figurePanel, BorderLayout.CENTER);
        figurePanel.setPolygonExampleValue(true);

        // Панель настроек
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JLabel labelSize = new JLabel("Choose size:");
        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 5, 250, 125);
        sizeSlider.setMajorTickSpacing(50);
        sizeSlider.setMinorTickSpacing(25);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        JLabel labelRotation = new JLabel("Choose rotation angle:");
        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 720, 0);
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
            JOptionPane.showMessageDialog(figureSetSettingsFrame, "Settings applied!");
        });

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            figurePanel.setFigureParameters(sizeSlider.getValue(), rotationSlider.getValue(), corners);
            selectedSettings.setFigureSettings(sizeSlider.getValue(), rotationSlider.getValue());
            selectedSettings.setFigurePatternMode(corners);
            selectedSettings.setFigureMode();
            figureSetSettingsFrame.dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            figureSetSettingsFrame.dispose();
        });

        settingsPanel.add(labelSize);
        settingsPanel.add(sizeSlider);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingsPanel.add(labelRotation);
        settingsPanel.add(rotationSlider);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPanel.add(applyButton);
        settingsPanel.add(okButton);
        settingsPanel.add(cancelButton);

        mainPanel.add(settingsPanel, BorderLayout.EAST);
        figureSetSettingsFrame.add(mainPanel);


        sizeSlider.addChangeListener(e -> {
            labelSize.setText("Choose size: " + sizeSlider.getValue());
            figurePanel.setPolygonSize(sizeSlider.getValue());
            selectedSettings.setFigureSize(sizeSlider.getValue());
            selectedSettings.setFigureMode();
            figurePanel.repaint();
        });


        rotationSlider.addChangeListener(e -> {
            labelRotation.setText("Choose rotation: " + rotationSlider.getValue());
            figurePanel.setPolygonRotate(rotationSlider.getValue());
            selectedSettings.setFigureRotate(rotationSlider.getValue());
            selectedSettings.setFigurePatternMode(corners);
            figurePanel.repaint();
        });


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