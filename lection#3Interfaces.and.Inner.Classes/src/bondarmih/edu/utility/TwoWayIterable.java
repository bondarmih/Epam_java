package bondarmih.edu.utility;

import java.util.Iterator;

/**
 * Created by bondarm on 10.05.16.
 *
 */
public interface TwoWayIterable<T> {
    TwoWayIterator<T> twoWayIterator();

}
