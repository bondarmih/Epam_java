package bondarmih.edu.diningphilosophers;

import java.util.concurrent.Semaphore;

/**
 * Created by bondarm on 31.05.16.
 */
public class DiningPhilosophers {

    private static boolean inDeadlock = false;

    public static void run() {

        Semaphore[] forks = new Semaphore[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Semaphore(1);
        }
        Semaphore waiter = new Semaphore(4);
        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i,forks[i], forks[((i+1) % 5)], waiter);
            Thread ph = new Thread(philosophers[i]);
            ph.start();
        }

        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
