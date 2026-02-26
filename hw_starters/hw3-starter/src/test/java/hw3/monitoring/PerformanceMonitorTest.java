package hw3.monitoring;

import hw3.structures.ArrayBag;
import hw3.structures.LinkedListBag;
import hw3.structures.SortableArrayList;
import hw3.structures.SortableLinkedList;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Focused tests for PerformanceMonitor functionality across all implementations.
 * These tests verify that performance counters are accurate, consistent, and properly
 * track the intended operations.
 */
public class PerformanceMonitorTest {

  // --- SortableArrayList performance monitoring tests ---

  @Test
  public void testSortableArrayListCounterStartsAt0() {
    Integer[] data = {3, 1, 4};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testSortableArrayListCounterReset() {
    Integer[] data = {3, 1, 4};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.compare(list.getPosition(0), list.getPosition(1));
    list.swap(list.getPosition(0), list.getPosition(1));
    monitor.resetCounters();

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testSortableArrayListTracksComparisons() {
    Integer[] data = {3, 1, 4};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.compare(list.getPosition(0), list.getPosition(1));

    assertEquals(1, monitor.getComparisonCount());
  }

  @Test
  public void testSortableArrayListTracksMultipleComparisons() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.compare(list.getPosition(0), list.getPosition(1));
    list.compare(list.getPosition(2), list.getPosition(3));
    list.compare(list.getPosition(1), list.getPosition(4));

    assertEquals(3, monitor.getComparisonCount());
  }

  @Test
  public void testSortableArrayListTracksModifications() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.swap(list.getPosition(0), list.getPosition(1));

    assertEquals(1, monitor.getModificationCount());
  }

  @Test
  public void testSortableArrayListTracksMultipleModifications() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.swap(list.getPosition(0), list.getPosition(1));
    list.swap(list.getPosition(2), list.getPosition(3));
    list.swap(list.getPosition(1), list.getPosition(4));

    assertEquals(3, monitor.getModificationCount());
  }

  @Test
  public void testSortableArrayListUpdatesSwapAndComparisonIndependently() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableArrayList<Integer> list = new SortableArrayList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.swap(list.getPosition(0), list.getPosition(1));

    assertEquals(1, monitor.getModificationCount());
    assertEquals(0, monitor.getComparisonCount());

    list.compare(list.getPosition(2), list.getPosition(3));

    assertEquals(1, monitor.getComparisonCount());
    assertEquals(1, monitor.getModificationCount());

  }


  // --- SortableLinkedList performance monitoring tests ---

  @Test
  public void testSortableLinkedListCounterStartsAt0() {
    Integer[] data = {3, 1, 4};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testSortableLinkedListCounterReset() {
    Integer[] data = {3, 1, 4};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.compare(list.getPosition(0), list.getPosition(1));
    list.swap(list.getPosition(0), list.getPosition(1));
    monitor.resetCounters();

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testSortableLinkedListTracksComparisons() {
    Integer[] data = {3, 1, 4};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.compare(list.getPosition(0), list.getPosition(1));

    assertEquals(1, monitor.getComparisonCount());
  }

  @Test
  public void testSortableLinkedListTracksMultipleComparisons() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.compare(list.getPosition(0), list.getPosition(1));
    list.compare(list.getPosition(2), list.getPosition(3));
    list.compare(list.getPosition(1), list.getPosition(4));

    assertEquals(3, monitor.getComparisonCount());
  }

  @Test
  public void testSortableLinkedListTracksModifications() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.swap(list.getPosition(0), list.getPosition(1));

    assertEquals(1, monitor.getModificationCount());
  }

  @Test
  public void testSortableLinkedListTracksMultipleModifications() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.swap(list.getPosition(0), list.getPosition(1));
    list.swap(list.getPosition(2), list.getPosition(3));
    list.swap(list.getPosition(1), list.getPosition(4));

    assertEquals(3, monitor.getModificationCount());
  }

  @Test
  public void testSortableLinkedListUpdatesSwapAndComparisonIndependently() {
    Integer[] data = {3, 1, 4, 1, 5};
    SortableLinkedList<Integer> list = new SortableLinkedList<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.swap(list.getPosition(0), list.getPosition(1));

    assertEquals(1, monitor.getModificationCount());
    assertEquals(0, monitor.getComparisonCount());

    list.compare(list.getPosition(2), list.getPosition(3));

    assertEquals(1, monitor.getComparisonCount());
    assertEquals(1, monitor.getModificationCount());

  }


