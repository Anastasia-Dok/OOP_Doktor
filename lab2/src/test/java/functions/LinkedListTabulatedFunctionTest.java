package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exceptions.InterpolationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class LinkedListTabulatedFunctionTest {
    private LinkedListTabulateFunction list;
    private double[] a = {0, 1, 2, 3, 4, 5, 6, 7};
    private double[] b = {0, 1, 4, 9, 16, 25, 36, 49};

    @Test
    void createList4argumentsTestInts() {
        MathFunction func = new SqrFunction();
        list = new LinkedListTabulateFunction(func, 0, 10, 11);
        for (int i = 0; i < 11; i++) {
            System.out.println(list.getY(i));
        }
    }

    @Test
    void createList4argumentsTestRotating() {
        MathFunction func = new SqrFunction();
        list = new LinkedListTabulateFunction(func, 10, 0, 11);
        for (int i = 0; i < 11; i++) {
            System.out.println(list.getY(i));
        }
    }

    @Test
    void createList4argumentsTestSimilar() {
        MathFunction func = new SqrFunction();
        list = new LinkedListTabulateFunction(func, 10, 10, 10);
        for (int i = 0; i < 11; i++) {
            System.out.println(list.getY(i));
        }
    }

    @Test
    void createList4argumentsTestDoubles() {
        MathFunction func = new SqrFunction();
        list = new LinkedListTabulateFunction(func, 5.5, 13.2, 4);
        for (int i = 0; i < 4; i++) {
            System.out.println(list.getY(i));
        }
    }

    @BeforeEach
    void createList2argumentsTest() {
        list = new LinkedListTabulateFunction(a, b);
    }

    @Test
    void getXTest() {
        double res = list.getX(4);
        Assertions.assertEquals(4, res);
    }

    @Test
    void getYTest() {
        double res = list.getY(3);
        Assertions.assertEquals(9, res);
    }

    @Test
    void setYTest() {
        int index = 2;
        double y = 4;
        list.setY(2, 4);
        Assertions.assertEquals(4, list.getY(2));
    }

    @Test
    void leftBoundTest() {
        double res = list.leftBound();
        Assertions.assertEquals(0, res);
    }

    @Test
    void rightBoundTest() {
        double res = list.rightBound();
        Assertions.assertEquals(7, res);
    }

    @Test
    void indexOfXExistingTest() {
        int res = list.indexOfX(4);
        Assertions.assertEquals(4, res);
    }

    @Test
    void indexOfXNotExistingTest() {
        int res = list.indexOfX(10);
        Assertions.assertEquals(-1, res);
    }

    @Test
    void indexOfYExistingTest() {
        int res = list.indexOfY(4);
        Assertions.assertEquals(2, res);
    }

    @Test
    void indexOfYNotExistingTest() {
        int res = list.indexOfY(100);
        Assertions.assertEquals(-1, res);
    }

    @Test
    void floorIndexOfXMiddleTest() {
        int res = list.floorIndexOfX(5.5);
        Assertions.assertEquals(5, res);
    }

    @Test
    void floorIndexOfXBoundTest() {
        int res = list.floorIndexOfX(100);
        Assertions.assertEquals(8, res);
    }

    @Test
    void getCountTest() {
        Assertions.assertEquals(8, list.getCount());
    }

    @Test
    void removeMiddleTest() {
        list.remove(2);
        list.remove(5);
        Assertions.assertEquals(3, list.getX(2));
        Assertions.assertEquals(7, list.getX(5));
    }

    @Test
    void removeLeftBoundTest() {
        list.remove(0);
        Assertions.assertEquals(1, list.getX(0));
    }

    @Test
    void removeRightBoundTest() {
        list.remove(list.getCount() - 1);
        Assertions.assertEquals(6, list.getX(list.getCount() - 1));
    }
    @Test
    void extrapolateRightTest() {
        double a = list.apply(9);
        Assertions.assertEquals(75,a);
    }
    @Test
    void extrapolateLeftTest() {
        double a = list.apply(2);
        Assertions.assertEquals(4,a);
    }
    @Test
    void interpolateTest1() {
        double a = list.apply(2.5);
        Assertions.assertEquals(6.5,a);
    }
    @Test
    void interpolateTest2() {
        double a = list.interpolate(4.2,4);
        Assertions.assertEquals(17.8,a);
    }
    @Test
    void applyTest1()
    {
        double a = list.apply(4.2);
        Assertions.assertEquals(17.8,a);
    }
    @Test
    void applyTest2()
    {
        double a = list.apply(6);
        Assertions.assertEquals(36,a);
    }

    @Test
    void iteratorForEachTest()
    {
        double[] arrx = new double[8];
        double[] arry = new double[8];
        int i = 0;
        for (Point point : list)
        {
            arrx[i] = point.x;
            arry[i] = point.y;
            ++i;
        }
        Assertions.assertArrayEquals(a,arrx);
        Assertions.assertArrayEquals(b,arry);
    }
    @Test
    void iteratorWhileTest() {
        Iterator<Point> iterator = list.iterator();
        Point point;
        double[] arrx = new double[8];
        double[] arry = new double[8];
        int i = 0;
        while (iterator.hasNext()) {
            point = iterator.next();
            arrx[i] = point.x;
            arry[i] = point.y;
            ++i;
        }
        Assertions.assertArrayEquals(a, arrx);
        Assertions.assertArrayEquals(b, arry);
    }
    @Test
    void exceptionsRemoveTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->list.remove(-1));
        Assertions.assertThrows(IllegalArgumentException.class,()->list.remove(100));
    }
    @Test
    void exceptionsGetTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->list.getNode(-1));
        Assertions.assertThrows(IllegalArgumentException.class,()->list.getNode(100));
        Assertions.assertThrows(IllegalArgumentException.class, ()->list.getY(-1));
        Assertions.assertThrows(IllegalArgumentException.class, ()->list.getY(100));
        Assertions.assertThrows(IllegalArgumentException.class, ()->list.getX(-1));
        Assertions.assertThrows(IllegalArgumentException.class, ()->list.getX(100));
    }
    @Test
    void exceptionsFloorTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->list.floorNodeOfX(-1));
        Assertions.assertThrows(IllegalArgumentException.class,()->list.floorIndexOfX(-1));
    }
    @Test
    void exceptionsConstructorTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->new LinkedListTabulateFunction(new double[]{1},new double[]{1}));
        Assertions.assertThrows(IllegalArgumentException.class,()->new LinkedListTabulateFunction(new double[]{},new double[]{}));
        Assertions.assertThrows(IllegalArgumentException.class,()->new LinkedListTabulateFunction(new SqrFunction(),1,10,1));
        Assertions.assertThrows(IllegalArgumentException.class,()->new LinkedListTabulateFunction(new SqrFunction(),1,10,0));
    }
    @Test
    void exceptionsSetTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class,()->list.setY(-1,10));
        Assertions.assertThrows(IllegalArgumentException.class,()->list.setY(100,10));
    }
    @Test
    void interpolationExceptionTest() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        LinkedListTabulateFunction linkedListFunction = new LinkedListTabulateFunction(xValues, yValues);
        assertThrows(InterpolationException.class,
                () -> linkedListFunction.interpolate(3.5, linkedListFunction.floorNodeOfX(1)));
    }
}
