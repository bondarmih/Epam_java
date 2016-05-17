package bondarmih.edu.utility;

/**
 * Created by bondarm on 12.05.16.
 *
 */
public interface TwoWayIterator<T> {
    T previousItem();
    T nextItem();
    boolean hasPrevious();
    boolean hasNext();
}
