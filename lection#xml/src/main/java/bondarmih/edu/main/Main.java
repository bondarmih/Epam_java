package bondarmih.edu.main;

import bondarmih.edu.application.Application;

/**
 * Created by bondarm on 14.06.16.
 */
public class Main {
    public static void main(String[] args) {
        Application xmlThread = new Application();
        xmlThread.start();
        try {
            xmlThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
