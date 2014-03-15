import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    
    private class Node { 
        private Item item; 
        private Node next;
        private Node prev;
    }

    public Deque() { // construct an empty deque
        size = 0;
        first = null;
        last = null;
    }
    
    public boolean isEmpty() { return size == 0; }
    
    public int size() { return size; } // return the number of items on the deque
    
    public void addFirst(Item item) { // insert the item at the front
        if (item == null) throw new NullPointerException();
        Node oldfirst = first; 
        first = new Node(); 
        first.item = item; 
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty()) last = first; //adding to an empty array
        else oldfirst.prev = first;
        size++;
    }
    
    public void addLast(Item item) { // insert the item at the end
        if (item == null) throw new NullPointerException("Item is null");
        Node oldlast = last; 
        last = new Node(); 
        last.item = item; 
        last.prev = oldlast;
        last.next = null; 
        if (isEmpty()) first = last; 
        else oldlast.next = last;
        size++;
    }
    
    public Item removeFirst() { // delete and return the item at the front
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item firstItem = first.item;
        first = first.next;
        if (size() > 1) first.prev = null;
        size--;
        return firstItem;     
    }
    
    public Item removeLast() { // delete and return the item at the end
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item lastItem = last.item;
        last = last.prev;
        if (size() > 1) last.next = null;
        size--;
        return lastItem;
    }
    
    public Iterator<Item> iterator() { return new DequeIterator(); } 
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public static void main(String[] args) { // unit testing
        //Unit Tests
        Deque testDeque = new Deque();
        //for ( int i = 0; i<30; i++){
            
       // }
        //for ( int i = 0; i<30; i++){
            //StdOut.println(testDeque.removeFirst() + "\n");
       // }
        testDeque.addFirst(1);
        StdOut.println(testDeque.removeFirst() + "\n");
        testDeque.addFirst(1);
        StdOut.println(testDeque.removeLast() + "\n");
        testDeque.addLast(1);
        StdOut.println(testDeque.removeFirst() + "\n");
        testDeque.addLast(1);
        StdOut.println(testDeque.removeLast() + "\n");
    }
}