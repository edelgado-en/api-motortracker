package api.motortracker.motortracker.util;

public class TwoDShape {

    private String color;

    public TwoDShape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "TwoDShape{" +
                "color='" + getColor() + '\'' +
                '}';
    }
}
