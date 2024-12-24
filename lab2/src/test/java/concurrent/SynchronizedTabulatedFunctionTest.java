package concurrent;

import functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedTabulatedFunctionTest {
    @Test
    void indexOfXTest1() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(4, func.indexOfX(5));
    }

    @Test
    void indexOfXTest2() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(-1, wrapper.indexOfX(6));
    }

    @Test
    void indexOfYTest1() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(-1, wrapper.indexOfY(6));
    }

    @Test
    void indexOfYTest2() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(1, wrapper.indexOfY(2));
    }

    @Test
    void leftBoundTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(1, wrapper.leftBound());
    }

    @Test
    void rightBoundTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(5, wrapper.rightBound());
    }


    @Test
    void extrapolateLeftTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(arrY[0] + (arrY[1] - arrY[0]) / (arrX[1] - arrX[0]) * (-1 - arrX[0]),wrapper.apply(-1) );
    }

    @Test
    void extrapolateRightTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(arrY[func.getCount() - 2] + (arrY[func.getCount() - 1] - arrY[func.getCount() - 2]) / (arrX[func.getCount() - 1] - arrX[func.getCount() - 2]) * (6 - arrX[func.getCount() - 2]), wrapper.apply(6));
    }


    @Test
    void getXTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(4, wrapper.getX(3));
    }

    @Test
    void getYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        assertEquals(3, wrapper.getX(2));
    }

    @Test
    void setYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        wrapper.setY(2, 5);
        assertEquals(5, wrapper.getY(2));
    }

    @Test
    void constructFunction() {
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(new SqrFunction(), 0, 5, 6);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        boolean b = false;
        for (int i = 0; i < wrapper.getCount(); i++) {
            if (wrapper.getY(i) != i * i && wrapper.getX(i) != i)
                b = true;
        }
        Assertions.assertFalse(b);
    }

    @Test
    void constructFunction2() {
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(new SqrFunction(), 5, 0, 6);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(func);
        boolean b = false;
        for (int i = 0; i < wrapper.getCount(); i++) {
            if (wrapper.getY(i) != i * i && wrapper.getX(i) != i)
                b = true;
        }
        Assertions.assertFalse(b);
    }

    @Test
    void iteratorTest1(){
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(arr);
        Iterator<Point> iterator = wrapper.iterator();
        int j = 0;
        while(iterator.hasNext()){
            Point point = iterator.next();
            assertEquals(point.x, wrapper.getX(j));
            assertEquals(point.y, wrapper.getY(j++));
        }
    }

    @Test
    void iteratorTest2(){
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(arr);
        int j = 0;
        for (Point point : wrapper) {
            assertEquals(point.x, wrapper.getX(j));
            assertEquals(point.y, wrapper.getY(j++));
        }
    }


    @Test
    void iteratorSyncArr()
    {
        SynchronizedTabulatedFunction arr = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(new double[]{1,2,3},new double[]{4,5,6}));
        Point[] points = new Point[3];
        int i =0;
        for(Point p: arr)
        {
            points[i] = p;
            ++i;
        }
        Assertions.assertArrayEquals(points,new Point[]{new Point(1,4),new Point(2,5),new Point(3,6),});
    }
    @Test
    void iteratorSyncLinkedList()
    {
        SynchronizedTabulatedFunction list = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(new double[]{1,2,3},new double[]{4,5,6}));
        Point[] points = new Point[3];
        int i =0;
        for(Point p: list)
        {
            points[i] = p;
            ++i;
        }
        Assertions.assertArrayEquals(points,new Point[]{new Point(1,4),new Point(2,5),new Point(3,6),});
    }

    @Test
    public void testDoSynchronouslyWithReturnValue() {
        // Создаем табулированную функцию
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 1, 10, 10);
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);

        // Операция получения значения y по индексу
        double yValue = synchronizedFunction.doSynchronously(f -> f.getY(5));

        assertEquals(1.0, yValue, 0.001);
    }

    @Test
    public void testDoSynchronouslyWithoutReturnValue() {
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 1, 10, 10);
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);

        // Операция изменения значения y по индексу (без возвращаемого значения)
        synchronizedFunction.doSynchronously(f -> {
            f.setY(5, 2.0);
            return null; // Возвращаем null для Void
        });

        assertEquals(2.0, synchronizedFunction.doSynchronously(f -> f.getY(5)), 0.001);
    }
}
