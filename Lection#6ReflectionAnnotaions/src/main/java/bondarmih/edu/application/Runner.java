package bondarmih.edu.application;

import bondarmih.edu.consumer.DailyCacheConsumer;
import bondarmih.edu.consumer.DailyCacheConsumerInherit;
import bondarmih.edu.consumer.HourlyCacheConsumer;

/**
 * Created by bondarm on 22.05.16.
 */
public class Runner {
    public static void run() {
        try {
            //ConsumerTester.test(new DailyCacheConsumer());
            //ConsumerTester.test(new HourlyCacheConsumer());
            ConsumerTester.test(new DailyCacheConsumerInherit());
        } catch (IllegalStateException e) {
            System.exit(-1);
        }
        }
}
