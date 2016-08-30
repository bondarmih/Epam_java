package bondarmih.edu.list;

/**
 * Created by bondarm on 10.05.16.
 *
 */

public interface DoubleLinkedListInterface<T> {
    T get(int index);
    boolean add(T value, int index);
    boolean drop(int index);
    T set(T value, int index);
    int getSize();
    void sort();
}
