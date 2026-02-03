package Homework02_3.converters;

public class PolarToCartesian implements Homework02_3.converters.Converter {

    @Override
    public String getName() {
        return "Polar to Cartesian";
    }

    @Override
    public String[] getParameterNames() {
        return new String[]{"r", "θ (radians)"};
    }

    @Override
    public String convert(double[] v) {
        double r = v[0];
        double theta = v[1];

        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);

        return String.format("x = %.4f , y = %.4f", x, y);
    }

    @Override
    public String toString() {
        return getName();
    }
}
