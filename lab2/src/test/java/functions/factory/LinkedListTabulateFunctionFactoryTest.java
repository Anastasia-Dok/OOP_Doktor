package functions.factory;

import functions.LinkedListTabulateFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTabulateFunctionFactoryTest
{
    @Test
    void createListTest()
    {
        LinkedListTabulateFunctionFactory listf = new LinkedListTabulateFunctionFactory();
        Assertions.assertInstanceOf(LinkedListTabulateFunction.class, listf.create(new double[]{1,4,9}, new double[]{1,2,3}));
    }
}
