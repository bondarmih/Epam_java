package bondarmih.edu.diningphilosophers;

import java.util.Random;

/**
 * Created by bondarm on 31.05.16.
 */
public class Philosopher implements Runnable {
    private int id;
    private Fork leftFork;
    private Fork rightFork;
    private PhilosopherState philosopherState;
    private int mealscount;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherState = PhilosopherState.HUNGRY;
        this.mealscount = 0;
    }

    public int getId() {
        return this.id;
    }

    private PhilosopherState getPhilosopherState() {
        return this.philosopherState;
    }

    private int getMealsCount() {
        return this.mealscount;
    }
    private void setPhilosopherState(PhilosopherState state) {
        this.philosopherState = state;
    }

    public void run() {
        stateOutput();
        while (!Thread.interrupted()) {
            synchronized (leftFork) {
                leftFork.pick(this);
                if (rightFork.getHolder() == null) {
                    synchronized (rightFork) {
                        rightFork.pick(this);
                        this.setPhilosopherState(PhilosopherState.EATING);
                        this.mealscount++;
                        stateOutput();
                        pause();
                        rightFork.drop();
                    }
                }
                leftFork.drop();
            }
            if (this.getPhilosopherState() == PhilosopherState.EATING) {
                this.setPhilosopherState(PhilosopherState.THINKING);
                stateOutput();
                pause();
                this.setPhilosopherState(PhilosopherState.HUNGRY);
                stateOutput();
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
        if (philosopherState == PhilosopherState.EATING) {
            System.out.println("Philosopher " + id + " is " + philosopherState.toString() + " (" + this.getMealsCount() + ")");
        } else {
            System.out.println("Philosopher " + id + " is " + philosopherState.toString());
        }
    }

}
