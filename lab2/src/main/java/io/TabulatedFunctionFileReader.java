package io;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader{

    public static void main(String[] args) {
        String filePath = "input/function.txt";

        try (BufferedReader readerArray = new BufferedReader(new FileReader(filePath));
             BufferedReader readerLinkedList = new BufferedReader(new FileReader(filePath))) {

            // Чтение функции с использованием фабрики для массива
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(readerArray, new ArrayTabulatedFunctionFactory());
            System.out.println("ArrayTabulatedFunction:");
            System.out.println(arrayFunction);

            // Чтение функции с использованием фабрики для связного списка
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(readerLinkedList, new LinkedListTabulatedFunctionFactory());
            System.out.println("LinkedListTabulatedFunction:");
            System.out.println(linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
