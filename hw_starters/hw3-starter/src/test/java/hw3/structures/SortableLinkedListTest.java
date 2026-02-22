package hw3.structures;

import hw3.structures.SortableLinkedList;
import hw3.structures.SortableList;

public class SortableLinkedListTest extends SortableListTest {
  @Override
  public SortableList<Integer> createSortableList(Integer[] data) {
    return new SortableLinkedList<>(data);
  }
}
