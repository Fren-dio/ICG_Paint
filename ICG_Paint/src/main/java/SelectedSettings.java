import java.awt.Color;

public class SelectedSettings {

    private Boolean STRAIGHT_LINE_MODE;
    private Boolean WAVED_LINE_MODE;
    private Boolean TRIANGLE_PATTERN_MODE;
    private Boolean FUN_MODE;

    private Color currentColor;
    private String currentMode;

    public SelectedSettings(Color initialColor) {
        this.currentColor = initialColor;
        this.STRAIGHT_LINE_MODE = true;
        this.WAVED_LINE_MODE = false;
        this.TRIANGLE_PATTERN_MODE = false;
        this.FUN_MODE = false;
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
        if (this.FUN_MODE)
            return "FUN_MODE";
        return "STRAIGHT_LINE_MODE";
    }

    public void setStraightLineMode() {
        this.STRAIGHT_LINE_MODE = true;
        this.WAVED_LINE_MODE = false;
        this.TRIANGLE_PATTERN_MODE = false;
        this.FUN_MODE = false;
    }

    public void setWavedLineMode() {
        this.STRAIGHT_LINE_MODE = false;
        this.WAVED_LINE_MODE = true;
        this.TRIANGLE_PATTERN_MODE = false;
        this.FUN_MODE = false;
    }

    public void setTrianglePatternMode() {
        this.STRAIGHT_LINE_MODE = false;
        this.WAVED_LINE_MODE = false;
        this.TRIANGLE_PATTERN_MODE = true;
        this.FUN_MODE = false;
    }

    public void setFunPatternMode() {
        this.STRAIGHT_LINE_MODE = false;
        this.WAVED_LINE_MODE = false;
        this.TRIANGLE_PATTERN_MODE = false;
        this.FUN_MODE = true;
    }
}