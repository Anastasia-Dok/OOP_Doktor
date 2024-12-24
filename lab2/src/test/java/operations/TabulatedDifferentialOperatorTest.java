package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TabulatedDifferentialOperatorTest {

    @Test
    void deriveLinkedListTest()
    {
        TabulatedFunction list = new LinkedListTabulatedFunctionFactory().create(new double[]{1,2,3,4},new double[]{1,4,9,16});
        TabulatedDifferentialOperator difflistop = new TabulatedDifferentialOperator();
        TabulatedFunction listdiff = difflistop.derive(list);
        Assertions.assertEquals(7,listdiff.getY(2));
        Assertions.assertEquals(5,listdiff.getY(1));
    }
    @Test
    void deriveLinkedListFabricTest()
    {
        LinkedListTabulatedFunctionFactory listfac = new LinkedListTabulatedFunctionFactory();
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
        Assertions.assertInstanceOf(ArrayTabulatedFunction.class, arrdiff);
        Assertions.assertEquals(7,arrdiff.getY(2));
        Assertions.assertEquals(5,arrdiff.getY(1));
    }
    @Test
    void deriveLinkedArrayTest()
    {
        LinkedListTabulatedFunctionFactory fac = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction arr = fac.create(new double[]{1,2,3,4},new double[]{1,4,9,16});
        TabulatedDifferentialOperator diffarrop = new TabulatedDifferentialOperator(fac);
        TabulatedFunction listdiff = diffarrop.derive(arr);
        Assertions.assertInstanceOf(LinkedListTabulatedFunction.class, listdiff);
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
        LinkedListTabulatedFunctionFactory fac = new LinkedListTabulatedFunctionFactory();
        diff.setFactory(fac);
        Assertions.assertEquals(fac,diff.getFactory());
    }
}
