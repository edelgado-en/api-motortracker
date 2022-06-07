package api.motortracker.motortracker.util;

public class Square extends Rectangle {

    public Square(String color, double width, double height) {
        super(color, width, height);
    }

    public double getArea() {
        return getWidth() * getWidth();
    }

    public double getPerimeter() {
        return 4 * getWidth();
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + getWidth() +
                ", area=" + getArea() +
                ", perimeter=" + getPerimeter() +
                + '}';
    }
}
