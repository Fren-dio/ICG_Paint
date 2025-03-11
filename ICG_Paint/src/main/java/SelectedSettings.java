import java.awt.Color;

public class SelectedSettings {

    private Boolean STRAIGHT_LINE_MODE;
    private Boolean WAVED_LINE_MODE;
    private int LINE_WEIGHT_RANGE;
    private int FIGURE_CORNERS;
    private boolean FIGURE_MODE;
    private Boolean FUN_MODE;

    private Boolean FILL_MODE;

    public int LIGHT_LINE_WEIGHT = 1;

    private Color currentColor;

    private int FIGURE_SIZE;
    private int FIGURE_ROTATE;

    public SelectedSettings(Color initialColor) {
        this.currentColor = initialColor;
        this.STRAIGHT_LINE_MODE = true;
        this.WAVED_LINE_MODE = false;
        this.FIGURE_CORNERS = 3;
        this.FIGURE_MODE = false;
        this.FUN_MODE = false;
        this.FILL_MODE = false;

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
        if (this.FUN_MODE)
            return "FUN_MODE";
        if (this.FIGURE_MODE)
            return "FIGURE_MODE";
        if (this.FILL_MODE)
            return "FILL_MODE";
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

    public void setFillMode() {
        setAllFalse();
        this.FILL_MODE = true;
    }

    public void setFigurePatternMode(int corners) {
        setAllFalse();
        this.FIGURE_CORNERS = corners;
    }

    public int getFigureCorners() {
        return this.FIGURE_CORNERS;
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

    public void setFigureSettings(int size, int rotate){
        this.FIGURE_SIZE = size;
        this.FIGURE_ROTATE = rotate;
    }

    public int getFigureSize() {
        return this.FIGURE_SIZE;
    }

    public void setFigureMode() {
        this.FIGURE_MODE = true;
    }

    public int getFigureRotate() {
        return this.FIGURE_ROTATE;
    }

    private void setAllFalse(){
        this.STRAIGHT_LINE_MODE = false;
        this.WAVED_LINE_MODE = false;
        this.FUN_MODE = false;
        this.FIGURE_MODE = false;
        this.FILL_MODE = false;

    }
}