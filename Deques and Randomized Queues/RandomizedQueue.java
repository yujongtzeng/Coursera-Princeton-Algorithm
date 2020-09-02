import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int size;
    public RandomizedQueue() {                // construct an empty randomized queue
        data = (Item[]) new Object[1];
        size = 0;
    }
    public boolean isEmpty() {                // is the randomized queue empty?
        return size == 0;
    }
    public int size() {                        // return the number of items on the randomized queue
        return size;
    }
    public void enqueue(Item item) {          // add the item
        if (item == null) { throw new java.lang.IllegalArgumentException(); }
        else {
            if (size == data.length) { resize(2 * data.length); }
            data[size++] = item;
        }
    }
    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];
        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        data = copy;
    }
    public Item dequeue() {                    // remove and return a random item
        if (isEmpty()) {throw new java.util.NoSuchElementException(); }
        int k = StdRandom.uniform(size);
        Item ans = data[k];
        data[k] = data[--size];
        data[size] = null;
        if (size < data.length/4) { resize(data.length/2); }
        return ans;
    }
    public Item sample() {                    // return a random item (but do not remove it)
        if (isEmpty()) {throw new java.util.NoSuchElementException(); }
        int k = StdRandom.uniform(size);
        return data[k];
    }
    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private int current;        
        final private int[] MAP;
        private ListIterator() {
            size = size();
            current = 0;            
            MAP = new int[size];
            for (int i = 0; i < size(); i++) {
                MAP[i] = i;
            }
            StdRandom.shuffle(MAP);
        }
        
        public boolean hasNext() {  return current <= size-1;  }    
        public void remove()     {  throw new java.lang.UnsupportedOperationException(); }
        public Item next() {
            if (current >= size()) { throw new java.util.NoSuchElementException(); }
            else return data[MAP[current++]];            
        }    
    }
    
    public static void main(String[] args) {  // unit testing (optional)
    }    
}