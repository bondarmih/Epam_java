package bondarmih.edu.list;

import bondarmih.edu.utility.TwoWayIterable;
import bondarmih.edu.utility.TwoWayIterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by bondarm on 10.05.16.
 *
 */
public class DoubleLinkedListImpl<listType extends Comparable>  implements DoubleLinkedListInterface<listType>, TwoWayIterable<listType>, Iterable<listType>{
    private ListElement<listType> header;
    private int size;
    private int modificationsCounter;

    public DoubleLinkedListImpl() {
        this.header = new ListElement<>(null, this.header, this.header);
        this.size = 0;
        this.modificationsCounter = 0;
    }

    @Override
    public listType get(int index) {
        try {
            indexValid(index);
            listType result = element(index).value;
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

    public void add(listType value){
        add(value,size);
    }

    @Override
    public boolean add(listType value, int index) {
        try {
            positionValid(index);
            ListElement<listType> newListElement;
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
    public listType set(listType value, int index) {
        ListElement<listType> old = element(index);
        listType oldValue = old.value;
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
                ListElement<listType> min = element(i);
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
        for (listType i : this) {
            if (i==null) throw new IllegalArgumentException("Element with index = " + counter + " is Null");
            counter++;
        }
    }

    private void swap(int a, int b){
        this.set(this.set(this.get(a),b),a) ;
    }

    private ListElement<listType> element(int index) {
        ListElement<listType> pos = header.next;
        for (int i = 0; i < index; i++)
            pos = pos.next;
        return pos;
    }

    @Override
    public Iterator<listType> iterator() {
        return new MyListIterator();
    }

    @Override
    public TwoWayIterator<listType> twoWayIterator() {
        return new MyTwoWayIterator();
    }


    private class ListElement<ElementType extends Comparable> implements Comparable<ListElement> {
        ElementType value;
        ListElement<ElementType> next;
        ListElement<ElementType> previous;

        ListElement(ElementType value, ListElement<ElementType> next, ListElement<ElementType> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public int compareTo (ListElement otherElement) {
            return this.value.compareTo(otherElement.value);
        }
    }

    private class MyListIterator implements Iterator<listType> {
        private ListElement<listType> currentElement;
        private ListElement<listType> next;
        private int nextIndex;

        MyListIterator() {
            next = header.next;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public listType next() {
            currentElement = next;
            next = next.next;
            nextIndex++;
            return currentElement.value;
        }

    }

    private class MyTwoWayIterator implements TwoWayIterator<listType> {
        private ListElement<listType> currentElement;
        private ListElement<listType> previousItem;
        private ListElement<listType> nextItem;
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
        public listType previousItem() {
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
        public listType nextItem() {
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
