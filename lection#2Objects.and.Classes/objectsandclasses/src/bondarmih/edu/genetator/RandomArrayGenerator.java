package bondarmih.edu.genetator;

/**
 *
 * Created by bondarm on 05.05.16.
 */
public class RandomArrayGenerator {
    public static double[] generateArray(int length) {
        double module = 100;
        double[] generatedArray = new double[length];
        for (int i=0; i<length; i++) {
            generatedArray[i] = Math.random()*module*2-module;
        }
        return generatedArray;
    }

    public static double[] generateArray() {
        return generateArray(30);
    }
}
