package functions.factory;

import functions.LinkedListTabulateFunction;
import functions.TabulatedFunction;

public class LinkedListTabulateFunctionFactory implements TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulateFunction(xValues, yValues);
    }
}
