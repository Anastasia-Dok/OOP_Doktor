package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;



class ArrayTabulatedFunctionTest {

    @Test
    void indexOfXTest1() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(4, func.indexOfX(5));
    }

    @Test
    void indexOfXTest2() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(-1, func.indexOfX(6));
    }

    @Test
    void indexOfYTest1() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(-1, func.indexOfY(6));
    }

    @Test
    void indexOfYTest2() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(1, func.indexOfY(2));
    }

    @Test
    void leftBoundTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(1, func.leftBound());
    }

    @Test
    void rightBoundTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(5, func.rightBound());
    }

    @Test
    void floorIndexOfXTest1() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(4, func.floorIndexOfX(5));
    }

    @Test
    void floorIndexOfXTest2() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(3, func.floorIndexOfX(4.5));
    }


    @Test
    void floorIndexOfXTest4() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(func.getCount(), func.floorIndexOfX(6));
    }

    @Test
    void extrapolateLeftTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(arrY[0] + (arrY[1] - arrY[0]) / (arrX[1] - arrX[0]) * (-1 - arrX[0]), func.apply(-1));
    }

    @Test
    void extrapolateRightTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(arrY[func.getCount() - 2] + (arrY[func.getCount() - 1] - arrY[func.getCount() - 2]) / (arrX[func.getCount() - 1] - arrX[func.getCount() - 2]) * (6 - arrX[func.getCount() - 2]), func.apply(6));
    }


    @Test
    void interpolateTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        int floorIndex = func.floorIndexOfX(4.5);
        Assertions.assertEquals(arrY[floorIndex] + (arrY[floorIndex + 1] - arrY[floorIndex]) / (arrX[floorIndex + 1] - arrX[floorIndex]) * (4.5 - arrX[floorIndex]), func.apply(4.5));
    }

    @Test
    void getXTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(4, func.getX(3));
    }

    @Test
    void getYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        Assertions.assertEquals(3, func.getX(2));
    }

    @Test
    void setYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        func.setY(2, 5);
        Assertions.assertEquals(5, func.getY(2));
    }

    @Test
    void constructFunction() {
        ArrayTabulateFunction func = new ArrayTabulateFunction(new SqrFunction(), 0, 5, 6);
        boolean b = false;
        for (int i = 0; i < func.getCount(); i++) {
            if (func.getY(i) != i * i && func.getX(i) != i)
                b = true;
        }
        Assertions.assertFalse(b);
    }

    @Test
    void constructFunction2() {
        ArrayTabulateFunction func = new ArrayTabulateFunction(new SqrFunction(), 5, 0, 6);
        boolean b = false;
        for (int i = 0; i < func.getCount(); i++) {
            if (func.getY(i) != i * i && func.getX(i) != i)
                b = true;
        }
        Assertions.assertFalse(b);
    }

    @Test
    void removeTest1() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        func.remove(2);
        Assertions.assertEquals(4, func.getX(2));
    }

    @Test
    void removeTest2() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        func.remove(0);
        Assertions.assertEquals(2, func.getX(0));
    }

    @Test
    void removeTest3() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulateFunction func = new ArrayTabulateFunction(arrX, arrY);
        func.remove(func.getCount() - 1);
        Assertions.assertEquals(4, func.getX(func.getCount() - 1));
    }

    @Test
    public void testInterpolate_ValidInterval() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        ArrayTabulateFunction function = new ArrayTabulateFunction(xValues, yValues);

        // Проверка корректного результата интерполяции
        assertEquals(4.5, function.interpolate(1.5, 0), 0.0001);
    }

    @Test
    public void testInterpolate_xOutsideInterval() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        ArrayTabulateFunction function = new ArrayTabulateFunction(xValues, yValues);

        // x за пределами интервала [xValues[floorIndex], xValues[floorIndex + 1]]
        assertThrows(InterpolationException.class, () -> function.interpolate(2.5, 0));
    }


    @Test
    public void testCheckLengthIsTheSame_DifferentLength() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0};
        assertThrows(DifferentLengthOfArraysException.class, () ->
                new ArrayTabulateFunction(xValues, yValues)
        );
    }

    @Test
    void testInterpolationException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        LinkedListTabulateFunction linkedListFunction = new LinkedListTabulateFunction(xValues, yValues);
        assertThrows(InterpolationException.class,
                () -> linkedListFunction.interpolate(3.5, linkedListFunction.floorNodeOfX(1)));
    }    @Test
    public void testCheckSorted_UnsortedArray() {
        double[] xValues = {1.0, 3.0, 2.0};
        double[] yValues = {4.0, 5.0, 6.0};
        assertThrows(ArrayIsNotSortedException.class, () ->
                new ArrayTabulateFunction(xValues, yValues)
        );

    }
    @Test
    public void testArrayIteratorWithWhile() {
        ArrayTabulateFunction function = new ArrayTabulateFunction(new double[]{1.0, 2.0, 3.0}, new double[]{10.0, 20.0, 30.0});
        Iterator<Point> iterator = function.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(function.getX(index), point.x, 1e-9);
            assertEquals(function.getY(index), point.y, 1e-9);
            index++;
        }

        assertEquals(function.getCount(), index);
    }
}