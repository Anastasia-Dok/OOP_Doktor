package io;

import functions.ArrayTabulateFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        // Укажите путь для сохранения файла
        String filePath = "output/serialized_array_functions.bin";

        // Создание табулированной функции
        ArrayTabulateFunction originalFunction = new ArrayTabulateFunction(new double[]{1.0, 2.0, 3.0}, new double[]{1.0, 4.0, 9.0});

        // Создание оператора для нахождения производных
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();

        // Получение первой и второй производных
        ArrayTabulateFunction firstDerivative = (ArrayTabulateFunction) differentialOperator.derive(originalFunction);
        ArrayTabulateFunction secondDerivative = (ArrayTabulateFunction) differentialOperator.derive(firstDerivative);

        // Сериализация функций
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            FunctionsIO.serialize(bos, originalFunction);
            FunctionsIO.serialize(bos, firstDerivative);
            FunctionsIO.serialize(bos, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения записи
        }

        // Десериализация функций
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            TabulatedFunction deserializedOriginal = FunctionsIO.deserialize(bos, ArrayTabulatedFunctionFactory.getInstance());
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bos, ArrayTabulatedFunctionFactory.getInstance());
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bos, ArrayTabulatedFunctionFactory.getInstance());

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

