package bondarmih.edu.application;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bondarm on 30.05.16.
 */
public class Counter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increase() {
        this.counter.incrementAndGet();
    }

    public int getValue() {
        return counter.get();
    }
}
