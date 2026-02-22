package hw3.sort;

import hw3.sort.BubbleSort;
import hw3.sort.SortingAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSortTest extends SortingAlgorithmTest {
  @Override
  public SortingAlgorithm<Integer> createSortingAlgorithm() {
    return new BubbleSort<>();
  }

  @Test
  public void testAlgorithmName() {
    assertEquals("Bubble Sort", algorithm.getName());
  }
}
