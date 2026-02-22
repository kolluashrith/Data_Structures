package hw3.sort;

import hw3.sort.InsertionSort;
import hw3.sort.SortingAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertionSortTest extends SortingAlgorithmTest {
  @Override
  public SortingAlgorithm<Integer> createSortingAlgorithm() {
    return new InsertionSort<>();
  }

  @Test
  public void testAlgorithmName() {
    assertEquals("Insertion Sort", algorithm.getName());
  }
}
