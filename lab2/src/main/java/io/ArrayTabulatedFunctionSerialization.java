package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {

        // Создание табулированной функции
        ArrayTabulatedFunction originalFunction = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{1.0, 4.0, 9.0});

        // Создание оператора для нахождения производных
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();

        // Получение первой и второй производных
        ArrayTabulatedFunction firstDerivative = (ArrayTabulatedFunction) differentialOperator.derive(originalFunction);
        ArrayTabulatedFunction secondDerivative = (ArrayTabulatedFunction) differentialOperator.derive(firstDerivative);

        // Сериализация функций
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized_array_functions.bin"))) {
            FunctionsIO.serialize(outputStream, originalFunction);
            FunctionsIO.serialize(outputStream, firstDerivative);
            FunctionsIO.serialize(outputStream, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения записи
        }

        // Десериализация функций
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized_array_functions.bin"))) {

            TabulatedFunction deserializedOriginal = FunctionsIO.deserialize(inputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(inputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(inputStream);

            // Вывод значений функций
            System.out.println("Original Function:");
            System.out.println(deserializedOriginal);
            System.out.println("First Derivative:");
            System.out.println(deserializedFirstDerivative);
            System.out.println("Second Derivative:");
            System.out.println(deserializedSecondDerivative);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Обработка исключений десериализации
        }
    }
}
