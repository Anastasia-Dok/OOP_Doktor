package operations;

import concurrent.SynchronizedTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction func) {
        double[] xValues = new double[func.getCount()];
        double[] yValues = new double[func.getCount()];
        Point[] ps = TabulatedFunctionOperationService.asPoints(func);
        for (int i = 0; i < ps.length - 1; ++i) {
            xValues[i] = ps[i].x;
            yValues[i] = (ps[i + 1].y - ps[i].y) / (ps[i + 1].x - ps[i].x);
        }
        xValues[xValues.length - 1] = ps[ps.length - 1].x;
        yValues[yValues.length - 1] = yValues[yValues.length - 2];
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if(!(function instanceof SynchronizedTabulatedFunction)) {
            function = new SynchronizedTabulatedFunction(function);
        }

        SynchronizedTabulatedFunction syncFunction = (SynchronizedTabulatedFunction) function;
        return syncFunction.doSynchronously(this::derive);
    }
    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException("for using derive");
    }

}

