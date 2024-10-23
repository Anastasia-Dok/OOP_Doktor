package io;
import functions.ArrayTabulateFunction;
import functions.LinkedListTabulateFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.FunctionsIO.writeTabulatedFunction;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (FileOutputStream outputStream1 = new FileOutputStream("output/array function.bin");
             FileOutputStream outputStream2 = new FileOutputStream("output/linked list function.bin")) {

            BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(outputStream1);
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(outputStream2);

            ArrayTabulateFunction arrayFunction = new ArrayTabulateFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0, 6.0});
            LinkedListTabulateFunction linkedListFunction = new LinkedListTabulateFunction(new double[]{1.0, 2.0, 3.0}, new double[]{3.0, 6.0, 9.0});

            writeTabulatedFunction(bufferedOutputStream1, arrayFunction);
            writeTabulatedFunction(bufferedOutputStream2, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
