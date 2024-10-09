package functions;

public class ConstantFunction implements MathFunction {
    private final double const;

    public double apply(double x) {
        return const;
    }

    public ConstantFunction(double const) {
        this.const = const;
    }


    public double getConst() {
        return const;
    }


}