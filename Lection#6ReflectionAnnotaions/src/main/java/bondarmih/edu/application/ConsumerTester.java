package bondarmih.edu.application;

import bondarmih.edu.consumer.Consumer;
import bondarmih.edu.consumer.DailyCacheConsumer;
import bondarmih.edu.injector.Injector;

/**
 * Created by bondarm on 22.05.16.
 */
public class ConsumerTester {
    public static void test(Consumer consumer) {
        System.out.println(consumer.getClass() + " cache inject test");
        Injector.inject(consumer);
        consumer.printOutput();
    }
}
