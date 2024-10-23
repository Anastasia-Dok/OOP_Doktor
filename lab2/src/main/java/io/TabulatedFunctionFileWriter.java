package io;

import functions.ArrayTabulateFunction;
import functions.LinkedListTabulateFunction;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {

        double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yValues = {1.0, 4.0, 9.0, 16.0, 25.0};


        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {

            FunctionsIO.writeTabulatedFunction(arrayWriter, new ArrayTabulateFunction(xValues, yValues));
            FunctionsIO.writeTabulatedFunction(linkedListWriter, new LinkedListTabulateFunction(xValues, yValues));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
