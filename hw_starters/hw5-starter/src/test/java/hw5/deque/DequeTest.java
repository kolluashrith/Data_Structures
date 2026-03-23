package hw5.deque;

import hw5.structures.Deque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract base class for testing implementations of the Deque interface.
 */
public abstract class DequeTest {

    protected Deque<Integer> deque;

    /**
     * Factory method for creating an instance of the Deque implementation under test.
     * @return a new, empty Deque.
     */
    public abstract Deque<Integer> createDeque();

    @BeforeEach
    public void setup() {
        deque = createDeque();
    }

    @Test
    public void testNewDequeIsEmpty() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    // TODO: Add more tests

}
