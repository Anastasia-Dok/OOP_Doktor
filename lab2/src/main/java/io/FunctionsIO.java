package io;


import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.text.ParseException;

public final class FunctionsIO {

    private FunctionsIO() {
        throw new UnsupportedOperationException("Cannot instantiate FunctionsIO class");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) throws IOException {
        PrintWriter printWriter = new PrintWriter(writer);
        // Записываем количество точек
        printWriter.println(function.getCount());

        // Записываем каждую точку
        for (Point point : function) {
            printWriter.printf("%f %f%n", point.x, point.y);
        }

        // Пробрасываем данные из буфера в поток
        printWriter.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        try {
            // Чтение первой строки - количество точек
            int count = Integer.parseInt(reader.readLine());

            // Инициализация массивов для значений x и y
            double[] xValues = new double[count];
            double[] yValues = new double[count];

            // Форматтер для чисел с плавающей запятой
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

            // Чтение точек функции
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                String[] parts = line.split(" ");

                try {
                    // Преобразование строк в числа
                    xValues[i] = numberFormat.parse(parts[0]).doubleValue();
                    yValues[i] = numberFormat.parse(parts[1]).doubleValue();
                } catch (ParseException e) {
                    throw new IOException("Error parsing numbers", e);
                }
            }

            // Возврат созданной функции с помощью фабрики
            return factory.create(xValues, yValues);

        } catch (NumberFormatException e) {
            throw new IOException("Error reading number of points", e);
        }
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException{
        DataOutputStream stream = new DataOutputStream(outputStream);
        stream.writeInt(function.getCount());

        for (Point point : function) {
            stream.writeDouble(point.x);
            stream.writeDouble(point.y);
        }

        stream.flush();
    }
    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream input = new DataInputStream(inputStream);
        int count = input.readInt();

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; i++) {
            xValues[i] = input.readDouble();
            yValues[i] = input.readDouble();
        }

        return factory.create(xValues, yValues);
    }
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)) {
            objectOutputStream.writeObject(function);
        }
        stream.flush();
    }
    static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(stream);

        Object obj = inputStream.readObject();
        return (TabulatedFunction) obj;
    }

}
