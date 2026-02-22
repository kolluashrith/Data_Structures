package hw3.structures;

import hw3.structures.SortableArrayList;
import hw3.structures.SortableList;

public class SortableArrayListTest extends SortableListTest {
  @Override
  public SortableList<Integer> createSortableList(Integer[] data) {
    return new SortableArrayList<>(data);
  }
}
