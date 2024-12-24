package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {

        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(new ConstantFunction(-1),1,1000,1000);

        Thread readThread = new Thread(new ReadTask(list));
        Thread writeThread = new Thread(new WriteTask (list, 0.5) );

        readThread.start();
        writeThread.start();
    }


}
