package Homework01.ui.shapes;

public interface Shape {
    String getName();

    String[] getParameterNames();

    double calculateArea(double[] values);

    double calculatePerimeter(double[] values);

}
