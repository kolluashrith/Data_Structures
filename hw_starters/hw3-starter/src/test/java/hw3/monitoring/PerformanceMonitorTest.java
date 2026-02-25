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

  // --- SortableLinkedList performance monitoring tests ---

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

//  // --- ArrayBag performance monitoring tests ---
//
//  @Test
//  public void testArrayBagCounterReset() {
//    Integer[] data = {3, 1, 4};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.compare(0, 1);
//    list.transpose(1);
//    monitor.resetCounters();
//
//    assertEquals(0, monitor.getComparisonCount());
//    assertEquals(0, monitor.getModificationCount());
//  }
//
//  @Test
//  public void testArrayBagTracksComparisons() {
//    Integer[] data = {3, 1, 4};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.compare(0, 1);
//
//    assertEquals(1, monitor.getComparisonCount());
//  }
//
//  @Test
//  public void testArrayBagTracksMultipleComparisons() {
//    Integer[] data = {3, 1, 4, 1, 5};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.compare(0, 1);
//    list.compare(2, 3);
//    list.compare(1, 4);
//
//    assertEquals(3, monitor.getComparisonCount());
//  }
//
//  @Test
//  public void testArrayBagTracksModifications() {
//    Integer[] data = {3, 1, 4, 1, 5};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.transpose(1);
//
//    assertEquals(1, monitor.getModificationCount());
//  }
//
//  @Test
//  public void testArrayBagTracksMultipleModifications() {
//    Integer[] data = {3, 1, 4, 1, 5};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.transpose(1);
//    list.transpose(2);
//    list.transpose(4);
//
//    assertEquals(3, monitor.getModificationCount());
//  }
//
//  // --- LinkedListBag performance monitoring tests ---
//
//  @Test
//  public void testLinkedListBagCounterReset() {
//    Integer[] data = {3, 1, 4};
//    LinkedListBag<Integer> list = new LinkedListBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.compare( 1); //Where tf is the get function???
//    list.moveToFront(1);
//    monitor.resetCounters();
//
//    assertEquals(0, monitor.getComparisonCount());
//    assertEquals(0, monitor.getModificationCount());
//  }
//
//  @Test
//  public void testLinkedListBagTracksComparisons() {
//    Integer[] data = {3, 1, 4};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.compare(0, 1);
//
//    assertEquals(1, monitor.getComparisonCount());
//  }
//
//  @Test
//  public void testLinkedListBagTracksMultipleComparisons() {
//    Integer[] data = {3, 1, 4, 1, 5};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.compare(0, 1);
//    list.compare(2, 3);
//    list.compare(1, 4);
//
//    assertEquals(3, monitor.getComparisonCount());
//  }
//
//  @Test
//  public void testLinkedListBagTracksModifications() {
//    Integer[] data = {3, 1, 4, 1, 5};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.transpose(1);
//
//    assertEquals(1, monitor.getModificationCount());
//  }
//
//  @Test
//  public void testLinkedListBagTracksMultipleModifications() {
//    Integer[] data = {3, 1, 4, 1, 5};
//    ArrayBag<Integer> list = new ArrayBag<>(data);
//    PerformanceMonitor monitor = list;
//
//    monitor.resetCounters();
//    list.transpose(1);
//    list.transpose(2);
//    list.transpose(4);
//
//    assertEquals(3, monitor.getModificationCount());
//  }

}
