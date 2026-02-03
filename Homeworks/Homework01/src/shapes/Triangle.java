package Homework01.ui.shapes;

public class Triangle implements Shape {

    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "base", "height" };
    }

    @Override
    public double calculateArea(double[] v) {
        return (v[0] * v[1]) / 2;
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return 3 * v[0];
    }

    @Override
    public String toString() {
        return getName();
    }
}
