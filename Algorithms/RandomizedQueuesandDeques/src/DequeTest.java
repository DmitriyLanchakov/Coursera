import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class DequeTest {
    private Deque<String> deque;

    @org.junit.Before
    public void init() {
        deque = new Deque<String>();
    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {

    }

    @org.junit.Test
    public void testSize() throws Exception {

    }

    @org.junit.Test
    public void testAddFirst() throws Exception {


    }

    @org.junit.Test
    public void testAddLast() throws Exception {

    }

    @org.junit.Test
    public void testRemoveFirst() throws Exception {

    }

    @org.junit.Test
    public void testRemoveLast() throws Exception {
        deque.addFirst("one");
        deque.addFirst("two");
        deque.addFirst("three");
        String str = deque.removeLast();
        assertEquals(str, "one");
        str = deque.removeFirst();
        assertEquals(str, "three");
    }

    @org.junit.Test
    public void testIterator() throws Exception {

    }
}