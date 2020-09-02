import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    public Deque() {                          // construct an empty deque      
        size = 0;  
    }
    public boolean isEmpty() {                // is the deque empty?
        return size == 0;
    }
    public int size() {                       // return the number of items on the deque
        return size;
    }
    public void addFirst(Item item) {         // add the item to the front
        if (item == null) throw new java.lang.IllegalArgumentException();
        else {
            Node oldfirst =  first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;   
            first.prev = null;
            if (isEmpty()) last = first;    
            else oldfirst.prev = first;
            size++;
        }    
    }    
    public void addLast(Item item) {           // add the item to the end
        if (item == null) throw new java.lang.IllegalArgumentException();
        else {
            Node oldlast = last; 
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            if (isEmpty()) first = last;
            else           oldlast.next = last;         
            size++;
        }    
    }    
    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            Item firstItem = first.item;
            first = first.next;
            size--;
            return firstItem;            
        }
    }
    public Item removeLast() {               // remove and return the item from the end
        if (isEmpty()) throw new java.util.NoSuchElementException();
        else {
            Item lastItem = last.item;
            last = last.prev;
            size--;
            return lastItem;            
        }
    }
    
    public Iterator<Item> iterator() {      // return an iterator over items in order from front to end
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        private int index = 0; 
        public boolean hasNext() {  return index <= size -1;  }    
        public void remove()     {  throw new java.lang.UnsupportedOperationException(); }
        public Item next() {
            if (current == null) { throw new java.util.NoSuchElementException(); }
            else {
                Item item = current.item;
                current = current.next; 
                index ++;
                return item;
            }
        }    
    }
    public static void main(String[] args) {  // unit testing (optional)
    }    
}
