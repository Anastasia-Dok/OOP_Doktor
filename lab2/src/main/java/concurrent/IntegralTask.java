package concurrent;

import functions.TabulatedFunction;

import java.util.concurrent.Callable;
public class IntegralTask implements Callable<Double> {
    private final TabulatedFunction function;
    private final double a;
    private final double b;
    public IntegralTask(TabulatedFunction function,double a, double b){
        if(a > b){
            throw new IllegalArgumentException("a can't be > b");
        }

        if (function == null){
            throw  new IllegalArgumentException("function is null");
        }

        this.a = a;
        this.b = b;
        this.function = function;
    }

    public double TrapezoidMethod(int n){

        if(n <= 0){
            throw new IllegalArgumentException("n can't be <= 0");
        }

        double interval = (b - a) / n;
        double result = 0.0;

        for (int i = 0; i <n; i++){
            double x0 = a + (i * interval);
            double x1 = x0 + interval;
            result += (function.apply(x0) + function.apply(x1)) * interval / 2;
        }

        return result;


    }

    public Double call(){
        return  TrapezoidMethod(1000);
    }

}
