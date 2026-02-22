package hw3.sort;

import hw3.sort.OptimizedBubbleSort;
import hw3.sort.SortingAlgorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizedBubbleSortTest extends SortingAlgorithmTest {
  @Override
  public SortingAlgorithm<Integer> createSortingAlgorithm() {
    return new OptimizedBubbleSort<>();
  }

  @Test
  public void testAlgorithmName() {
    assertEquals("Optimized Bubble Sort", algorithm.getName());
  }
}
