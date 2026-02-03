package Homework01.ui.shapes;

public class Rectangle implements Shape {

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "width", "height" };
    }

    @Override
    public double calculateArea(double[] v) {
        return v[0] * v[1];
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return 2 * (v[0]+v[1]);
    }

    @Override
    public String toString() {
        return getName();
    }
}
