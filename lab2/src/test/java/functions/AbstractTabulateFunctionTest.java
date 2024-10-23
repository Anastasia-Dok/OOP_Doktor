package functions;

import org.junit.jupiter.api.Test;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulateFunctionTest {
    @Test
    public void testCheckLengthIsTheSame_SameLength() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        assertDoesNotThrow(() -> AbstractTabulateFunction.checkLengthIsTheSame(xValues, yValues));
    }

    @Test
    public void testCheckLengthIsTheSame_DifferentLength() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0};
        assertThrows(DifferentLengthOfArraysException.class, () ->
                AbstractTabulateFunction.checkLengthIsTheSame(xValues, yValues)
        );
    }

    @Test
    public void testCheckSorted_SortedArray() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0};
        assertDoesNotThrow(() -> AbstractTabulateFunction.checkSorted(xValues));
    }

    @Test
    public void testCheckSorted_UnsortedArray() {
        double[] xValues = {1.0, 3.0, 2.0, 4.0};
        assertThrows(ArrayIsNotSortedException.class, () ->
                AbstractTabulateFunction.checkSorted(xValues)
        );
    }
    @Test
    void testToString1(){
        double [] xValues = {1.0, 2.0, 3.0};
        double [] yValues = {4.0, 5.0, 6.0};
        LinkedListTabulateFunction function1 = new LinkedListTabulateFunction(xValues, yValues);
        ArrayTabulateFunction function2 = new ArrayTabulateFunction(xValues,yValues);

        String stringLinkedListFunction = "LinkedListTabulatedFunction size = 3\n[1.0; 4.0]\n[2.0; 5.0]\n[3.0; 6.0]\n";
        String stringArrayFunction = "ArrayTabulatedFunction size = 4\n[1.0; 6.0]\n[2.0; 4.0]\n[3.0; 0.0]\n[4.25; 5.55]\n";

        assertEquals(stringLinkedListFunction, function1.toString());
        assertEquals(stringArrayFunction, function2.toString());
    }

}