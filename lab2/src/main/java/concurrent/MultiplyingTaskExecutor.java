package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction( new UnitFunction(), 1,1000,1000);

        //Создание листа потоков
        List<Thread> threads = new ArrayList<>();

        // Запуск 10 потоков, выполняющих задачу умножения
        for (int i = 0; i < 10; i++) {
            MultiplyingTask task = new MultiplyingTask(function);
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        // Старт всех потоков
        for (Thread thread : threads) {
            thread.start();
        }

        while (!threads.isEmpty()) {
            threads.removeIf(thread -> !thread.isAlive());
        }




        System.out.println(function);
    }
}
