import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by den on 02.02.15.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque()     {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() { return N == 0; }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Item is null");
        if (isEmpty()) {
            Node node = new Node();
            node.item = item;
            first = node;
            last = node;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            oldFirst.prev = first;
            first.next = oldFirst;
        }
        N++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Item is null");
        if (isEmpty()) {
            Node node = new Node();
            node.item = item;
            first = node;
            last = node;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
            last.prev = oldLast;
        }
        N++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is Empty");
        Item firstItem = first.item;
        first = first.next;
        N--;
        if (isEmpty())
            last = null;
        else
            first.prev = null;
        return firstItem;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is Empty");
        Item lastItem = last.item;
        last = last.prev;
        N--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return lastItem;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Deque has not next element");
            Item thisItem = current.item;
            current = current.next;
            return thisItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("Hello");
        deque.addFirst("First");
        System.out.println(deque.removeLast());
//        deque.addLast("Last");
//        Iterator it = deque.iterator();
//        for (String str : deque) {
//            System.out.println(str);
//        }
//        deque.removeLast();
//        System.out.println();
//        for (String str : deque) {
//            System.out.println(str);
//        }
//        deque.removeFirst();
//        System.out.println();
//        for (String str : deque) {
//            System.out.println(str);
//        }
//        deque.removeFirst();
//        System.out.println();
//        for (String str : deque) {
//            System.out.println(str);
//        }
    }
}