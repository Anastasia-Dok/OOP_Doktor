package concurrent;

import functions.TabulatedFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class IntegralTaskExecutor {
    private final int numThreads;
    private final ExecutorService executor;


    public IntegralTaskExecutor(int numThreads) {
        this.numThreads = numThreads;
        this.executor = Executors.newFixedThreadPool(numThreads);

    }

    public double integrate(TabulatedFunction function) throws ExecutionException, InterruptedException {
        List<Future<Double>> results = new ArrayList<>();
        double integralResult = 0.0;

        double intervalLength = Math.abs((function.rightBound() - function.leftBound()))/numThreads;

        for (int i = 0; i < numThreads; i++) {
            double segmentStart = function.leftBound() + i * intervalLength;
            double segmentEnd = segmentStart + intervalLength;
            IntegralTask integral = new IntegralTask(function, segmentStart, segmentEnd);
            results.add(executor.submit(integral));
        }

        for (Future<Double> result : results) {
                integralResult += result.get();

        }


        return integralResult;
    }


    public void shutdown() {
        executor.shutdown();
    }

}


