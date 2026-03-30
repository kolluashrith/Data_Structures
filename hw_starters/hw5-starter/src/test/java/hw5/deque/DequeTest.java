package hw5.deque;

import hw5.exceptions.EmptyException;
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

    @Test
    public void testAddFirstAddsElementToEmptyDeque() {
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
    }

    @Test
    public void testAddLastAddsElementToEmptyDeque() {
        deque.addLast(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
    }

    @Test
    public void testAddFirstAddsElementToFrontWithMultipleElements() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        assertEquals(3, deque.size());
        assertEquals(1, deque.peekFirst());
        assertEquals(3, deque.peekLast());
    }

    @Test
    public void testAddLastAddsElementToEndWithMultipleElements() {
        deque.addLast(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        assertEquals(4, deque.size());
        assertEquals(4, deque.peekLast());
        assertEquals(1, deque.peekFirst());
    }

    @Test
    public void testRemoveFirstThrowsEmptyExceptionIfDequeIsEmpty() {
        try {
            deque.removeFirst();
            fail("removeFirst failed to call empty exception when called on empty deque.");
        } catch (EmptyException e) {
            System.out.println("Empty exception thrown.");
        }
    }

    @Test
    public void testRemoveFirstOnlyAffectsFirstElement() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        int removed = deque.removeFirst();

        assertEquals(2, deque.size());
        assertEquals(2, deque.peekFirst());
        assertEquals(3, deque.peekLast());
        assertEquals(1, removed);
    }

    @Test
    public void testRemoveFirstWithMultipleRemovals() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        int removed1 = deque.removeFirst();
        int removed2 = deque.removeFirst();
        int removed3 = deque.removeFirst();

        assertEquals(0, deque.size());
        assertEquals(1, removed1);
        assertEquals(2, removed2);
        assertEquals(3, removed3);
    }

    @Test
    public void testRemoveLastThrowsEmptyExceptionIfDequeIsEmpty() {
        try {
            deque.removeLast();
            fail("removeLast failed to call empty exception when called on empty deque.");
        } catch (EmptyException e) {
            System.out.println("Empty exception thrown.");
        }
    }

    @Test
    public void testRemoveLastOnlyAffectsLastElement() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        int removed = deque.removeLast();

        assertEquals(2, deque.size());
        assertEquals(1, deque.peekFirst());
        assertEquals(2, deque.peekLast());
        assertEquals(3, removed);
    }

    @Test
    public void testRemoveLastWithMultipleRemovals() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        int removed1 = deque.removeLast();
        int removed2 = deque.removeLast();
        int removed3 = deque.removeLast();

        assertEquals(0, deque.size());
        assertEquals(3, removed1);
        assertEquals(2, removed2);
        assertEquals(1, removed3);
    }

    @Test
    public void testPeekFirstThrowsEmptyExceptionIfDequeIsEmpty() {
        try {
            deque.peekFirst();
            fail("peekFirst failed to call empty exception when called on empty deque.");
        } catch (EmptyException e) {
            System.out.println("Empty exception thrown.");
        }
    }

    @Test
    public void testPeekFirstReturnsFirstElement() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);

        assertEquals(1, deque.peekFirst());
    }

    @Test
    public void testPeekFirstDoesNotAffectDeque() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);

        assertEquals(1, deque.peekFirst());
        assertEquals(4,  deque.size());
    }

    @Test
    public void testPeekFirstUpdatesCorrectlyWithRemovals() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);

        assertEquals(1, deque.peekFirst());

        deque.removeFirst();
        assertEquals(2, deque.peekFirst());

        deque.removeLast();
        assertEquals(2, deque.peekFirst());
    }

    @Test
    public void testPeekLastThrowsEmptyExceptionIfDequeIsEmpty() {
        try {
            deque.peekLast();
            fail("peekLast failed to call empty exception when called on empty deque.");
        } catch (EmptyException e) {
            System.out.println("Empty exception thrown.");
        }
    }

    @Test
    public void testPeekLastReturnsLastElement() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);

        assertEquals(4, deque.peekLast());
    }

    @Test
    public void testPeekLastDoesNotAffectDeque() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);

        assertEquals(4, deque.peekLast());
        assertEquals(4, deque.size());
    }

    @Test
    public void testPeekLastUpdatesCorrectlyWithRemovals() {
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);

        assertEquals(4, deque.peekLast());

        deque.removeLast();
        assertEquals(3, deque.peekLast());

        deque.removeFirst();
        assertEquals(3, deque.peekLast());
    }

    @Test
    public void testPeekFirstAndPeekLastReturnSameValueWhenOnlyOneElementInDeque() {
        deque.addFirst(3);
        assertEquals(3, deque.peekFirst());
        assertEquals(3, deque.peekLast());
    }

    @Test
    public void testAddingManyValuesMaintainsDequeFunctions() {
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }
        assertEquals(99, deque.peekLast());
        assertEquals(0, deque.peekFirst());
        assertEquals(100, deque.size());
        assertFalse(deque.isEmpty());

        for (int i = 0; i < 100; i++) {
            assertEquals(i, deque.removeFirst());
        }
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }
}
