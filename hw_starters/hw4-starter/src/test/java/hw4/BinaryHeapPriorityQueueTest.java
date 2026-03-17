package hw4;

import exceptions.EmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BinaryHeapPriorityQueueTest extends PriorityQueueTest {

  @Override
  protected PriorityQueue<Integer> createQueue() {
    return new BinaryHeapPriorityQueue<>();
  }

  @Test
  void testDefaultConstructorCreatesEmptyQueue() {
      assertTrue(pq.empty());
  }

  @Test
  void testInsertOneElement() {
    pq.insert(1);
    assertFalse(pq.empty());
  }

  @Test
  void testInsertThreeElementsInOrder() {
    int[] actual = {1, 2, 3};
    for (int i : actual) {
      pq.insert(i);
    }
    assertTrue(orderIsPreserved(actual));
  }

  private boolean orderIsPreserved(int[] actualOrder) {
    int index = 0;
    for (int value : pq) {
      if (!(actualOrder[index++] == value)) {
        return false;
      }
    }
    return true;
  }

  @Test
  void testInsertThreeElementsOutOfOrder() {
    int[] reverse = {3, 2, 1};
    int[] actual = {1, 3, 2}; //3 should swap with 2 and then 1 should swap with 2
    for (int i : reverse) {
      pq.insert(i);
    }
    assertTrue(orderIsPreserved(actual));
  }

  @Test
  void testInsertFiveElementsOutOfOrder() {
    int[] random = {3, 5, 1, 9, 3, 2, 1};
    int[] actual = {1, 3, 1, 9, 5, 3, 2};
    for (int i : random) {
      pq.insert(i);
    }
    assertTrue(orderIsPreserved(actual));
  }

  @Test
  void testRemoveOneElement() {
    pq.insert(1);
    pq.remove();
    assertTrue(pq.empty());
  }

  @Test
  void testBestReturnsCorrectlyWithOneElement() {
    pq.insert(1);
      assertEquals(1, pq.best());
  }

  @Test
  void testBestReturnsCorrectlyWithMultipleElementsInOrder() {
    //Checking with Min Heap
    pq.insert(1);
    pq.insert(2);
    pq.insert(3);
    assertEquals(1, pq.best());
  }

  @Test
  void testBestReturnsCorrectlyWithMultipleElementsOutOfOrder() {
    //Checking with Min Heap
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    assertEquals(1, pq.best());
  }

  @Test
  void testBestEmptyThrowsEmptyException() {
    try {
      int tester = pq.best();
      fail("Empty exception should have been thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  void testRemoveEmptyThrowsEmptyException() {
    try {
      pq.remove();
      fail("Empty exception should have been thrown");
    } catch (EmptyException e) {
      return;
    };
  }

  @Test
  void testRemoveMultipleElements() {
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    pq.remove();
    pq.remove();
    pq.remove();

    assertTrue(pq.empty());
  }

  @Test
  void testRemoveOnlyAffectsBestElement() {
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    pq.remove();

    assertEquals(2, pq.best());
  }

  @Test
  void testGetEmptyThrowsEmptyException() {
    try {
      pq.best();
      fail("Empty exception should have been thrown when getting from empty queue");
    } catch (EmptyException e) {
      return;
    };
  }

  @Test
  void testInsertDuplicate() {
    pq.insert(4);
    pq.insert(4);
    pq.insert(1);
    pq.insert(1);

    assertEquals(1, pq.best());
  }

  @Test
  void testRemoveDuplicateOnlyRemovesOneElement() {
    pq.insert(4);
    pq.insert(4);
    pq.insert(1);
    pq.insert(1);

    pq.remove();
    assertEquals(1, pq.best());

    pq.remove();
    assertEquals(4, pq.best());
  }

  @Test
  void testHandleManyElements() {
    pq.insert(6);
    pq.insert(2);
    pq.insert(4);
    pq.insert(1);
    pq.insert(7);
    pq.insert(4);
    pq.insert(5);

    assertEquals(1,  pq.best());

    pq.remove();
    assertEquals(2, pq.best());
  }

}
