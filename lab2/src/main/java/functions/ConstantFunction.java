package functions;

public class ConstantFunction implements MathFunction {
    private final double consta;

    public double apply(double x) {
        return consta;
    }

    public ConstantFunction(double consta) {
        this.consta = consta;
    }


    public double getConst() {
        return consta;
    }


}