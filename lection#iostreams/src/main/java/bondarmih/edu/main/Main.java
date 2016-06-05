package bondarmih.edu.main;

import bondarmih.edu.application.Runner;

/**
 * Created by bondarm on 05.06.16.
 */
public class Main {
    public static void main(String[] args) {
        Thread catalogThread = new Runner();
        catalogThread.start();
        try {
            catalogThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
