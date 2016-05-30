package bondarmih.edu.application;

import bondarmih.edu.thread.CountingThread;

/**
 * Created by bondarm on 30.05.16.
 */
public class MultithreadingCounter {
    public static void run() {
        System.out.println("Multithreading counter with 100 threads,");
        System.out.println("each incrementing counter 100_000 times");
        Counter counter = new Counter();
        CountingThread[] threads = new CountingThread[100];
        for (Thread thread : threads) {
            thread = new CountingThread(counter);
            thread.start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter value is " + counter.getValue());
    }
}
