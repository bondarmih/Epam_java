package bondarmih.edu.diningphilosophers;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by bondarm on 31.05.16.
 */
public class Philosopher implements Runnable {
    private int id;
    private Semaphore leftFork;
    private Semaphore rightFork;
    private PhilosopherState philosopherState = PhilosopherState.THINKING;
    private Semaphore waiter;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, Semaphore waiter) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherState = PhilosopherState.HUNGRY;
        this.waiter = waiter;
    }

    public int getId() {
        return this.id;
    }

    private void setPhilosopherState(PhilosopherState state) {
        this.philosopherState = state;
    }

    public void run() {
        stateOutput();
        while (!Thread.interrupted()) {
            try {
                if (waiter.tryAcquire()) {
                    leftFork.acquire();
                    if (waiter.tryAcquire()) {
                        rightFork.acquire();
                        this.setPhilosopherState(PhilosopherState.EATING);
                        stateOutput();
                        pause();
                        rightFork.release();
                        waiter.release();
                    }
                    leftFork.release();
                    waiter.release();
                }
                this.setPhilosopherState(PhilosopherState.THINKING);
                stateOutput();
                pause();
                this.setPhilosopherState(PhilosopherState.HUNGRY);
                stateOutput();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pause() {
        try {
            Thread.sleep(Math.abs(new Random(35).nextInt(10)));
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " InterruptiedException");
        }
    }

    private void stateOutput() {
        System.out.println("Philosopher " + id + " is " + philosopherState.toString());
    }

}
