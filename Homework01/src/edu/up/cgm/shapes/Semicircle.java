package shapes;

public class Semicircle implements Shape {

    @Override
    public String getName() {
        return "Semicircle";
    }

    @Override
    public String[] getParameterNames() {
        return new String[] { "diameter" };
    }

    @Override
    public double calculateArea(double[] v) {
        double radius= v[0]/2;
        return Math.PI * radius * radius * .5;
    }

    @Override
    public double calculatePerimeter(double[] v) {
        return Math.PI * (v[0]/2) + v[0];
    }

    @Override
    public String toString() {
        return getName();
    }
}
