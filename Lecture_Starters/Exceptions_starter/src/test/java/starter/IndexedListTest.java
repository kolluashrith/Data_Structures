package starter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class IndexedListTest {
  private IndexedList<Integer> numbers;
  private final static int SIZE = 5;
  private final static int DEFAULT_VALUE = 10;

  @BeforeEach
  void setUp() {
    numbers = new ArrayIndexedList<>(SIZE, DEFAULT_VALUE);
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < numbers.length(); index++) {
      assertEquals(DEFAULT_VALUE, numbers.get(index));
    }
  }

  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    numbers.put(2, 7);
    assertEquals(7, numbers.get(2));
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    numbers.put(1, 8);
    assertEquals(8, numbers.get(1));
    numbers.put(1, -5);
    assertEquals(-5, numbers.get(1));
  }

  @Test
  @DisplayName("length() returns the correct size after IndexedList is instantiated.")
  void testLengthAfterConstruction() {
    assertEquals(SIZE, numbers.length());
  }

  @Test
  @DisplayName("get() throws exception if index is below valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      numbers.get(-1);
      fail("IndexException not thrown for index < 0");
    } catch (IndexException ex) {
      return; //if we're here, the exception was thrown
    }
  }

  @Test
  @DisplayName("get() throws exception if index is above valid range.")
  void testGetWithIndexAboveRangeThrowsException() {
    try {
      numbers.get(SIZE);
      fail("IndexException not thrown for index < 0");
    } catch (IndexException ex) {
      return; //if we're here, the exception was thrown
    }
  }

}