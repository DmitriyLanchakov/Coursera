import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by den on 02.02.15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 1;
    private Item[] queue;
    private int n;
    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[DEFAULT_CAPACITY];
        n = 0;
    }
    // is the queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Item is null");
        if (n == queue.length) {
            resize(queue.length*2);
        }
        queue[n++] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Empty queue");
        StdRandom.shuffle(queue, 0, n-1); //перемешиваем в произвольном порядке массив и берем последний элемент
        Item randomItem = queue[n-1];
        queue[n-1] = null;
        n--;
        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return randomItem;
    }

    private void resize(int capacity) {
        Item[] resizingItem = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            resizingItem[i] = queue[i];
        queue = resizingItem;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Empty queue");
        StdRandom.shuffle(queue, 0, n-1);
        return queue[n-1];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int iterInd = 0;
        private Item[] iterQueue;

        public RandomizedQueueIterator() {
            iterQueue = (Item[]) new Object[n];
            for (int i = 0; i < n; i++)
                iterQueue[i] = queue[i];
        }

        @Override
        public boolean hasNext() {
            return iterInd < n;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return queue[iterInd++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("First");
        rq.enqueue("Hello");
        rq.enqueue("World");

        Iterator it = rq.iterator();
        System.out.println(rq.dequeue());
        for (String str : rq)
            System.out.println(it.next());


    }
}