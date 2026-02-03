package Homework02_3.converters;

public class CartesianToPolar implements Homework02_3.converters.Converter {

    @Override
    public String getName() {
        return "Cartesian to Polar";
    }

    @Override
    public String[] getParameterNames() {
        return new String[]{"x", "y"};
    }

    @Override
    public String convert(double[] v) {
        double x = v[0];
        double y = v[1];

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        return String.format("r = %.4f , θ = %.4f rad", r, theta);
    }

    @Override
    public String toString() {
        return getName();
    }
}
