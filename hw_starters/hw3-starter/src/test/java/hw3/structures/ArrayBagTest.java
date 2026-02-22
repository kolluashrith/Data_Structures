package hw3.structures;

import hw3.structures.ArrayBag;
import hw3.structures.Bag;

public class ArrayBagTest extends BagTest {
  @Override
  public Bag<Integer> createBag(Integer[] data) {
    return new ArrayBag<>(data);
  }
}
