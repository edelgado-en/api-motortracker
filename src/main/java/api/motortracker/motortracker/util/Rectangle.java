package api.motortracker.motortracker.util;

public class Rectangle extends TwoDShape {

    private double width;

    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", area=" + getArea() +
                ", perimeter=" + getPerimeter() +
                '}';
    }
}
