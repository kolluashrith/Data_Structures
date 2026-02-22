package hw3.structures;

import hw3.structures.Bag;
import hw3.structures.LinkedListBag;

public class LinkedListBagTest extends BagTest {
  @Override
  public Bag<Integer> createBag(Integer[] data) {
    return new LinkedListBag<>(data);
  }
}
