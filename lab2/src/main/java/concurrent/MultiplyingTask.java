package concurrent;

import functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function){

        this.function = function;
    }

    public void run(){
        for(int i = 0; i < function.getCount();i++){
            synchronized (function) {
                function.setY(i, function.getY(i)*2);
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " completed the task");
    }
}