// --- ArrayBag performance monitoring tests ---

  @Test
  public void testArrayBagCounterStartsAt0() {
    Integer[] data = {3, 1, 4};
    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testArrayBagCounterReset() {
    Integer[] data = {3, 1, 4};
    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4);
    list.contains(0);
    monitor.resetCounters();

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testArrayBagTracksComparisons() {
    Integer[] data = {3, 1, 4};
    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4); //3rd element --> 3 comparisons expected

    assertEquals(3, monitor.getComparisonCount());
  }

  @Test
  public void testArrayBagTracksModifications() {
    Integer[] data = {3, 1, 4};
    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4); //One transpose modification expected

    assertEquals(1, monitor.getModificationCount());
  }

  @Test
  public void testArrayBagTracksMultipleModifications() {
    Integer[] data = {3, 1, 4};
    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4); //One transpose modification expected
    list.contains(4); //Second transpose modification
    list.contains(3); //Third transpose modification

    assertEquals(3, monitor.getModificationCount());
  }

  @Test
  public void testArrayBagTracksModificationsWhenNonePresent() {
    Integer[] data = {3, 1, 4};
    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(3); //Already at front of list --> no modifications

    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testArrayBagTransposeOptimizationReducesComparisons() {
    Integer[] data = {3, 1, 4, 5, 2, 9};

    ArrayBag<Integer> list = new ArrayBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();

    for (int i = 0; i < 10; i++) {
      list.contains(4);
      list.contains(4);
    }
    //Regular array: 3*10 comparisons = 30 total.

    assertTrue(monitor.getComparisonCount() < 30);
    assertTrue(monitor.getModificationCount() > 0);
  }

// --- LinkedListBag performance monitoring tests ---

  @Test
  public void testLinkedListBagCounterStartsAt0() {
    Integer[] data = {3, 1, 4};
    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testLinkedListBagCounterReset() {
    Integer[] data = {3, 1, 4};
    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4);
    list.contains(0);
    monitor.resetCounters();

    assertEquals(0, monitor.getComparisonCount());
    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testLinkedListBagTracksComparisons() {
    Integer[] data = {3, 1, 4};
    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4); //3rd element --> 3 comparisons expected

    assertEquals(3, monitor.getComparisonCount());
  }

  @Test
  public void testLinkedListTracksModifications() {
    Integer[] data = {3, 1, 4};
    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4); //One move to front modification expected

    assertEquals(1, monitor.getModificationCount());
  }

  @Test
  public void testLinkedListTracksMultipleModifications() {
    Integer[] data = {3, 1, 4};
    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(4); //One move to front modification expected
    list.contains(3); //Second move to front modification
    list.contains(4); //Third move to front modification

    assertEquals(3, monitor.getModificationCount());
  }

  @Test
  public void testLinkedListTracksModificationsWhenNonePresent() {
    Integer[] data = {3, 1, 4};
    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();
    list.contains(3); //Already at front of list --> no modifications

    assertEquals(0, monitor.getModificationCount());
  }

  @Test
  public void testLinkedListBagMoveToFrontOptimizationReducesComparisons() {
    Integer[] data = {3, 1, 4, 5, 2, 9};

    LinkedListBag<Integer> list = new LinkedListBag<>(data);
    PerformanceMonitor monitor = list;

    monitor.resetCounters();

    for (int i = 0; i < 10; i++) {
      list.contains(4);
      list.contains(4);
    }
    //Regular Linked List: 3*10 comparisons = 30 total.

    assertTrue(monitor.getComparisonCount() < 30);
    assertTrue(monitor.getModificationCount() > 0);
  }
}