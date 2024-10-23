package operations;

import functions.ArrayTabulateFunction;
import functions.LinkedListTabulateFunction;
import functions.Point;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPointsForArrayFunction() {
        ArrayTabulateFunction function = new ArrayTabulateFunction(
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
        LinkedListTabulateFunction function = new LinkedListTabulateFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertEquals(3, points.length);
        assertEquals(new Point(1.0, 4.0), points[0]);
        assertEquals(new Point(2.0, 5.0), points[1]);
        assertEquals(new Point(3.0, 6.0), points[2]);
    }


}
