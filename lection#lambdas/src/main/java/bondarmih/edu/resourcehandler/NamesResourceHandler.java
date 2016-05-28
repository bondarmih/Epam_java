package bondarmih.edu.resourcehandler;

import bondarmih.edu.util.RandomInt;
import bondarmih.edu.util.Sex;
import bondarmih.edu.util.TextFileReader;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by bondarm on 25.05.16.
 */
public class NamesResourceHandler {
    @Nullable
    private ArrayList<String> maleNames;
    @Nullable
    private ArrayList<String> femaleNames;

    public NamesResourceHandler() {
        this.maleNames = new ArrayList<>(TextFileReader.readFromFile("male_names.txt"));
        this.femaleNames = new ArrayList<>(TextFileReader.readFromFile("female_names.txt"));
    }

    public String getRandomName(Sex sexOfName) {
        try {
            ArrayList<String> nameList = null;
            switch (sexOfName) {
                case MALE: {
                    if (maleNames != null) {
                        nameList = maleNames;
                    }
                    else {
                        throw new NoSuchElementException("Male names list is null");
                    }
                    break;
                }
                case FEMALE: {
                    if (femaleNames != null) {
                        nameList = femaleNames;
                    }
                    else {
                        throw new NoSuchElementException("Female names list is null");
                    }
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
                return nameList.get(RandomInt.randInt(0, nameList.size()-1));
        }
        catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println(e.getMessage() + ". Name ANONYMOUS is returned");
            return "ANONUMOUS";
        }
    }
}
