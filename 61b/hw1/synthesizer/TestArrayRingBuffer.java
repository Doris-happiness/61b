package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, arb.peek());
        assertEquals(3, arb.fillCount);
        assertEquals(1, arb.dequeue());
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 