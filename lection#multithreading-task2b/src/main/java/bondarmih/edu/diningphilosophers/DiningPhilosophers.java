package bondarmih.edu.diningphilosophers;

import java.util.concurrent.Semaphore;

/**
 * Created by bondarm on 31.05.16.
 */
public class DiningPhilosophers {

    private static boolean inDeadlock = false;
    public static Semaphore semaphore = new Semaphore(4);

    public static void run() {

        Fork[] forks = new Fork[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }
        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i,forks[i], forks[((i+1) % 5)]);
            Thread ph = new Thread(philosophers[i]);
            ph.start();
        }

        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                if (!inDeadlock && deadlockMark(forks)) {
                    System.out.println("Deadlock!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean deadlockMark(Fork[] forks) {
        boolean result = true;
        int i = 0;
        while (result == true && i < forks.length) {
            if (forks[i].getHolder() == null) {
                return false;
            } else {
                result = result && (forks[i].getHolder().getId() == i);
            }
            i++;
        }
        inDeadlock = result;
        return result;
    }
}
