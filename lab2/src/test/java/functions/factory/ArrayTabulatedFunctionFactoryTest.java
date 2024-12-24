package functions.factory;

import functions.ArrayTabulatedFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayTabulatedFunctionFactoryTest
{
    @Test
    void createArrayTest()
    {
        ArrayTabulatedFunctionFactory arrf = new ArrayTabulatedFunctionFactory();
        Assertions.assertInstanceOf(ArrayTabulatedFunction.class, arrf.create(new double[]{1,2,3}, new double[]{1,4,9}));
    }
}