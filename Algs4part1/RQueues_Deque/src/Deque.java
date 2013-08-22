import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    public Deque() {
        currentSize = 1;
        reallyExist = 0;
        values = (Item[]) new Object[currentSize];
        start = 0;
        end = 0;
    }

    public boolean isEmpty() {
        return reallyExist == 0;
    }

    public int size() {
        return reallyExist;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        int t = startNext();        
        values[t] = item;
        reallyExist++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        values[endNext()] = item;
        reallyExist++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item temp = values[start];
        values[start] = null;
        startPrev();
        reallyExist--;
        return temp;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item temp = values[end];
        values[end] = null;
        endPrev();
        reallyExist--;
        return temp;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void checkSize() {
        if (reallyExist == currentSize)
            resize(currentSize * 2);
        else if ((double) reallyExist / (double) currentSize < 0.25
                && currentSize > 1)
            resize(currentSize / 2);
    }

    private void resize(int newSize) {
        if (newSize < 0)
            throw new IndexOutOfBoundsException("negative resize");
        if (newSize == 0)
            return;
        DequeIterator iterator = this.DequeIterator;
        // end += newSize / 2;
        values = copyArray;
        currentSize = newSize;
    }

    private int endNext() {
        int lastSize = currentSize;
        end--;
        checkSize();
        if (currentSize == lastSize && end == currentSize)
            end = 0;
        return end;
    }

    private int endPrev() {
        int lastSize = currentSize;
        checkSize();
        end++;        
        if (currentSize == lastSize && end == -1)
            end = currentSize - 1;
        return end;
    }

    private int startPrev() {
        int lastSize = currentSize;
        checkSize();
        start--;
        if (currentSize == lastSize && start == -1)
            start = currentSize - 1;
        return start;
    }

    private int startNext() {
        if (start >= 0 && start < currentSize && values[start] == null)
            return start;
        start++;
        checkSize();
        if (start == currentSize)
            return start = 0;
        return start;
    }

    public class DequeIterator implements Iterator<Item> {
        private int current = start;
        private int counter = 0;

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == 0)
                current = currentSize - 1;
            else
                current--;
            return values[current];
        }

        public boolean hasNext() {
            return !(counter > reallyExist);
        }
    }

    private Item[] values;
    private int currentSize;
    private int reallyExist;
    private int start;
    private int end;
}
