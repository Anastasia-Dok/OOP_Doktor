package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulateFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {

        LinkedListTabulateFunction list = new LinkedListTabulateFunction(new ConstantFunction(-1),1,1000,1000);

        Thread readThread = new Thread(new ReadTask(list));
        Thread writeThread = new Thread(new WriteTask (list, 0.5) );

        readThread.start();
        writeThread.start();
    }


}
