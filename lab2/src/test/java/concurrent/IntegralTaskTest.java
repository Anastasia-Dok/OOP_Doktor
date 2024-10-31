package concurrent;

import functions.LinkedListTabulateFunction;
import functions.MathFunction;
import org.junit.jupiter.api.Test;


class IntegralTaskTest {

    @Test
    void TrapezoidMethod() {
        MathFunction f = (x)->x*x;
        LinkedListTabulateFunction function = new LinkedListTabulateFunction(f,1,10,10);

        IntegralTask integralTask = new IntegralTask(function,1,10);



    }
}