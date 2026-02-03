package Homework02_3.converters;

public interface Converter {

    String getName();
    String[] getParameterNames(); // input labels
    String convert(double[] values);
}
