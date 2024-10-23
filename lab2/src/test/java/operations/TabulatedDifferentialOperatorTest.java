package operations;

import functions.ArrayTabulateFunction;
import functions.LinkedListTabulateFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulateFunctionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TabulatedDifferentialOperatorTest {

    @Test
    void deriveLinkedListTest()
    {
        TabulatedFunction list = new LinkedListTabulateFunctionFactory().create(new double[]{1,2,3,4},new double[]{1,4,9,16});
        TabulatedDifferentialOperator difflistop = new TabulatedDifferentialOperator();
        TabulatedFunction listdiff = difflistop.derive(list);
        Assertions.assertEquals(7,listdiff.getY(2));
        Assertions.assertEquals(5,listdiff.getY(1));
    }
    @Test
    void deriveLinkedListFabricTest()
    {
        LinkedListTabulateFunctionFactory listfac = new LinkedListTabulateFunctionFactory();
        TabulatedDifferentialOperator difflistop = new TabulatedDifferentialOperator(listfac);
        TabulatedFunction listdiff = difflistop.derive(difflistop.getFactory().create(new double[]{1,2,3,4},new double[]{1,4,9,16}));
        Assertions.assertEquals(7,listdiff.getY(2));
        Assertions.assertEquals(5,listdiff.getY(1));
    }
    @Test
    void deriveLinkedArrayFabricTest()
    {
        ArrayTabulatedFunctionFactory arrfac = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator diffarrop = new TabulatedDifferentialOperator(arrfac);
        TabulatedFunction arrdiff = diffarrop.derive(diffarrop.getFactory().create(new double[]{1,2,3,4},new double[]{1,4,9,16}));
        Assertions.assertInstanceOf(ArrayTabulateFunction.class, arrdiff);
        Assertions.assertEquals(7,arrdiff.getY(2));
        Assertions.assertEquals(5,arrdiff.getY(1));
    }
    @Test
    void deriveLinkedArrayTest()
    {
        LinkedListTabulateFunctionFactory fac = new LinkedListTabulateFunctionFactory();
        TabulatedFunction arr = fac.create(new double[]{1,2,3,4},new double[]{1,4,9,16});
        TabulatedDifferentialOperator diffarrop = new TabulatedDifferentialOperator(fac);
        TabulatedFunction listdiff = diffarrop.derive(arr);
        Assertions.assertInstanceOf(LinkedListTabulateFunction.class, listdiff);
        Assertions.assertEquals(7,listdiff.getY(2));
        Assertions.assertEquals(5,listdiff.getY(1));
    }
    @Test
    void applyTest()
    {
        TabulatedDifferentialOperator diff = new TabulatedDifferentialOperator();
        Assertions.assertThrows(UnsupportedOperationException.class,()->diff.apply(2));
    }
    @Test
    void setTest()
    {
        TabulatedDifferentialOperator diff = new TabulatedDifferentialOperator();
        LinkedListTabulateFunctionFactory fac = new LinkedListTabulateFunctionFactory();
        diff.setFactory(fac);
        Assertions.assertEquals(fac,diff.getFactory());
    }
}
