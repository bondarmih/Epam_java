package bondarmih.edu.application;

import bondarmih.edu.consumer.DailyCacheConsumer;
import bondarmih.edu.consumer.HourlyCacheConsumer;
import bondarmih.edu.injector.Injector;

/**
 * Created by bondarm on 22.05.16.
 */
public class Runner {
    public static void run() {
        ConsumerTester.test(new DailyCacheConsumer());
        ConsumerTester.test(new HourlyCacheConsumer());
        }
}
