package bondarmih.edu.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by bondarm on 26.05.16.
 */
public class PersonList<Person> extends ArrayList<Person> {
    public String toString() {
        return Arrays.toString(this.toArray());
    }
}
