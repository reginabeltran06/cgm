package shapes;

public class Pentagon implements Shape {

    @Override
    public String getName() {
        return "Pentagon";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "side" };
    }

    @Override
    public double calculateArea(double[] v) {
        double a = v[0];
        return (5 * a * a) / (4 * Math.tan(Math.PI / 5));
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return 5 * v[0];
    }

    @Override
    public String toString() {
        return getName();
    }
}
