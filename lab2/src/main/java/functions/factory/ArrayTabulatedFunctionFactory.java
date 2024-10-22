package functions.factory;

import functions.ArrayTabulateFunction;
import functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulateFunction(xValues, yValues);
    }
}
