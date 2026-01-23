package shapes;

public class Circle implements Shape {

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "radius" };
    }

    @Override
    public double calculateArea(double[] v) {
        return Math.PI * v[0] * v[0];
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return 2* Math.PI * v[0];
    }

    @Override
    public String toString() {
        return getName();
    }
}
