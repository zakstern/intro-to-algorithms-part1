import java.util.Iterator;
import java.util.NoSuchElementException;

public class Subset {    
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> R = new RandomizedQueue<String>();
      
        for (;;) {
            try {
                String string = StdIn.readString();
                R.enqueue(string);
            } catch (NoSuchElementException e) {
                break;
            }
        }
        Iterator<String> iterator = R.iterator();
        for (int i = 0; i < k; i++) {
            String element = iterator.next();
            StdOut.println(element);
        }
    }
}