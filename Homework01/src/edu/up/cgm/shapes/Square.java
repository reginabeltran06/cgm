package shapes;

public class Square implements Shape {

    @Override
    public String getName() {
        return "Square";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "side" };
    }

    @Override
    public double calculateArea(double[] v) {
        return v[0] * v[0];
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return 4 * v[0];
    }

    @Override
    public String toString() {
        return getName();
    }
}
