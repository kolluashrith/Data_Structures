package hw3.monitoring;

import hw3.monitoring.PerformanceMonitor;
import hw3.sort.BubbleSort;
import hw3.sort.InsertionSort;
import hw3.sort.OptimizedBubbleSort;
import hw3.sort.SelectionSort;
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

  // TODO: Add more tests!

  // --- SortableLinkedList performance monitoring tests ---

  // TODO: Add tests!

  // --- ArrayBag performance monitoring tests ---

  // TODO: Add tests!

  // --- LinkedListBag performance monitoring tests ---

  // TODO: Add tests!

}
