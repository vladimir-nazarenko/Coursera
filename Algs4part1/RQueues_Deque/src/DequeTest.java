import static org.junit.Assert.*;

import org.junit.Test;


public class DequeTest {
    
    public static void main(String[] args){
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < 5; i++)
            dq.addFirst(i);
        for (int i = 0; i < 5; i++)
        {
            int temp = dq.removeLast();
            assertEquals(i, temp);
        }
    }

    @Test
    public void usualQueue_SequenceOfEnqueueAndDequeue_ProcessedCorrectly() {
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < 5; i++)
            dq.addFirst(i);
        for (int i = 0; i < 5; i++)
        {
            int temp = dq.removeLast();
            assertEquals(i, temp);
        }
    }

    @Test
    public void reversedQueue_ProcessedCorrectly()
    {
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < 5; i++)
            dq.addLast(i);
        for (int i = 0; i < 5; i++)
        {
            int temp = dq.removeFirst();
            assertEquals(i, temp);
        }
    }
    
    
    @Test
    public void testIterator() {
        fail("Not yet implemented"); // TODO
    }
    
}
