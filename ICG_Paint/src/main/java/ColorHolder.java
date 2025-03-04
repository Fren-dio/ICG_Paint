import java.awt.Color;

public class ColorHolder {
    private Color currentColor;

    public ColorHolder(Color initialColor) {
        this.currentColor = initialColor;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
}