package bondarmih.edu.diningphilosophers;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by bondarm on 31.05.16.
 */
public class Philosopher implements Runnable {
    private int id;
    private Fork leftFork;
    private Fork rightFork;
    private PhilosopherState philosopherState = PhilosopherState.THINKING;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherState = PhilosopherState.HUNGRY;
    }

    public int getId() {
        return this.id;
    }

    private PhilosopherState getPhilosopherState() {
        return this.philosopherState;
    }
    private void setPhilosopherState(PhilosopherState state) {
        this.philosopherState = state;
    }

    public void run() {
        stateOutput();
        while (!Thread.interrupted()) {
            leftFork.pick(this);
            rightFork.pick(this);
            this.setPhilosopherState(PhilosopherState.EATING);
            stateOutput();
            pause();
            rightFork.drop();
            leftFork.drop();
            this.setPhilosopherState(PhilosopherState.THINKING);
            stateOutput();
            pause();
            this.setPhilosopherState(PhilosopherState.HUNGRY);
            stateOutput();
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

    private boolean deadlockMark() {

        return false;
    }
}
