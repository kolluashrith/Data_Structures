package hw3.sort;

import hw3.sort.SelectionSort;
import hw3.sort.SortingAlgorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectionSortTest extends SortingAlgorithmTest {
  @Override
  public SortingAlgorithm<Integer> createSortingAlgorithm() {
    return new SelectionSort<>();
  }

  @Test
  public void testAlgorithmName() {
    assertEquals("Selection Sort", algorithm.getName());
  }
}
