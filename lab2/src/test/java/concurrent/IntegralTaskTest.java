package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import org.junit.jupiter.api.Test;


class IntegralTaskTest {

    @Test
    void TrapezoidMethod() {
        MathFunction f = (x)->x*x;
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(f,1,10,10);

        IntegralTask integralTask = new IntegralTask(function,1,10);



    }
}