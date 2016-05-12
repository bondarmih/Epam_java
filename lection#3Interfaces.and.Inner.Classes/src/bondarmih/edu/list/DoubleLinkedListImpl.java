package bondarmih.edu.list;

import bondarmih.edu.utility.TwoWayIterable;
import bondarmih.edu.utility.TwoWayIterator;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Created by bondarm on 10.05.16.
 *
 */
public class DoubleLinkedListImpl<listType extends Number & Comparable>  implements DoubleLinkedListInterface<listType>, TwoWayIterable<listType>, Iterable<listType>{
    private ListElement<listType> header;
    private int size;
    private int position;
    //public Iterator<ListElement> iterator;
    //public TwoWayIterable<ListElement> twoWayIterator;


    public DoubleLinkedListImpl() {
        this.header = new ListElement<listType>(null, this.header, this.header);
        this.size = 0;
    }

    @Override
    public listType get(int index) {
        if (indexValid(index)) return element(index).value;
        else return null;
    }

    private boolean indexValid(int index) {
        return index >= 0 && index < size;
    }

    private boolean positionValid(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public boolean add(listType value, int index) {
        if (positionValid(index)) {
            ListElement<listType> newListElement;
            if (size == 0) {
                newListElement = new ListElement<>(value, null, null);
                newListElement.next = newListElement;
                newListElement.previous = newListElement;
            }
            else if (index == size) {
                newListElement = new ListElement<>(value, header.next, header.previous);
                header.previous = newListElement;
            }
            else {
                newListElement = new ListElement<>(value, element(index), element(index).previous);
            }
            size++;
            newListElement.previous.next = newListElement;
            newListElement.next.previous= newListElement;
            return true;
        }
        return false;
    }

    @Override
    public boolean drop(int index) {
        if (indexValid(index)) {
            element(index).next.previous = element(index).previous;
            element(index).previous.next = element(index).next;
            element(index).next = null;
            element(index).previous = null;
            element(index).value = null;
            size--;

            return true;
        }
        return false;
    }

    @Override
    public boolean set(listType value, int index) {
        if (indexValid(index)) {
            element(index).value = value;
            return true;
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void sort() {
        for (int i = 0; i < size; i++) {
            ListElement<listType> min = element(i);
            int minIndex = i;
            for (int k = i + 1; k < size; k++) {
                if (element(k).compareTo(min)<0) {
                    minIndex = k;
                    min = element(k);
                }
            }
            if (i != minIndex) swap(element(i), element(minIndex));
        }
    }

    private void swap(ListElement<listType> a, ListElement<listType> b){
        ListElement<listType> c = a;
        c.next.previous = c;
        c.previous.next = c;

        a = b;
        a.next.previous = a;
        a.previous.next = a;

        b = c;
        b.next.previous = b;
        b.previous.next = b;
    }

    private ListElement<listType> element(int index) {
        ListElement<listType> pos = header;
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


    private class ListElement<ElementType> implements Comparable<ListElement> {
        ElementType value;
        ListElement<ElementType> next;
        ListElement<ElementType> previous;

        ListElement(ElementType value, ListElement<ElementType> next, ListElement<ElementType> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public int compareTo (ListElement otherElement) {
            return new BigDecimal(this.toString()).compareTo(new BigDecimal(otherElement.toString()));
        }
    }

    private class MyListIterator implements Iterator<listType> {
        private ListElement<listType> currentElement;
        private ListElement<listType> next;
        private int nextIndex;

        MyListIterator() {
            // assert isPositionIndex(index);
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
        private ListElement<listType> previous;
        private int previousIndex;

        MyTwoWayIterator() {
            previous = header.previous;
            previousIndex = size - 1;
        }

        @Override
        public listType previous() {
            currentElement = previous;
            previous = previous.previous;
            previousIndex--;
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return previousIndex>=0;
        }
    }

}
