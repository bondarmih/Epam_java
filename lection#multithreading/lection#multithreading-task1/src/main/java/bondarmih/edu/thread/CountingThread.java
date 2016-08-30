package bondarmih.edu.thread;

import bondarmih.edu.application.Counter;

/**
 * Created by bondarm on 30.05.16.
 */
public class CountingThread extends Thread {
    private Counter counter;

    public CountingThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i<100000; i++) {
            counter.increase();
        }
    }
}
