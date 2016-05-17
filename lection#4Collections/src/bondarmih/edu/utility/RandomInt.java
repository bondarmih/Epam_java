package bondarmih.edu.utility;

import java.util.Random;

/**
 * Created by bondarm on 17.05.16.
 *
 */
public class RandomInt {
    public static int randInt(int min, int max) throws IllegalArgumentException{
        try {
            if (min>max) throw new IllegalArgumentException("MIN must be smaller than MAX");
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            return randomNum;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Method randInt can't be executed correctly: " + e.getMessage());
            throw e;
        }
    }
}
