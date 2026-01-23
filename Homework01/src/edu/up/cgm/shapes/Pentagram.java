package shapes;

public class Pentagram implements Shape {

    @Override
    public String getName() {
        return "Pentagram";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "side" };
    }

    @Override
    public double calculateArea(double[] v) {
        double a = v[0];
        double pentagonArea = (5 * a * a) / (4 * Math.tan(Math.PI / 5));
        return pentagonArea * ((Math.sqrt(5) - 1) / 2);
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return 10 * v[0];
    }

    @Override
    public String toString() {
        return getName();
    }
}
