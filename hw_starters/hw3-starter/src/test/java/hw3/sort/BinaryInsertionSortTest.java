package hw3.sort;

import hw3.sort.BinaryInsertionSort;
import hw3.sort.SortingAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryInsertionSortTest extends SortingAlgorithmTest {
  @Override
  public SortingAlgorithm<Integer> createSortingAlgorithm() {
    return new BinaryInsertionSort<>();
  }

  @Test
  public void testAlgorithmName() {
    assertEquals("Binary Insertion Sort", algorithm.getName());
  }
}
