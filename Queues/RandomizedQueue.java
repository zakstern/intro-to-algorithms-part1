import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] a; 
    private int N = 0;
    private int randomIndex;
    
    public RandomizedQueue() { // construct an empty randomized queue
        a = (Item[]) new Object[1];
    } 
    
    private void resize(int capacity) { 
        Item[] copy = (Item[]) new Object[capacity]; 
        for (int i = 0; i < N; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }
    
    public boolean isEmpty() { // is the queue empty?
        return N == 0;
    }
    
    public int size() { // return the number of items on the queue
        return N;
    }
    
    public void enqueue(Item item) { // add the item
        if (item == null) throw new NullPointerException();
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }
    
    public Item dequeue() { // delete and return a random item
        if (isEmpty()) throw new NoSuchElementException();
        exchange(a, StdRandom.uniform(N), --N);
        Item item = a[N]; 
        a[N] = null; 
        if (N > 0 && N == a.length/4) resize(a.length/2); 
        return item;
    }
    
    private void exchange(Item[] q, int i, int j) {
        if (i == j)
            return;
        Item swap = q[i];
        q[i] = q[j];
        q[j] = swap;
    }
   
    public Item sample() { // return (but do not delete) a random item
        if (isEmpty()) throw new NoSuchElementException();
        return a[StdRandom.uniform(N)]; 
    }
    
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private int[] indexArray;
        
        public RandomArrayIterator() {
            i = N;
            indexArray = new int[i];
            for (int j = 0; j < i; ++j) {
                indexArray[j] = j;
            }
            StdRandom.shuffle(indexArray);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {        
            if (!hasNext()) throw new NoSuchElementException();
            return a[indexArray[--i]]; 
        }
    }
    
    public static void main(String[] args) {   // unit testing
    }
}