package operations;

import functions.TabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.factory.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPointsForArrayFunction() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertEquals(3, points.length);
        assertEquals(new Point(1.0, 4.0), points[0]);
        assertEquals(new Point(2.0, 5.0), points[1]);
        assertEquals(new Point(3.0, 6.0), points[2]);
    }

    @Test
    public void testAsPointsForLinkedListFunction() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertEquals(3, points.length);
        assertEquals(new Point(1.0, 4.0), points[0]);
        assertEquals(new Point(2.0, 5.0), points[1]);
        assertEquals(new Point(3.0, 6.0), points[2]);
    }

    @Test
    public void testAddWithSameFunctionTypes() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValuesA = {4.0, 5.0, 6.0};
        double[] yValuesB = {7.0, 8.0, 9.0};
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);
        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValues, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValues, yValuesB);
        TabulatedFunction result = service.add(functionA, functionB);
        assertEquals(11.0, result.apply(1.0), 0.0001);
        assertEquals(13.0, result.apply(2.0), 0.0001);
        assertEquals(15.0, result.apply(3.0), 0.0001);
    }
    @Test
    public void testSubstractWithSameFunctionTypes() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValuesA = {8.0, 15.0, 60.0};
        double[] yValuesB = {2.0, 3.0, 10.0};
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);
        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValues, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValues, yValuesB);
        TabulatedFunction result = service.subtract(functionA, functionB);
        assertEquals(6.0, result.apply(1.0), 0.0001);
        assertEquals(12.0, result.apply(2.0), 0.0001);
        assertEquals(50.0, result.apply(3.0), 0.0001);
    }

    @Test
    public void testMultiplyWithSameFunctionTypes() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValuesA = {4.0, 5.0, 6.0};
        double[] yValuesB = {7.0, 8.0, 9.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValues, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValues, yValuesB);

        TabulatedFunction result = service.multiply(functionA, functionB);

        assertEquals(28.0, result.apply(1.0), 0.0001);
        assertEquals(40.0, result.apply(2.0), 0.0001);
        assertEquals(54.0, result.apply(3.0), 0.0001);
    }

    @Test
    public void testDevisionWithSameFunctionTypes() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValuesA = {8.0, 15.0, 60.0};
        double[] yValuesB = {2.0, 3.0, 10.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValues, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValues, yValuesB);

        TabulatedFunction result = service.devide(functionA, functionB);

        assertEquals(4.0, result.apply(1.0), 0.0001);
        assertEquals(5.0, result.apply(2.0), 0.0001);
        assertEquals(6.0, result.apply(3.0), 0.0001);
    }

}
