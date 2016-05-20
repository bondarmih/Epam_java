package bondarmih.edu.list;

import bondarmih.edu.utility.Function;
import bondarmih.edu.utility.TwoWayIterable;
import bondarmih.edu.utility.TwoWayIterator;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by bondarm on 10.05.16.
 *
 */
public class DoubleLinkedListImpl<T extends Comparable>  implements DoubleLinkedListInterface<T>, TwoWayIterable<T>, Iterable<T>{
    private ListElement<T> header;
    private int size;
    private int modificationsCounter;

    public DoubleLinkedListImpl() {
        this.header = new ListElement<>(null, this.header, this.header);
        this.size = 0;
        this.modificationsCounter = 0;
    }

    @Override
    public T get(int index) {
        try {
            indexValid(index);
            T result = element(index).value;
            if (result == null) throw new IllegalArgumentException("Element with index = " + index + " is Null");
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Element extract operation can not be performed. "+ e.getMessage());
        }
        return element(index).value;
    }

    private void indexValid(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index is " + index + ", must be in range (0;" + (size-1)+")");
    }

    private void positionValid(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index is " + index + ", must be in range (0;" + size+")");
    }

    public void add(T value){
        add(value,size);
    }

    @Override
    public boolean add(T value, int index) {
        try {
            positionValid(index);
            ListElement<T> newListElement;
            if (size == 0) {
                newListElement = new ListElement<>(value, null, null);
                newListElement.next = newListElement;
                newListElement.previous = newListElement;
                header.next = newListElement;
                header.previous = newListElement;
            } else if (index == 0) {
                newListElement = new ListElement<>(value, header.next, header.previous);
                header.next = newListElement;
            } else if (index == size) {
                newListElement = new ListElement<>(value, header.next, header.previous);
                header.previous = newListElement;
            } else {
                newListElement = new ListElement<>(value, element(index), element(index).previous);
            }
            size++;
            modificationsCounter++;
            newListElement.previous.next = newListElement;
            newListElement.next.previous = newListElement;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Element add operation can not be performed: "+ e.getMessage());

        }
        return true;

    }

    @Override
    public boolean drop(int index) {
        try {
            indexValid(index);
            element(index).next.previous = element(index).previous;
            element(index).previous.next = element(index).next;
            size--;
            modificationsCounter++;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Element drop operation can not be performed: "+ e.getMessage());
        }
        return true;
    }

    @Override
    public T set(T value, int index) {
        ListElement<T> old = element(index);
        T oldValue = old.value;
        try {
            indexValid(index);
            old.value = value;
            modificationsCounter++;

        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Value set operation can not be performed: "+ e.getMessage());
        }
        return oldValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void sort() {
        try {
            checkListForNullValues();

            for (int i = 0; i < size; i++) {
                ListElement<T> min = element(i);
                int minIndex = i;

                for (int k = i + 1; k < size; k++) {
                    if (element(k).compareTo(min) < 0) {
                        minIndex = k;
                        min = element(k);
                    }

                }
                if (i != minIndex) swap(i, minIndex);
            }
            modificationsCounter++;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Sort operation can not be performed: " + e.getMessage());
            throw e;
        }
    }

    private void checkListForNullValues() {
        int counter = 0;
        for (T i : this) {
            if (i==null) throw new IllegalArgumentException("Element with index = " + counter + " is Null");
            counter++;
        }
    }

    private void swap(int a, int b){
        this.set(this.set(this.get(a),b),a) ;
    }

    private ListElement<T> element(int index) {
        ListElement<T> pos = header.next;
        for (int i = 0; i < index; i++)
            pos = pos.next;
        return pos;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public TwoWayIterator<T> twoWayIterator() {
        return new MyTwoWayIterator();
    }

    public static <T extends Comparable, F extends Comparable> DoubleLinkedListImpl<F> map(DoubleLinkedListImpl<T> inputList, Function <T,F> mapFunction) {
        DoubleLinkedListImpl<F> outputList = new DoubleLinkedListImpl<>();
        for (T listElement : inputList ) {
            outputList.add(mapFunction.apply(listElement));
        }
        return  outputList;

    }

    private class ListElement<E extends Comparable> implements Comparable<ListElement> {
        E value;
        ListElement<E> next;
        ListElement<E> previous;

        ListElement(E value, ListElement<E> next, ListElement<E> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public int compareTo (ListElement otherElement) {
            return this.value.compareTo(otherElement.value);
        }
    }

    private class MyListIterator implements Iterator<T> {
        private ListElement<T> currentElement;
        private ListElement<T> next;
        private int nextIndex;

        MyListIterator() {
            next = header.next;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            currentElement = next;
            next = next.next;
            nextIndex++;
            return currentElement.value;
        }

    }

    private class MyTwoWayIterator implements TwoWayIterator<T> {
        private ListElement<T> currentElement;
        private ListElement<T> previousItem;
        private ListElement<T> nextItem;
        private int previousIndex;
        private int nextIndex;
        private int expectedModificationsCount = modificationsCounter;

        MyTwoWayIterator() {
            previousItem = header.previous;
            nextItem = header.next;
            previousIndex = size - 1;
            nextIndex = 0;
        }

        @Override
        public T previousItem() {
            checkForModifications();
            currentElement = previousItem;
            previousItem = previousItem.previous;
            previousIndex--;
            if (nextIndex==0)
                nextIndex = size - 1;
            else
                nextIndex--;
            return previousItem.value;
        }

        @Override
        public T nextItem() {
            checkForModifications();
            currentElement = nextItem;
            nextItem = nextItem.next;
            nextIndex++;
            if (previousIndex==size-1)
                previousIndex=0;
            else
                previousIndex++;
            return nextItem.value;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        private void checkForModifications(){
            if (modificationsCounter != expectedModificationsCount)
                throw new ConcurrentModificationException();
        }
    }

}
