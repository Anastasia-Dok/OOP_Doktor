package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction, Insertable, Removable {
    protected double[] arrX;
    protected double[] arrY;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        count = xValues.length;
        arrX = Arrays.copyOf(xValues, xValues.length);
        arrY = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        arrX = new double[count];
        arrY = new double[count];
        arrX[0] = xFrom;
        arrX[count - 1] = xTo;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 1; i < count - 1; i++)
            arrX[i] = arrX[i - 1] + step;
        for (int i = 0; i < count; i++)
            arrY[i] = source.apply(arrX[i]);

    }

    public double getY(int index) {
        return arrY[index];
    }

    public void setY(int index, double value) {
        arrY[index] = value;
    }

    public double getX(int index) {
        return arrX[index];
    }

    public int indexOfX(double x) {
        for (int i = 0; i < count; i++)
            if (x == arrX[i]) return i;
        return -1;
    }

    public int indexOfY(double y) {
        for (int i = 0; i < count; i++)
            if (y == arrY[i]) return i;
        return -1;
    }

    public double leftBound() {
        return arrX[0];
    }

    public double rightBound() {
        return arrX[count - 1];
    }

    protected int floorIndexOfX(double x) {
        if (x < arrX[0]) return 0;
        if (x > arrX[count - 1]) return count;
        if (indexOfX(x) != -1) return indexOfX(x);
        int index = 0;
        for (int i = 1; i < count; i++) {
            if (x < arrX[i]) {
                index = i - 1;
                break;
            }
        }
        return index;
    }

    protected double extrapolateLeft(double x) {
        if (count == 1) return arrY[0];
        return arrY[0] + (arrY[1] - arrY[0]) / (arrX[1] - arrX[0]) * (x - arrX[0]);
    }

    protected double extrapolateRight(double x) {
        if (count == 1) return arrY[0];
        return arrY[count - 2] + (arrY[count - 1] - arrY[count - 2]) / (arrX[count - 1] - arrX[count - 2]) * (x - arrX[count - 2]);
    }

    protected double interpolate(double x, int floorIndex) {
        if (count == 1) return arrY[0];
        return arrY[floorIndex] + (arrY[floorIndex + 1] - arrY[floorIndex]) / (arrX[floorIndex + 1] - arrX[floorIndex]) * (x - arrX[floorIndex]);
    }

    public void insert(double x, double y) {
        ++count;
        int index = 0;
        int oldBound = count - 1;
        while (index != oldBound && x > arrX[index])
            ++index;
        if (index < arrX.length) {
            if (arrX[index] == x) {
                arrY[index] = y;
                return;
            }
        }
        if (oldBound + 1 >= arrX.length) {
            double[] newArrX = new double[arrX.length * 2];
            double[] newArrY = new double[arrX.length * 2];
            System.arraycopy(arrX, 0, newArrX, 0, arrX.length);
            System.arraycopy(arrY, 0, newArrY, 0, arrY.length);
            arrX = newArrX;
            arrY = newArrY;
        }
        if (index == oldBound) {
            arrX[index] = x;
            arrY[index] = y;
            return;
        }
        for (int i = oldBound; i >= index; --i) {
            arrX[i + 1] = arrX[i];
            arrY[i + 1] = arrY[i];
        }
        arrX[index] = x;
        arrY[index] = y;
    }

    public void remove(int index) {
        double[] newArrX = new double[count - 1];
        double[] newArrY = new double[count - 1];
        for (int i = 0; i < index; i++) {
            newArrX[i] = arrX[i];
            newArrY[i] = arrY[i];
        }
        for (int i = index + 1; i < count; i++) {
            newArrX[i - 1] = arrX[i];
            newArrY[i - 1] = arrY[i];
        }
        count--;
        arrX = newArrX;
        arrY = newArrY;
    }
}
