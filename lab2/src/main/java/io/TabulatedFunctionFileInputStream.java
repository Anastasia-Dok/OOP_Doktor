package io;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulateFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

import static io.FunctionsIO.readTabulatedFunction;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("input/binary function.bin");){
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            TabulatedFunction function = readTabulatedFunction(bufferedInputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(function.toString());

        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Enter the size and value function: ");

        try{
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(reader);

            TabulatedFunction consoleFunction = readTabulatedFunction(bufferedReader, new LinkedListTabulateFunctionFactory());

            System.out.println(new TabulatedDifferentialOperator(new LinkedListTabulateFunctionFactory()).derive(consoleFunction).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}