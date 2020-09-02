import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue1<Item> implements Iterable<Item> {
    Deque<Item> data;
    public RandomizedQueue1() {                // construct an empty randomized queue
        data = new Deque<Item>();
    }
    public boolean isEmpty() {                // is the randomized queue empty?
        return data.isEmpty();
    }
    public int size() {                        // return the number of items on the randomized queue
        return data.size();
    }
    public void enqueue(Item item) {          // add the item
        if (item == null) {throw new java.lang.IllegalArgumentException();}
        else {data.addFirst(item);}
    }
    public Item dequeue() {                    // remove and return a random item
        if (isEmpty()) {throw new java.util.NoSuchElementException();}
        else{
            int seed = StdRandom.uniform(data.size());
            Deque<Item> temp = new Deque();
            for (int i = 0; i < seed-1; i++){
                temp.addLast(data.removeFirst());
            }
            Item ans = data.removeFirst();
            while(!temp.isEmpty()){
                data.addFirst(temp.removeLast());
            }
            return ans;
        }
        
    }
    public Item sample() {                    // return a random item (but do not remove it)
        if (isEmpty()) {throw new java.util.NoSuchElementException();}
        else{
            int seed = StdRandom.uniform(data.size());
            Deque<Item> temp = new Deque();
            for (int i = 0; i < seed-1; i++){
                temp.addLast(data.removeFirst());
            }
            Item ans = data.removeFirst();
            data.addFirst(ans);
            while(!temp.isEmpty()){
                data.addFirst(temp.removeLast());
            }
            return ans;        
        }
    }
    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private int current;
        private int size;
        private Item[] s;
        private ListIterator(){
            size = size();
            current = 0;
            
            int[] order = new int[size];
            for(int i = 0; i< size(); i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
            
            s = (Item[]) new Object[size()];
            Iterator<Item> ite = data.iterator();
            for(int i = 0; i< size(); i++) {
                s[order[i]] = ite.next();
            }
        }
        
        public boolean hasNext() {  return current > size-1;  }    
        public void remove()     {  throw new java.lang.UnsupportedOperationException();}
        public Item next() {
            if (current >= size()) {throw new java.util.NoSuchElementException(); }
            else return s[current++];            
        }    
    }
    
    public static void main(String[] args) {  // unit testing (optional)
    }    
}