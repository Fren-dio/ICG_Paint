import java.awt.Color;

public class SelectedSettings {

    private Boolean STRAIGHT_LINE_MODE;
    private Boolean WAVED_LINE_MODE;
    private int LINE_WEIGHT_RANGE;
    private final int DEFAULT_LINE_WEIGHT = 1;
    private Boolean TRIANGLE_PATTERN_MODE;
    private Boolean SQUARE_PATTERN_MODE;
    private Boolean FUN_MODE;

    public int LIGHT_LINE_WEIGHT = 1;

    private Color currentColor;
    private String currentMode;

    public SelectedSettings(Color initialColor) {
        this.currentColor = initialColor;
        this.STRAIGHT_LINE_MODE = true;
        this.WAVED_LINE_MODE = false;
        this.TRIANGLE_PATTERN_MODE = false;
        this.SQUARE_PATTERN_MODE = false;
        this.FUN_MODE = false;

        this.LINE_WEIGHT_RANGE = 1;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public String getCurrentMode() {
        if (this.WAVED_LINE_MODE)
            return "WAVED_LINE_MODE";
        if (this.TRIANGLE_PATTERN_MODE)
            return "TRIANGLE_PATTERN_MODE";
        if (this.SQUARE_PATTERN_MODE)
            return "SQUARE_PATTERN_MODE";
        if (this.FUN_MODE)
            return "FUN_MODE";
        return "STRAIGHT_LINE_MODE";
    }

    public int getCurrentWeight()
    {
        return this.LINE_WEIGHT_RANGE;
    }

    public void setStraightLineMode() {
        setAllFalse();
        this.STRAIGHT_LINE_MODE = true;
    }

    public void setWavedLineMode() {
        setAllFalse();
        this.WAVED_LINE_MODE = true;
    }

    public void setTrianglePatternMode() {
        setAllFalse();
        this.TRIANGLE_PATTERN_MODE = true;
    }

    public void setSquarePatternMode() {
        setAllFalse();
        this.SQUARE_PATTERN_MODE = true;
    }

    public void setFunPatternMode() {
        setAllFalse();
        this.FUN_MODE = true;
    }

    public void setLightWeight(){
        this.LINE_WEIGHT_RANGE = 1;
    }

    public void setMediumWeight(){
        this.LINE_WEIGHT_RANGE = 3;
    }

    public void setBoldWeight(){
        this.LINE_WEIGHT_RANGE = 6;
    }

    private void setAllFalse(){
        this.STRAIGHT_LINE_MODE = false;
        this.WAVED_LINE_MODE = false;
        this.TRIANGLE_PATTERN_MODE = false;
        this.SQUARE_PATTERN_MODE = false;
        this.FUN_MODE = false;

    }
}