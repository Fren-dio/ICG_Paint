import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class ToolBarMenu extends JToolBar {

    private final int btnSize = 40;

    private final ImagePanel imagePanel;
    private final SelectedSettings selectedSettings;

    public ToolBarMenu(ImagePanel imagePanel, SelectedSettings selectedSettings) {
        this.imagePanel = imagePanel;
        this.selectedSettings = selectedSettings;

        // Кнопка для прямой линии
        JButton lineBtn = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("line_icon.jpg"))));
        if (lineBtn.isSelected())
            lineBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("waved_line_icon.jpg"))));
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
        clearBtn.addActionListener(ev -> imagePanel.clear());
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

        JButton otherWeightLineBtn = new JButton("?");
        otherWeightLineBtn.setBackground(new Color(255, 255, 255));
        otherWeightLineBtn.setToolTipText("Set your own line weight.");
        setDimensionBtn(otherWeightLineBtn, weightBtnSize);
        otherWeightLineBtn.addActionListener(e -> {
            int weight = showBoldsDialog();
            if (weight >= 1) {
                selectedSettings.setWeight(weight);
            }
        });
        weightsGrid.add(otherWeightLineBtn);

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

    int showParametrDialog(int lowBarrier, int topBarrier) {
        Integer selected = null;

        while (selected == null) {
            String input = JOptionPane.showInputDialog(
                    this,
                    "Введите толщину линии (от 1 до 100):",
                    "Bold of line",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Если пользователь нажал "Отмена" или закрыл окно
            if (input == null) {
                break;
            }
            try {
                selected = Integer.parseInt(input);
                if (selected < lowBarrier || selected > topBarrier) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a number between 1 and 20.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    selected = null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid input. Please enter a valid integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        return (selected != null) ? selected : -1;
    }

    int showBoldsDialog() {
        Integer selected = null;

        while (selected == null) {
            String input = JOptionPane.showInputDialog(
                    this,
                    "Введите толщину линии (от 1 до 100):",
                    "Bold of line",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Если пользователь нажал "Отмена" или закрыл окно
            if (input == null) {
                break;
            }
            try {
                selected = Integer.parseInt(input);
                if (selected < 1 || selected > 100) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a number between 1 and 20.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    selected = null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid input. Please enter a valid integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        return (selected != null) ? selected : -1;
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

    public void createChosenWindowForStar(int corners, String formName) {
        int formSizeH = 380;
        int formSizeW = 500;
        JFrame figureSetSettingsFrame = createFrame(formName, formSizeW, formSizeH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        ImagePanel figurePanel = createFigurePanel(formSizeW, formSizeH, corners);
        mainPanel.add(figurePanel, BorderLayout.CENTER);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JSlider sizeSlider = createSlider(0, 200, 100, 50, 25);
        JTextField sizeTextField = createTextField(sizeSlider);
        JPanel sizePanel = createSliderPanel("Choose size: ", sizeTextField, sizeSlider);
        configureSliderListener(sizeSlider, sizeTextField, figurePanel, "size");

        JSlider cornersSlider = createSlider(0, 100, 5, 20, 10);
        JTextField cornersTextField = createTextField(cornersSlider);
        JPanel cornersPanel = createSliderPanel("Choose amount of corners:", cornersTextField, cornersSlider);
        configureSliderListener(cornersSlider, cornersTextField, figurePanel, "corners");

        JSlider rotationSlider = createSlider(0, 1440, 0, 360, 180);
        JTextField rotationTextField = createTextField(rotationSlider);
        JPanel rotationPanel = createSliderPanel("Choose rotation:", rotationTextField, rotationSlider);
        configureSliderListener(rotationSlider, rotationTextField, figurePanel, "rotate");

        JSlider radiusSlider = createSlider(0, 200, 0, 50, 25);
        JTextField radiusTextField = createTextField(radiusSlider);
        JPanel radiusPanel = createSliderPanel("Choose radius:", radiusTextField, radiusSlider);
        configureSliderListener(radiusSlider, radiusTextField, figurePanel, "radius");

        JButton applyButton = createButton("Apply", e -> figurePanel.repaint());
        JButton okButton = createButton("Ok", e -> {
            selectedSettings.setStarMode();
            figureSetSettingsFrame.dispose();
        });
        JButton cancelButton = createButton("Cancel", e -> figureSetSettingsFrame.dispose());

        addComponentsToSettingsPanel(settingsPanel, sizePanel, cornersPanel, rotationPanel, radiusPanel, applyButton, okButton, cancelButton);

        mainPanel.add(settingsPanel, BorderLayout.EAST);
        figureSetSettingsFrame.add(mainPanel);
        figureSetSettingsFrame.setVisible(true);
    }

    private JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(
                (int) ((screenSize.getWidth() - width) / 2),
                (int) ((screenSize.getHeight() - height) / 2)
        );
        return frame;
    }

    private ImagePanel createFigurePanel(int width, int height, int corners) {
        ImagePanel panel = new ImagePanel(selectedSettings, 100, 0, corners);
        panel.setPreferredSize(new Dimension(width - 200, height));
        panel.setStarExampleValue(true);
        return panel;
    }

    private ImagePanel createFigurePanelForPolygon(int width, int height, int corners) {
        ImagePanel panel = new ImagePanel(selectedSettings, 100, 0, corners);
        panel.setPreferredSize(new Dimension(width - 200, height));
        panel.setPolygonExampleValue(true);
        return panel;
    }

    private JSlider createSlider(int min, int max, int value, int majorTick, int minorTick) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(minorTick);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private JTextField createTextField(JSlider slider) {
        JTextField textField = new JTextField(3);
        textField.setMaximumSize(new Dimension(50, 20));
        textField.setText(String.valueOf(slider.getValue()));
        return textField;
    }

    private JPanel createSliderPanel(String labelText, JTextField textField, JSlider slider) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(textField);
        panel.add(slider);
        return panel;
    }

    private void configureSliderListener(JSlider slider, JTextField textField, ImagePanel panel, String paramType) {
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            textField.setText(String.valueOf(value));
            switch (paramType) {
                case "size" -> panel.setStarParamsSize(value);
                case "corners" -> panel.setStarParamsCorners(value);
                case "rotate" -> panel.setStarParamsRotate(value);
                case "radius" -> panel.setStarParamsRadius(value);
            }
            panel.repaint();
        });

        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= slider.getMinimum() && value <= slider.getMaximum()) {
                    slider.setValue(value);
                } else {
                    showErrorDialog("Value must be between " + slider.getMinimum() + " and " + slider.getMaximum());
                }
            } catch (NumberFormatException ex) {
                showErrorDialog("Please enter a valid integer.");
            }
        });
    }

    private void configureSliderListenerForPolygon(JSlider slider, JTextField textField, ImagePanel panel, String paramType) {
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            textField.setText(String.valueOf(value));
            switch (paramType) {
                case "size" -> panel.setPolygonSize(value);
                case "rotate" -> panel.setPolygonRotate(value);
            }
            panel.repaint();
        });

        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= slider.getMinimum() && value <= slider.getMaximum()) {
                    slider.setValue(value);
                } else {
                    showErrorDialog("Value must be between " + slider.getMinimum() + " and " + slider.getMaximum());
                }
            } catch (NumberFormatException ex) {
                showErrorDialog("Please enter a valid integer.");
            }
        });
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void addComponentsToSettingsPanel(JPanel settingsPanel, JPanel sizePanel, JPanel cornersPanel, JPanel rotationPanel, JPanel radiusPanel, JButton applyButton, JButton okButton, JButton cancelButton) {
        settingsPanel.add(new JLabel("Choose size: "));
        settingsPanel.add(sizePanel);
        settingsPanel.add(new JLabel("Choose amount of corners:"));
        settingsPanel.add(cornersPanel);
        settingsPanel.add(new JLabel("Choose rotation:"));
        settingsPanel.add(rotationPanel);
        settingsPanel.add(new JLabel("Choose radius:"));
        settingsPanel.add(radiusPanel);

        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(applyButton);
        btnPanel.add(okButton);
        btnPanel.add(cancelButton);
        settingsPanel.add(btnPanel);
    }

    private void addComponentsToSettingsPanelForPolygon(JPanel settingsPanel, JPanel sizePanel, JPanel rotationPanel, JButton applyButton, JButton okButton, JButton cancelButton) {
        settingsPanel.add(new JLabel("Choose size: "));
        settingsPanel.add(sizePanel);
        settingsPanel.add(new JLabel("Choose rotation:"));
        settingsPanel.add(rotationPanel);

        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(applyButton);
        btnPanel.add(okButton);
        btnPanel.add(cancelButton);
        settingsPanel.add(btnPanel);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    int showPolygonCornersDialog() {
        Integer[] cornersOptions = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

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

    private JButton createColorButton(Color color) {
        JButton button = new JButton("");
        setDimensionBtn(button, new Dimension(btnSize / 2, btnSize / 2));
        button.setBackground(color);

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

    public void createChosenWindow(int corners, String formName) {
        int formSizeH = 380;
        int formSizeW = 500;
        JFrame figureSetSettingsFrame = createFrame(formName, formSizeW, formSizeH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        ImagePanel figurePanel = createFigurePanelForPolygon(formSizeW, formSizeH, corners);
        figurePanel.setPolygonExampleValue(true);
        figurePanel.setFigureParameters(100, 0, corners);
        figurePanel.drawExample();
        figurePanel.repaint();
        mainPanel.add(figurePanel, BorderLayout.CENTER);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JSlider sizeSlider = createSlider(0, 200, 100, 50, 25);
        JTextField sizeTextField = createTextField(sizeSlider);
        JPanel sizePanel = createSliderPanel("Choose size: ", sizeTextField, sizeSlider);
        configureSliderListenerForPolygon(sizeSlider, sizeTextField, figurePanel, "size");

        JSlider rotationSlider = createSlider(0, 1440, 0, 360, 180);
        JTextField rotationTextField = createTextField(rotationSlider);
        JPanel rotationPanel = createSliderPanel("Choose rotation:", rotationTextField, rotationSlider);
        configureSliderListenerForPolygon(rotationSlider, rotationTextField, figurePanel, "rotate");

        JButton applyButton = createButton("Apply", e -> figurePanel.repaint());
        JButton okButton = createButton("Ok", e -> {
            selectedSettings.setFigurePatternMode(corners);
            figureSetSettingsFrame.dispose();
        });
        JButton cancelButton = createButton("Cancel", e -> figureSetSettingsFrame.dispose());

        addComponentsToSettingsPanelForPolygon(settingsPanel, sizePanel, rotationPanel, applyButton, okButton, cancelButton);

        mainPanel.add(settingsPanel, BorderLayout.EAST);
        figureSetSettingsFrame.add(mainPanel);
        figureSetSettingsFrame.setVisible(true);
    }

}